import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ConnectionWeb {

	public static void main(String[] args) {
		// TODO have the user prep the web
		int width = 500;
		int height = 500;
		int bubbleSize = 50;
		
		Person adam = new Person("Adam", true);
		Person allen = new Person("Allen");
		Person ana = new Person("Ana");
		adam.addConnection(allen, ConnectionType.FRIEND);
		ana.addConnection(adam, ConnectionType.FRIEND);
		ana.addConnection(allen, ConnectionType.FRIEND);
		
		ArrayList<Person> people = new ArrayList<>();
		people.add(adam);
		people.add(allen);
		people.add(ana);
		
		Web.setUp(width, height, bubbleSize, adam, people);
	}
	
	
	private static class Web extends Canvas {
		private static int width;
		private static int height;
		private static int bubbleSize;
		private static int centerX;
		private static int centerY;
		private static Person mainPerson;
		private static ArrayList<Person> population;
		private static Graphics g;
		
		public static void setUp(int width, int height, int bubbleSize, Person mainPerson, ArrayList<Person> population) {
			Web.width = width;
			Web.height = height;
			Web.bubbleSize = bubbleSize;
			Web.centerX = width / 2;
			Web.centerY = width/2;
			Web.population = population;
			Web.mainPerson = mainPerson;
			
			JFrame frame = new JFrame("Connection Web");
			Canvas canvas = new Web();
			canvas.setSize(width, height);
			frame.add(canvas);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		@Override
		public void paint(Graphics g) {
			Web.g = g;
			
			// draw the main person in the center first
			int bubbleOffset = bubbleSize / 2;
			drawBubble(mainPerson, centerX - bubbleOffset, centerY - bubbleOffset);
			
			for(int i = 0; i < population.size(); i++) {
				if(population.get(i) != mainPerson) {
					drawBubble(population.get(i), i * bubbleSize, i * bubbleSize);
				}
			}
		}
		
		// x is the x coordinate of the top left of the bubble
		// y is the y coordinate of the top left of the bubble
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
