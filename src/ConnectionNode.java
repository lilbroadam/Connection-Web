
// TODO change class name to ConnectionNode so don't have to implement generics for ConnectionType
public class ConnectionNode {
	
	Person person;
	ConnectionType connectionType;
	ConnectionNode nextNode;
	/**
	 * Store a reference to a Person object representing the type of connection to that Person object.<br>
	 * ConnectionNode is meant to be used to point one Person object to another, creating a "connection" between them.
	 *   
	 * @param person
	 * @param connectionType
	 * @param nextNode
	 */
	// TODO @param nextNode might be unneeded. Maybe replace with boolean mutual
	public ConnectionNode(Person person, ConnectionType connectionType, ConnectionNode nextNode) {
		this.person = person;
		this.connectionType = connectionType;
		this.nextNode = nextNode;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person newPerson) {
		person = newPerson;
	}
	
	public ConnectionType getConnectionType() {
		return connectionType;
	}
	
	public void setConnectionType(ConnectionType newConnectionType) {
		connectionType = newConnectionType;
	}
	
	public ConnectionNode getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(ConnectionNode newNextNode) {
		nextNode = newNextNode;
	}
}
