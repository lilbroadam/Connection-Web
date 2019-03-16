import java.util.ArrayList;
import java.util.Iterator;

public class Person {
	
	String name;
	ArrayList<ConnectionNode> connectionsTo;
	
	public Person(Person person, ConnectionType connectionType) {
//		Node<Person, ConnectionType> connection = new Node(Person, connectionType)); 
//		connectionsTo.add();
		ConnectionNode connection = new ConnectionNode(person, connectionType, null);
		connectionsTo.add(connection);
	}
	
	public Person(String name) {
		this.name = name;
		connectionsTo = new ArrayList<>();
	}
	
	public void addConnection(Person person, ConnectionType connection) {
		ConnectionNode connectNode = new ConnectionNode(person, connection, null);
		connectionsTo.add(connectNode);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		Iterator<ConnectionNode> it = connectionsTo.iterator();
		
		if(!it.hasNext())
			System.out.println(name + " has no connections");
		
		while(it.hasNext()) {
			sb.append(name);
			sb.append(" -> ");
			sb.append(it.next().getPerson().name);
		}
		
		return sb.toString();		
	}

}
