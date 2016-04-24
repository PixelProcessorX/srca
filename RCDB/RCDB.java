// Student Recreation Center Application
// (Database Code by Dillon Simion)

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/
import java.sql.*;
import java.util.ArrayList;
// Notice, do not import com.mysql.jdbc.* or you will have problems!


/*
 * 
 *  Timestamp stamp = new Timestamp(System.currentTimeMillis());
  Date date = new Date(stamp.getTime());
  System.out.println(date);
 * 
 */

public class RCDB
{
	public static void main(String[] args) {
		try{
		Connection c = DB.ConnectToDatabase();
		
		
		
		/*
		static EventCat[] GetEventCategories(EventCat cc)
		static Fitness[] GetFitnessCategories(Fitness f)
		static Improvement[] GetImprovementCategories(Improvement ii)
		static Event[] GetEvents()
		static Event[] GetEventsWhere(Event e)
		static UserEvent[] GetUserEvents(User u)
		
		static User NewUser(User u) //Returns the completed User Structure.
		static void DelUser(User u)
		static UserEvent NewUserEvent(UserEvent e, User u)
		static void DelUserEvent(UserEvent e, User u)
		static void DelUserEvents(User u)
		*/
		
		DbEvent ee = new DbEvent(); 
		Fitness ff = new Fitness(); 
		Improvement ii = new Improvement(); 
		EventCat cc = new EventCat(); 
		UserEvent ue = new UserEvent(); 
		User uu = new User(); 
		
		uu.is_instructor = false;
		uu.is_staffmember = true;
		uu.username = "RecEvaluator";
		uu = DB.NewUser(uu);
		
		ue.defined_name = "TestEvent";
		ue.id_event = 2;
		ue.id_user = uu.id;
		ue.is_private = true;
		ue.is_registered = true;
		ue.notify = false;
		ue = DB.NewUserEvent(ue, uu);
		
		cc.description = "improv";
		
		PrintToStringObjectArray( DB.GetEventCategories(cc) );
		PrintToStringObjectArray( DB.GetFitnessCategories(ff) );
		PrintToStringObjectArray( DB.GetImprovementCategories(ii) );
		PrintToStringObjectArray( DB.GetEvents() );
		PrintToStringObjectArray( DB.GetEventsWhere(ee) );
		PrintToStringObjectArray( DB.GetUserEvents(uu) );
		
		DB.DelUser(uu);
		DB.DelUserEvent(ue, uu);
		DB.DelUserEvents(uu);
		
		
		
		
		
		
		
		
		
		//myStmt1.setNull(1, Types.INTEGER);
		/*CallableStatement myStmt1 = c.prepareCall("{call StoredProcedure(?,?,?,?);}");
		myStmt1.setString(1, "First Arg");
		myStmt1.setString(2, "Second Arg");
		myStmt1.setInt(3,    567);
		myStmt1.setString(4, "InOut Example"); //For IN
		myStmt1.registerOutParameter(4, Types.VARCHAR); //For OUT
		myStmt1.execute();
		myStmt1.getString(4);//Get Returned OUT Value
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ResultSetMetaData myRd = myRs.getMetaData();
		
		Statement myStmt0 = c.createStatement();
		String sqlI = "insert into rcdb.table (field, field, field) values ('col0','col1','col2')";
		String sqlU = "update rcdb set columnName='value' where columnA=value";
		String sqlD = "delete from rcdb where columnA=value";
		myStmt0.executeUpdate(sqlI);*/
		
		// 
		
		
		ExecuteSQL(c,"SELECT users.* FROM users WHERE true;");
		ExecuteSQL(c,"SELECT events_.* FROM events_ WHERE true;");
		ExecuteSQL(c,"SELECT userevents.* FROM userevents WHERE true;");
		ExecuteSQL(c,"SELECT cats_event.* FROM cats_event WHERE true;");
		ExecuteSQL(c,"SELECT cats_fit.* FROM cats_fit WHERE true;");
		ExecuteSQL(c,"SELECT cats_impr.* FROM cats_impr WHERE true;");
		
		
		
		
		
		}catch(Exception e){
		    throw new IllegalStateException("Generic Exception Catch Triggered", e);
		}
	}
	static void PrintToStringObjectArray(ToString[] ss){
		for(int i = 0; i < ss.length; i++){
			System.out.println(ss[i].toString());
		}
		System.out.println();
	}
	static void PrintResultSet(ResultSet rs)
	{
		try{
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			rs.next();
			for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        System.out.print(rsmd.getColumnName(i));
		    }
			System.out.println("");
			do{
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
			    System.out.println("");
			}while(rs.next());
			System.out.println("");
		}catch(Exception e){
		    throw new IllegalStateException("Error Printing Records!!", e);
		}
	}
	static void ExecuteSQL(Connection c, String sql)
	{
		try{
			System.out.println(sql);
			Statement s = c.createStatement();
			s.executeQuery(sql);
			ResultSet rs = s.getResultSet();
			PrintResultSet(rs);
		}catch(Exception e){
		    throw new IllegalStateException("Error Retrieving Records or Executing Query!!", e);
		}
	}
	
	
}
