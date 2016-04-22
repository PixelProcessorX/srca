
public class EventCat implements ToString {
	public int id;
	public int id_fit;
	public String name;
	public String description;
	public String tags;
	//For Search Functionality
	public int _id_improve;
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				Integer.toString(id_fit) + " " + 
				name + " " + 
				description + " [" + 
				tags + "]");
	}
	EventCat(){
		id = -1;
		id_fit = -1;
		_id_improve = -1;
		name = "NULL";
		description = "NULL";
		tags = "NULL";
	}
}
