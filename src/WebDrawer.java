import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class WebDrawer {
	
	private List<Person> people;
	private Person mainPerson;
	private int windowWidth;
	private int windowHeight;
	private int bubbleSize;
	private int centerX;
	private int centerY;
	private JFrame frame;
	private WebDrawerCanvas canvas;
	
	public WebDrawer(List<Person> people, int width, int height, int bubbleRadius) {
		this.people = people;
		windowWidth = width;
		windowHeight = height;
		bubbleSize = bubbleRadius;
		centerX = width / 2;
		centerY = height / 2;
		
		// search for the main person
		Iterator<Person> it = people.iterator();
		while(it.hasNext() && mainPerson == null) {
			Person currentPerson = it.next();
			if(currentPerson.isMainPerson())
				mainPerson = currentPerson;
		}
		if(mainPerson == null)
			throw new IllegalStateException("The given people List must contain a main person");
		
		setPeopleCoords(people);
		
		canvas = new WebDrawerCanvas();
		canvas.setSize(width, height);
		frame = new JFrame("Connection Web");
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null); // set initial window location to middle of screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public WebDrawer(List<Person> people, int width, int height) {
		this(people, width, height, 25);
	}
	
	public WebDrawer(List<Person> people, int bubbleRadius) {
		this(people, 600, 400, bubbleRadius);
	}
	
	public WebDrawer(List<Person> people) {
		this(people, 600, 400, 25);
	}
	
	// the WebDrawer object must pass true into this method to display the Web
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
	
	public void setWindowSize(int width, int height) {
		windowWidth = width;
		windowHeight = height;
		frame.setSize(width, height);
	}
	
	public void setBubbleSize(int size) {
		bubbleSize = size;
	}
	
	// assign the coordinates for each Person.
	// coordinates are the center of the bubble of where the Person should be displayed in the Web
	private void setPeopleCoords(List<Person> people) {
		for(Person currentPerson : people) {
			// main Person goes in the middle
			if(currentPerson.isMainPerson()) {
				currentPerson.xCoord = centerX;
				currentPerson.yCoord = centerY;
			} 
			
			// determine everyone else's coordinates
			else { // TODO this section needs to be determined properly
				Random r = new Random();
				currentPerson.xCoord = r.nextInt(windowWidth);
				currentPerson.yCoord = r.nextInt(windowHeight);
			}	
		}
	}
	
	private class WebDrawerCanvas extends Canvas {
		Graphics g;
		
		public WebDrawerCanvas() {
			Mouse mouse = new Mouse();
			this.addMouseListener(mouse);
			this.addMouseMotionListener(mouse);
		}
		
		public void paint(Graphics g) {
			this.g = g;
			
			drawWeb(people);
		}
		
		private void drawWeb(List<Person> people) {
			// TODO could this be turned into a O(N) approach instead of O(N^2)?
			drawPeople(people);
			drawConnections(people);
		}
		
		// draws people in a "bubble" based on the bubble size and the Person's xCoord and yCoord
		private void drawPeople(List<Person> people) {
			for(Person currentPerson : people) {
				int halfBubble = bubbleSize/2;
				String name = currentPerson.name();
				int bubbleXCoord = currentPerson.xCoord - halfBubble;
				int bubbleYCoord = currentPerson.yCoord - halfBubble;
				int nameWidth = g.getFontMetrics().stringWidth(name);
				int nameHeight = g.getFontMetrics().getAscent();
				int nameXOffset = halfBubble - nameWidth/2;
				int nameYOffset = halfBubble + nameHeight/2;
				
				// TODO shouldn't this go before nameWidth is determined?
				// 		what does this even do again?
				g.setFont(g.getFont().deriveFont(Font.PLAIN, 14));
				
				// draw the bubble
				g.drawOval(bubbleXCoord, bubbleYCoord, bubbleSize, bubbleSize);
				g.drawString(name, bubbleXCoord + nameXOffset, bubbleYCoord + nameYOffset);
			}
		}
		
		// draw the Connection from every person to every Connection that Person has
		// TODO different colors for different ConnectionTypes
		private void drawConnections(List<Person> people) {
			for(Person currentPerson : people) {
				int personXCoord = currentPerson.xCoord;
				int personYCoord = currentPerson.yCoord;
				
				for(Connection connection : currentPerson.connections()) {
					int connectionXCoord = connection.getPerson().xCoord;
					int connectionYCoord = connection.getPerson().yCoord;
					
					g.drawLine(personXCoord, personYCoord, connectionXCoord, connectionYCoord);
				}
			}
		}
		
		private class Mouse extends Frame implements MouseListener, MouseMotionListener {
			
			@Override
			public void mouseClicked(MouseEvent e) {} // TODO

			@Override
			public void mousePressed(MouseEvent e) {} // TODO

			@Override
			public void mouseReleased(MouseEvent e) {} // TODO

			@Override
			public void mouseEntered(MouseEvent e) {} // TODO

			@Override
			public void mouseExited(MouseEvent e) {} // TODO

			@Override
			public void mouseDragged(MouseEvent e) { // TODO this method was just written for testing
				mainPerson.xCoord = e.getX();
				mainPerson.yCoord = e.getY();
				canvas.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {} // TODO
			
		}
		
	}
}
