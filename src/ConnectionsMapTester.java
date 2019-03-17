
public class ConnectionsMapTester {
	
	public static void main(String args[]) {
		Person adam = new Person("Adam", true);
		Person allen = new Person("Allen");
		adam.addConnection(allen, ConnectionType.FRIEND, true);

		Person ana = new Person("Ana");
		ana.addConnection(adam, ConnectionType.FRIEND, true);
		ana.addConnection(allen, ConnectionType.FRIEND, true);

		System.out.println(adam); // *Adam <-> Allen   Adam <-> Ana
		System.out.println(allen); // Allen <-> *Adam   Allen <-> Ana
		System.out.println(ana); // Ana <-> *Adam   Ana <-> Ana
	}
}
