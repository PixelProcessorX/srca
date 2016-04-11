
public class Fitness implements ToString {
	public int id;
	public String name;
	public String description;
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
