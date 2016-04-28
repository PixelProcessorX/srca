
public class Fitness implements ToString {
	public int id; /** SEARCH FIELD, KEEP -1 FOR SEARCH*/
	public String name; /** SEARCH FIELD*/
	public String description; /** SEARCH FIELD*/
	//For Search Functionality
	public int _id_impr;
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				name + " " + 
				description);
	}
	Fitness(){
		id = -1;
		_id_impr = -1;
		name = "NULL";
		description = "NULL";
	}
}
