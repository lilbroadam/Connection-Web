import java.util.ArrayList;

public class ConnectionWeb {

	public static void main(String[] args) {
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
		
//		WebDrawer webDrawer = new WebDrawer(people);
		WebDrawer webDrawer = new WebDrawer(people, width, height, bubbleSize);
		webDrawer.setVisible(true);
//		webDrawer.setWindowSize(width, height);
//		webDrawer.setBubbleSize(bubbleSize);
	}
	
}
