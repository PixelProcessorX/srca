package RCDB;

public class UserEvent implements ToString {
	public int id;
	public int id_user;
	public int id_event;
	public boolean is_private;
	public boolean is_registered;
	public String defined_name;
	public boolean notify;
	public java.sql.Timestamp notify_when;
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				Integer.toString(id_user) + " " + 
				Integer.toString(id_event) + " PrivateRow:" + 
				Boolean.toString(is_private) + " Registered:" + 
				Boolean.toString(is_registered) + " " + 
				defined_name + " " +
				Boolean.toString(notify) + " " + 
				notify_when.toString());
	}
	UserEvent(){
		id = -1;
		id_user = -1;
		id_event = -1;
		is_private = true;
		is_registered = false;
		defined_name = "NULL";
		notify = false;
		notify_when = java.sql.Timestamp.valueOf("1900-01-01 00:00:00.000000");
	}
}
