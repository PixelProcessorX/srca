import java.sql.*;
import java.util.ArrayList;
import java.lang.reflect.*;

public class DB {
	public static boolean ENABLEDEBUG = true;
	public static Connection c = null;
	static Connection ConnectToDatabase(){
		Connection connection = null;
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		String url = "jdbc:mysql://" + RCDB.where + ":" + Integer.toString(RCDB.port) + "/rcdb";
		try{
		    c = connection = DriverManager.getConnection(url, RCDB.username, RCDB.password);
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
	static EventCat[] GetEventCategories(EventCat cc) 
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetCatsEvent(?, ?, ?, ?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, cc.id);
		myStmt1.setInt      (x++, cc._id_fit);
		myStmt1.setInt      (x++, cc.id_improve);
		myStmt1.setString   (x++, cc.name);
		myStmt1.setString   (x++, cc.description);
		myStmt1.setString   (x++, cc.tags);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<EventCat> list = new ArrayList<EventCat>();
		while(myRs.next()){ int i = 1;
			EventCat ec       = new EventCat();
			ec.id             = myRs.getInt(i++);
			ec.id_improve     = myRs.getInt(i++);
			ec.name           = myRs.getString(i++);
			ec.description    = myRs.getString(i++);
			ec.tags           = myRs.getString(i++);
			list.add(ec);}
		EventCat[] result = new EventCat[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static Fitness[] GetFitnessCategories(Fitness f)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetCatsFitness(?, ?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, f.id);
		myStmt1.setInt      (x++, f._id_impr);
		myStmt1.setString   (x++, f.name);
		myStmt1.setString   (x++, f.description);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<Fitness> list = new ArrayList<Fitness>();
		while(myRs.next()){ int i = 1;
			Fitness ff        = new Fitness();
			ff.id             = myRs.getInt(i++);
			ff.name           = myRs.getString(i++);
			ff.description    = myRs.getString(i++);
			list.add(ff);}
		Fitness[] result = new Fitness[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static Improvement[] GetImprovementCategories(Improvement ii)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetCatsImprovement(?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, ii.id);
		myStmt1.setString   (x++, ii.name);
		myStmt1.setString   (x++, ii.description);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<Improvement> list = new ArrayList<Improvement>();
		while(myRs.next()){ int i = 1;
			Improvement im    = new Improvement();
			im.id             = myRs.getInt(i++);
			im.id_fit         = myRs.getInt(i++);
			im.name           = myRs.getString(i++);
			im.description    = myRs.getString(i++);
			list.add(im);}
		Improvement[] result = new Improvement[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static DbEvent[] GetEvents()
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetEvents()}");
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<DbEvent> list = new ArrayList<DbEvent>();
		while(myRs.next()){ int i = 1; 
			DbEvent ee          = new DbEvent();
			ee.id             = myRs.getInt(i++);
			ee.id_cat         = myRs.getInt(i++);
			ee.id_instructor  = myRs.getInt(i++);
			ee.name           = myRs.getString(i++);
			ee.description    = myRs.getString(i++);
			ee.when_days      = myRs.getString(i++);
			ee.when_beg       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.when_end       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.cost_cents     = myRs.getInt(i++);
			ee.link           = myRs.getString(i++);
			ee.calendar_id    = myRs.getString(i++);
			ee.status         = myRs.getString(i++);
			ee.last_updated   = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			list.add(ee);}
		DbEvent[] result = new DbEvent[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static DbEvent[] GetEventsWhere(DbEvent e)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetEventsWhere(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, e.id);
		myStmt1.setInt      (x++, e.id_cat);
		myStmt1.setInt      (x++, e.id_instructor);
		myStmt1.setString   (x++, e.name);
		myStmt1.setTimestamp(x++, e._when_beg_stt);//Must be '1900-01-01 00:00:00' or greater!
		myStmt1.setTimestamp(x++, e._when_beg_end);//Must be '1900-01-01 00:00:00' or greater!
		myStmt1.setTimestamp(x++, e._when_end_stt);//Must be '1900-01-01 00:00:00' or greater!
		myStmt1.setTimestamp(x++, e._when_end_end);//Must be '1900-01-01 00:00:00' or greater!
		myStmt1.setInt      (x++, e._cost_cents_min);
		myStmt1.setInt      (x++, e._cost_cents_max);
		myStmt1.setInt      (x++, e._cat_fit);
		myStmt1.setInt      (x++, e._cat_impr);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<DbEvent> list = new ArrayList<DbEvent>();
		while(myRs.next()){ int i = 1;
			DbEvent ee          = new DbEvent();
			ee.id             = myRs.getInt(i++);
			ee.id_cat         = myRs.getInt(i++);
			ee.id_instructor  = myRs.getInt(i++);
			ee.name           = myRs.getString(i++);
			ee.description    = myRs.getString(i++);
			ee.when_days      = myRs.getString(i++);
			ee.when_beg       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.when_end       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.cost_cents     = myRs.getInt(i++);
			ee.link           = myRs.getString(i++);
			ee.calendar_id    = myRs.getString(i++);
			ee.status         = myRs.getString(i++);
			ee.last_updated   = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			list.add(ee);}
		DbEvent[] result = new DbEvent[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static UserEvent[] GetUserEvents(User u)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetUserEvents(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<UserEvent> list = new ArrayList<UserEvent>();
		while(myRs.next()){ int i = 1;
			UserEvent ue     = new UserEvent();
			ue.id            = myRs.getInt(i++);
			ue.id_user       = myRs.getInt(i++);
			ue.id_event      = myRs.getInt(i++);
			ue.is_private    = myRs.getBoolean(i++);
			ue.is_registered = myRs.getBoolean(i++);
			ue.defined_name  = myRs.getString(i++);
			ue.notify        = myRs.getBoolean(i++);
			ue.notify_when   = myRs.getTimestamp(i++);
			list.add(ue);}
		UserEvent[] result = new UserEvent[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	static User NewUser(User u) //Returns the completed User Structure.
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call NewUser(?, ?, ?, ?, ?, ?)}"); int x = 1;
		myStmt1.setBoolean  (x++, u.is_staffmember);
		myStmt1.setBoolean  (x++, u.is_instructor);
		myStmt1.setBytes    (x++, u.googleid);
		myStmt1.setBytes    (x++, u.passhash);
		myStmt1.setBytes    (x++, u.authtoken);
		myStmt1.setString   (x++, u.username);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		int i = 1;
		myRs.next();
		User uu           = new User();
		uu.id             = myRs.getInt(i++);
		uu.is_staffmember = myRs.getBoolean(i++);
		uu.is_instructor  = myRs.getBoolean(i++);
		uu.googleid       = myRs.getBytes(i++);
		uu.passhash       = myRs.getBytes(i++);
		uu.authtoken      = myRs.getBytes(i++);
		uu.username       = myRs.getString(i++);
		uu.login_token    = myRs.getBytes(i++);//Filled Value Required for User Interactivity Verification
		if(ENABLEDEBUG) System.out.println("Created the account " + uu.id + " " + uu.username + "\n");
		if(ENABLEDEBUG) System.out.flush();
		return uu;
	}
	static void DelUser(User u)
	throws SQLException
	{
		//This operation CANNOT BE UNDONE, so there should be a CONFIRMATION DIALOG before the user is allowed to do this.
		CallableStatement myStmt1 = c.prepareCall("{call DeleteLoggedInUser(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		if(ENABLEDEBUG) System.out.println("Deleted the account " + u.id + " " + u.username + ".\n");
		if(ENABLEDEBUG) System.out.flush();
	}
	static UserEvent NewUserEvent(UserEvent e, User u)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call NewUserEvent(?, ?, ?, ?, ?, ?, ?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, e.id_user);
		myStmt1.setInt      (x++, e.id_event);
		myStmt1.setBoolean  (x++, e.is_private);
		myStmt1.setBoolean  (x++, e.is_registered);
		myStmt1.setString   (x++, e.defined_name);
		myStmt1.setBoolean  (x++, e.notify);
		myStmt1.setTimestamp(x++, e.notify_when);
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		int i = 1;
		myRs.next();
		UserEvent ue      = new UserEvent();
		ue.id             = myRs.getInt(i++);
		ue.id_user        = myRs.getInt(i++);
		ue.id_event       = myRs.getInt(i++);
		ue.is_private     = myRs.getBoolean(i++);
		ue.is_registered  = myRs.getBoolean(i++);
		ue.defined_name   = myRs.getString(i++);
		ue.notify         = myRs.getBoolean(i++);
		ue.notify_when    = myRs.getTimestamp(i++);
		if(ENABLEDEBUG) System.out.println("Created a New User Event " + ue.id + " for " + u.id + " " + u.username + ".\n");
		if(ENABLEDEBUG) System.out.flush();
		return ue;
	}
	static void DelUserEvent(UserEvent e, User u)
	throws SQLException
	{
		//This operation CANNOT BE UNDONE.
		CallableStatement myStmt1 = c.prepareCall("{call DeleteLoggedInUserEvent(?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, e.id);
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		if(ENABLEDEBUG) System.out.println("The User Event " + e.id + " for " + u.username + " Was Deleted\n");
		if(ENABLEDEBUG) System.out.flush();
	}
	static void DelUserEvents(User u)
	throws SQLException
	{
		//This operation CANNOT BE UNDONE.
		CallableStatement myStmt1 = c.prepareCall("{call DeleteLoggedInUserEvents(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		if(ENABLEDEBUG) System.out.println("All User Events for " + u.username + " Deleted\n");
		if(ENABLEDEBUG) System.out.flush();
	}
	static User LoginFunction(byte[] credentials)
	throws SQLException
	{
		//NOT IMPLEMENTED, REQUIRES GOOGLE SIGNIN FUNCTIONALITY
		return null;
	}
	
	
	

	static DbEvent[] AdminGetUsers()
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetEvents()}");
		if(ENABLEDEBUG) System.out.println(myStmt1);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<DbEvent> list = new ArrayList<DbEvent>();
		while(myRs.next()){ int i = 1; 
			DbEvent ee          = new DbEvent();
			ee.id             = myRs.getInt(i++);
			ee.id_cat         = myRs.getInt(i++);
			ee.id_instructor  = myRs.getInt(i++);
			ee.name           = myRs.getString(i++);
			ee.description    = myRs.getString(i++);
			ee.when_days      = myRs.getString(i++);
			ee.when_beg       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.when_end       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			ee.cost_cents     = myRs.getInt(i++);
			ee.link           = myRs.getString(i++);
			ee.calendar_id    = myRs.getString(i++);
			ee.status         = myRs.getString(i++);
			ee.last_updated   = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
			list.add(ee);}
		DbEvent[] result = new DbEvent[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		if(ENABLEDEBUG) System.out.flush();
		return result;
	}
	
	
	
	
	
	
	
	
}
