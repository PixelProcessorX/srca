package RCDB;

public class Improvement implements ToString {
	public int id; /** SEARCH FIELD, KEEP -1 FOR SEARCH*/
	public int id_fit; /** SEARCH FIELD*/
	public String name; /** SEARCH FIELD*/
	public String description; /** SEARCH FIELD*/
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				Integer.toString(id_fit) + " " + 
				name + " " + 
				description);
	}
	Improvement(){
		id = -1;
		id_fit = -1;
		name = "NULL";
		description = "NULL";
	}
}
