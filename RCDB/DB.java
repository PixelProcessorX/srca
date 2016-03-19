import java.sql.*;
import java.util.ArrayList;

public class DB {
	static Connection ConnectToDatabase(){
		Connection connection = null;
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		String url = "jdbc:mysql://" + RCDB.where + ":" + Integer.toString(RCDB.port) + "/rcdb";
		try{
		    connection = DriverManager.getConnection(url, RCDB.username, RCDB.password);
		    return connection;
		}catch(SQLException e){
		    if(RCDB.debugmode == true){
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: " + e.getSQLState());
			    System.out.println("VendorError: " + e.getErrorCode());
		    }
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	static EventCat[] GetEventCategories(EventCat c, int cat_impr_id){
		
		return null;
	}
	static Fitness[] GetFitnessCategories(Fitness f){
		
		return null;
	}
	static Improvement[] GetImprovementCategories(Improvement i){
		
		return null;
	}
	static Event[] GetEvents(){
		
		return null;
	}
	static Event[] GetEventsWhere(Event e){
		
		return null;
	}
	static UserEvent[] GetUserEvents(User u){
		
		return null;
	}
	static void NewUser(User u)
	{
		/*
			new_user.is_staffmember, 
			new_user.is_instructor, 
			new_user.googleid, //4096 length
			new_user.passhash, //4096 length
			new_user.authtoken,//4096 length
			new_user.username*/
	}
	static void DelUser(User u)
	{
		
	}
	static void NewUserEvent(UserEvent e, User u)
	{
		
	}
	static void DelUserEvent(UserEvent e, User u)
	{
		
	}
	static void DelUserEvents(User u)
	{
		
	}
	static User LoginFunction(byte[] credentials){
		
		return null;
	}
}
