
public class ConnectionsMap {

	public static void main(String[] args) {
		Person adam = new Person("Adam");
		Person allen = new Person("Allen");
		adam.addConnection(allen, ConnectionType.FRIEND);
		System.out.println(adam); // Adam -> Allen
		System.out.println(allen); // Allen has no connections
	}

}
