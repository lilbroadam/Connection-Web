import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ConnectionWeb {

	public static void main(String[] args) {
		// TODO have the user prep the web
//		Ecosystem ecosystem = new Ecosystem();
		
		Person adam = new Person("Adam", true);
		Person allen = new Person("Allen");
		Person ana = new Person("Ana");
		adam.addConnection(allen, ConnectionType.FRIEND);
		ana.addConnection(adam, ConnectionType.FRIEND);
		ana.addConnection(allen, ConnectionType.FRIEND);
		
//		ecosystem.addPerson(adam, allen, ana);
		
		
//		Web.drawWeb(ecosystem);
		
		ArrayList<Person> people = new ArrayList<>();
		people.add(adam);
		people.add(allen);
		people.add(ana);
		Web.drawWeb(people);
		
	}
	
	
	private static class Web extends Canvas {
		private static int width = 500;
		private static int height = 500;
//		private static Ecosystem ecosystem;
		private static ArrayList<Person> population;
		
		public static void drawWeb(ArrayList<Person> population) {
			JFrame frame = new JFrame("Connection Web");
			Canvas canvas = new Web();
			canvas.setSize(width, height);
			frame.add(canvas);
			frame.pack();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
//			Web.ecosystem = ecosystem;
			Web.population = population;
		}
		
		public void paint(Graphics g) {
			for(int i = 0; i < population.size(); i++) {
				g.drawString(population.get(i).getName(), 5*i, 5*i);
			}
		}
	}
}
