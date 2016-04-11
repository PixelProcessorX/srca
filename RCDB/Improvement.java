
public class Improvement implements ToString {
	public int id;
	public int id_fit;
	public String name;
	public String description;
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
