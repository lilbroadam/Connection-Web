import java.util.ArrayList;

public class Ecosystem {
	
	private ArrayList<Person> ecosystem;
	
	public Ecosystem() {
		ecosystem = new ArrayList<>();
	}

	public void addPerson(Person...addPerson) {
		for(Person person : addPerson)
			ecosystem.add(person);
	}
	
	public ArrayList<Person> getMembers() {
		return ecosystem;
	}
	
	public int size() {
		return ecosystem.size();
	}

}
