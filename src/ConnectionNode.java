
// TODO change class name to ConnectionNode so don't have to implement generics for ConnectionType
public class ConnectionNode {
	
	private Person person;
	private ConnectionType connectionType;
	private boolean mutual;
	
	
	/**
	 * Store a reference to a Person object representing the type of connection to that Person object.<br>
	 * ConnectionNode is meant to be used to point one Person object to another, creating a "connection" between them.
	 *   
	 * @param person
	 * @param connectionType
	 * @param mutual
	 */
	public ConnectionNode(Person person, ConnectionType connectionType, boolean isMutual) {
		this.person = person;
		this.connectionType = connectionType;
		mutual = isMutual;
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
	
	public boolean getMutual() {
		return mutual;
	}
	
	public void setMutual(boolean newMutual) {
		mutual = newMutual;
	}
}
