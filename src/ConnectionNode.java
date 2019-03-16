
// TODO change class name to ConnectionNode so don't have to implement generics for ConnectionType
public class ConnectionNode {
	
	Person person;
	ConnectionType connectionType;
	ConnectionNode nextNode;
	
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
	
	public ConnectionNode getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(ConnectionNode newNextNode) {
		nextNode = newNextNode;
	}
}
