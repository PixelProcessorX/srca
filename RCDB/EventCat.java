
public class EventCat implements ToString {
	public int id;
	public int id_improve;
	public String name;
	public String description;
	public String tags;
	//For Search Functionality
	public int _id_fit;
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				Integer.toString(id_improve) + " " + 
				name + " " + 
				description + " [" + 
				tags + "]");
	}
	EventCat(){
		id = -1;
		id_improve = -1;
		_id_fit = -1;
		name = "NULL";
		description = "NULL";
		tags = "NULL";
	}
}
