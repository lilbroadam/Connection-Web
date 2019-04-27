import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;

public class WebDrawer {
	
	private List<Person> people;
	private Person mainPerson;
	private int windowWidth;
	private int windowHeight;
	private int bubbleSize;
	private int centerX;
	private int centerY;
	private JFrame frame;
	private Canvas canvas;
	
	public WebDrawer(List<Person> people, int width, int height, int bubbleRadius) {
		this.people = people;
		windowWidth = width;
		windowHeight = height;
		bubbleSize = bubbleRadius;
		centerX = width / 2;
		centerY = height / 2;
		
		// search for the main person
		for(Person currentPerson : people)
			if(currentPerson.isMainPerson())
				mainPerson = currentPerson;
		if(mainPerson == null)
			throw new IllegalStateException("The given people List must contain a main person");
				
		frame = new JFrame("Connection Web");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		canvas = new WebDrawerCanvas();
		canvas.setSize(width, height);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null); // set default window location to middle of screen
	}
	
	public WebDrawer(List<Person> people, int width, int height) {
		this(people, width, height, 25);
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
	
	private class WebDrawerCanvas extends Canvas {
		Graphics g;
		
		public void paint(Graphics g) {
			this.g = g;
			
			setPeopleCoords(people);
			drawWeb(people);
		}
		
		// assign the coordinates for each person
		// coordinates are the center coordinate of where the Person should be displayed in the Web
		private void setPeopleCoords(List<Person> people) {
			
			for(Person currentPerson : people) {
				// main Person goes in the middle
				if(currentPerson.isMainPerson()) {
					currentPerson.xCoord = centerX;
					currentPerson.yCoord = centerY;
				} else { // TODO this section needs to be determined better
					Random r = new Random();
					currentPerson.xCoord = r.nextInt(windowWidth);
					currentPerson.yCoord = r.nextInt(windowHeight);
				}
					
			}
			
		}
		
		private void drawWeb(List<Person> people) {
			// TODO could this be turned into a T(N) appraoch instead of T(2N)?
			drawPeople(people);
			drawConnections(people);
		}
		
		// draws people in a "bubble" based on the buble size and the Person's xCoord and yCoord
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
				
				// draw the buble
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
		
	}
	
}
