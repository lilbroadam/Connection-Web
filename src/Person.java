import java.util.ArrayList;
import java.util.Iterator;

public class Person {
	
	private String name;
	private ArrayList<Connection> connectionsTo;
	private boolean mainPerson;
	
	
	public Person(String name, boolean mainPerson) {
		this.name = name;
		this.mainPerson = mainPerson;
		connectionsTo = new ArrayList<>();
	}
	
	public Person(String name) {
		this(name, false);
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
		Connection connection = new Connection(person, connectionType, mutual);
		connectionsTo.add(connection);
		
		if(mutual) {
			// make the connection from the @param person to this Person
			Connection connection2 = new Connection(this, connectionType, mutual);
			person.connectionsTo.add(connection2);
		}
	}
	
	/**
	 * Add a new mutual connection from the calling Person to the @param Person
	 * @param person
	 * @param connection
	 */
	public void addConnection(Person person, ConnectionType connection) {
		addConnection(person, connection, true);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMainPerson() {
		return mainPerson;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		Iterator<Connection> it = connectionsTo.iterator();
		
		if(!it.hasNext())
			System.out.println(name + " has no connections");
		
		while(it.hasNext()) {
			Connection currentConnection = it.next();
			String currentPersonName = currentConnection.getPerson().name;
			
			
			if(mainPerson)
				sb.append("*" + name);
			else
				sb.append(name);
			
			if(currentConnection.getMutual())
				sb.append(" <-> ");
			else
				sb.append(" -> ");
			
			if(currentConnection.getPerson().isMainPerson())
				sb.append("*" + currentPersonName);
			else
				sb.append(currentPersonName);
			sb.append("\n");
		}
		
		
		return sb.toString();		
	}

}
