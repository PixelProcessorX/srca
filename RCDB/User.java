

public class User {
	public int id;
	public boolean is_staffmember;
	public boolean is_instructor;
	public byte[] googleid;//MUST INITIALIZE 4096 BYTES
	public byte[] passhash;//MUST INITIALIZE 4096 BYTES
	public byte[] authtoken;//MUST INITIALIZE 4096 BYTES
	public String username;
	public byte[] login_token;//MUST INITIALIZE 4096 BYTES
}
