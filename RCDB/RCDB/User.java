package RCDB;

public class User implements ToString {
	public int id;
	public boolean is_staffmember;
	public boolean is_instructor;
	public String googleid;
	public String google_email;
	public String username;
	public byte[] login_token;//MUST INITIALIZE 4096 BYTES
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " Staff:" + 
				Boolean.toString(is_staffmember) + " Inst.:" + 
				Boolean.toString(is_instructor) + " " + 
				username + " ");
	}
	User(){
		id = -1;
		is_staffmember = false;
		is_instructor = false;
		googleid = "";
		google_email = "";
		username = "NULL";
		login_token = null;
	}
}
