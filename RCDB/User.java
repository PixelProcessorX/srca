
public class User implements ToString {
	public int id;
	public boolean is_staffmember;
	public boolean is_instructor;
	public byte[] googleid;//MUST INITIALIZE 4096 BYTES
	public byte[] passhash;//MUST INITIALIZE 4096 BYTES
	public byte[] authtoken;//MUST INITIALIZE 4096 BYTES
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
		googleid = null;
		passhash = null;
		authtoken = null;
		username = "NULL";
		login_token = null;
	}
}
