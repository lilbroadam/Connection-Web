import java.util.ArrayList;
import java.util.Iterator;

public class Person {
	
	String name;
	ArrayList<ConnectionNode> connectionsTo;
	
	public Person(Person person, ConnectionType connectionType) {
		ConnectionNode connection = new ConnectionNode(person, connectionType, null);
		connectionsTo.add(connection);
	}
	
	public Person(String name) {
		this.name = name;
		connectionsTo = new ArrayList<>();
	}
	
	/**
	 * If mutual is true, adds a new mutual connection between the calling Person and the @param person.<br>
	 * If mutual is false, adds a one-way connection from the calling Person to the @param person.
	 * 
	 * @param person the Person this Person is adding a connection to
	 * @param connectionType the type of connection this connection is
	 * @param mutual if this connection is mutual or not. 
	 */
	public void addConnection(Person person, ConnectionType connectionType, boolean mutual) {
		// add the connection from this Person to the @param person
		ConnectionNode connection = new ConnectionNode(person, connectionType, null);
		connectionsTo.add(connection);
		
		if(mutual) {
			// make the connection from the @param person to this Person
			ConnectionNode connection2 = new ConnectionNode(this, connectionType, null);
			this.connectionsTo.add(connection2);
		}
	}
	
	/**
	 * Adds a new connection from the calling Person to the @param Person
	 * @param person
	 * @param connection
	 */
	public void addConnection(Person person, ConnectionType connection) {
		addConnection(person, connection, false);
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
