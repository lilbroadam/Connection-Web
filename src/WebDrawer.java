import java.util.List;
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
	
	public WebDrawer(List<Person> people, int width, int height, int bubble) {
		this.people = people;
		windowWidth = width;
		windowHeight = height;
		bubbleSize = bubble;
		centerX = width / 2;
		centerY = height / 2;
		
		// search for the main person
		for(Person person : people)
			if(person.isMainPerson())
				mainPerson = person;
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
	
	// the WebDrawer object must pass in true to this method to display the Web
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
			
			// draw the main person in the center first
			int bubbleOffset = bubbleSize / 2;
			drawBubble(mainPerson, centerX - bubbleOffset, centerY - bubbleOffset);
			
			for(int i = 0; i < people.size(); i++)
				if(people.get(i) != mainPerson)
					drawBubble(people.get(i), i * bubbleSize, i * bubbleSize);
		}
		
		// x is the x coordinate of the top left of the bubble
		// y is the y coordinate of the top left of the bubble
		// TODO make a Bubble class to use as a wrapper class for Person but I forgot why
		private void drawBubble(Person person, int x, int y) {
			String name = person.getName();
			
			int nameWidth = g.getFontMetrics().stringWidth(name);
			int nameHeight = g.getFontMetrics().getAscent();			
			int nameXOffset = bubbleSize/2 - nameWidth/2;
			int nameYOffset = bubbleSize/2 + nameHeight/2;
			
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 14));
			
			g.drawOval(x, y, bubbleSize, bubbleSize);
			g.drawString(name, x + nameXOffset, y + nameYOffset);
		}
	}
	
}
