package RCDB;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class DB {
	public static String  where           = "localhost"; //IP address or URL after //, etc...
	public static int     port            = 3306;        //The port the MySQL database is active on.
	public static String  username        = "User";
	public static String  password        = "%cr7fj#49Lc)A;_*i`Fslby1XttR99m_";
	public static String  username_admin  = "Admin";
	public static String  password_admin  = "tTkd!SyMN%}#E0Fzjb~9Nhcf)y'dko@0";
   private static String  admin_key       = "8EA8E23FA108B328613678DEEDD9142D6558386DCD6ECECC42F3461443DF5577";
	public static Connection c = null;
	public static Connection a = null;
	
	static boolean CompareToAdminKey(String key){
		return admin_key.equals(key);
	}
	static void ConnectToDatabase(){
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		String url = "jdbc:mysql://" + DB.where + ":" + Integer.toString(DB.port) + "/rcdb";
		try{
		    c = DriverManager.getConnection(url, DB.username, DB.password);
		    a = DriverManager.getConnection(url, DB.username_admin, DB.password_admin);
		}catch(SQLException e){
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	static byte[] ByteArray_To_byteArray(Byte[] oBytes)
	{
	    byte[] bytes = new byte[oBytes.length];
	    for(int i = 0; i < oBytes.length; i++) {
	        bytes[i] = oBytes[i];
	    }
	    return bytes;
	}
	static protected ResultSet ParamizedExecSQL(String sql, Object... args)
	throws SQLException
	{
		PreparedStatement stmt = a.prepareStatement(sql);
		for(int p = 0; p < args.length; p++){
			String n = args[p].getClass().getName();
			switch(n){
				case "java.sql.Timestamp":{
					stmt.setTimestamp(p+1, ((java.sql.Timestamp)args[p]));
				}break;
				case "java.lang.String":{
					stmt.setString(p+1, ((String)args[p]));
				}break;
				case "java.lang.Integer":{
					stmt.setInt(p+1, ((Integer)args[p]).intValue());
				}break;
				case "java.lang.Boolean":{
					stmt.setBoolean(p+1, ((Boolean)args[p]).booleanValue());
				}break;
				case "[Ljava.lang.Byte":{
					stmt.setBytes(p+1, ByteArray_To_byteArray((Byte[])args[p]));
				}break;
			}
		}
		String t = sql.substring(0, 6).toUpperCase(); // Type of SQL command.
		if(t.equals("INSERT") || t.equals("UPDATE") || t.equals("DELETE")){
			stmt.executeUpdate();
			return null;
		}
		return stmt.executeQuery();
	}
	static EventCat[] GetEventCategories(EventCat cc) 
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetCatsEvent(?, ?, ?, ?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, cc.id);
		myStmt1.setInt      (x++, cc.id_fit);
		myStmt1.setInt      (x++, cc._id_improve);
		myStmt1.setString   (x++, cc.name);
		myStmt1.setString   (x++, cc.description);
		myStmt1.setString   (x++, cc.tags);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		ArrayList<EventCat> list = new ArrayList<EventCat>();
		while(myRs.next()){ int i = 1;
			EventCat ec       = new EventCat();
			ec.id             = myRs.getInt(i++);
			ec.id_fit         = myRs.getInt(i++);
			ec.name           = myRs.getString(i++);
			ec.description    = myRs.getString(i++);
			ec.tags           = myRs.getString(i++);
			list.add(ec);}
		EventCat[] result = new EventCat[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
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
		return result;
	}
	static Improvement[] GetImprovementCategories(Improvement ii)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetCatsImprovement(?, ?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, ii.id);
		myStmt1.setString   (x++, ii.name);
		myStmt1.setString   (x++, ii.description);
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
		return result;
	}
	static DbEvent[] GetEvents()
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetEvents()}");
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
			ee.when_day      = myRs.getString(i++);
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
		return result;
	}
	static DbEvent TranslateDbEvent(ResultSet myRs)
	throws SQLException
	{
		myRs.next();
		int i = 1; 
		DbEvent ee          = new DbEvent();
		ee.id             = myRs.getInt(i++);
		ee.id_cat         = myRs.getInt(i++);
		ee.id_instructor  = myRs.getInt(i++);
		ee.name           = myRs.getString(i++);
		ee.description    = myRs.getString(i++);
		ee.when_day      = myRs.getString(i++);
		ee.when_beg       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
		ee.when_end       = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
		ee.cost_cents     = myRs.getInt(i++);
		ee.link           = myRs.getString(i++);
		ee.calendar_id    = myRs.getString(i++);
		ee.status         = myRs.getString(i++);
		ee.last_updated   = myRs.getTimestamp(i++);//Must be '1900-01-01 00:00:00' or greater!
		return ee;
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
			ee.when_day      = myRs.getString(i++);
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
		return result;
	}
	static UserEvent[] TranslateUserEvents(ResultSet myRs)
	throws SQLException
	{
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
		return result;
	}
	static UserEvent[] GetUserEvents(User u)
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call GetUserEvents(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		return TranslateUserEvents(myRs);
	}
	static User NewUser(User u) //Returns the completed User Structure.
	throws SQLException
	{
		CallableStatement myStmt1 = c.prepareCall("{call NewUser(?, ?)}"); int x = 1;
		myStmt1.setString   (x++, u.googleid);
		myStmt1.setString   (x++, u.google_email);
		myStmt1.setString   (x++, u.username);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		return TranslateUser(myRs);
	}
	static User TranslateUser(ResultSet myRs)
	throws SQLException
	{
		int i = 1;
		myRs.next();
		User uu           = new User();
		uu.id             = myRs.getInt(i++);
		uu.is_staffmember = myRs.getBoolean(i++);
		uu.is_instructor  = myRs.getBoolean(i++);
		uu.googleid       = myRs.getString(i++);
		uu.google_email   = myRs.getString(i++);
		uu.username       = myRs.getString(i++);
		uu.login_token    = myRs.getBytes(i++);//Filled Value Required for User Interactivity Verification
		return uu;
	}
	static void DelUser(User u)
	throws SQLException
	{
		//This operation CANNOT BE UNDONE, so there should be a CONFIRMATION DIALOG before the user is allowed to do this.
		CallableStatement myStmt1 = c.prepareCall("{call DeleteLoggedInUser(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		myStmt1.execute();
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
		myStmt1.execute();
	}
	static void DelUserEvents(User u)
	throws SQLException
	{
		//This operation CANNOT BE UNDONE.
		CallableStatement myStmt1 = c.prepareCall("{call DeleteLoggedInUserEvents(?, ?)}"); int x = 1;
		myStmt1.setInt      (x++, u.id);
		myStmt1.setBytes    (x++, u.login_token);
		myStmt1.execute();
	}
	static boolean LoginSubFunction(User uu)
	throws SQLException
	{
		//This enables checking the database to check for a user existing - if not an account can be created.
		CallableStatement myStmt1 = c.prepareCall("{call LoginFunction(?)}"); int x = 1;
		myStmt1.setString   (x++, uu.googleid);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		if(!myRs.next()) return false; //If empty, user doesn't exist.
		return true;
	}
	static User Login(User u)
	throws SQLException
	{
		//the login function only needs the authenticated Google ID and username filled in.
		/** If the user doesn't exist it will be created and returned.*/
		if(!LoginSubFunction(u)){
			return NewUser(u);
		}
		CallableStatement myStmt1 = c.prepareCall("{call LoginFunction(?)}"); int x = 1;
		myStmt1.setString   (x++, u.googleid);
		myStmt1.execute();
		ResultSet myRs = myStmt1.getResultSet(); //To retrieve the table results.
		int i = 1;
		myRs.next();
		User uu           = new User();
		uu.id             = myRs.getInt(i++);
		uu.is_staffmember = myRs.getBoolean(i++);
		uu.is_instructor  = myRs.getBoolean(i++);
		uu.googleid       = myRs.getString(i++);
		uu.google_email   = myRs.getString(i++);
		uu.username       = myRs.getString(i++);
		uu.login_token    = myRs.getBytes(i++);//Filled Value Required for User Interactivity Verification
		return uu;
	}
	
	
	//###################################################################################################
	//###################################################################################################
	//###################################################################################################
	//###################################################################################################
	//###################################################################################################
	//###################################################################################################
	
	
	
	
	
	
	static User[]      AdminGetUsers()
	throws SQLException
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT users.* FROM users");
		ArrayList<User> list = new ArrayList<User>();
		while(myRs.next()){ int i = 1;
			User uu           = new User();
			uu.id             = myRs.getInt(i++);
			uu.is_staffmember = myRs.getBoolean(i++);
			uu.is_instructor  = myRs.getBoolean(i++);
			uu.googleid       = myRs.getString(i++);
			uu.google_email   = myRs.getString(i++);
			uu.username       = myRs.getString(i++);
			uu.login_token    = myRs.getBytes(i++);//Filled Value Required for User Interactivity Verification
			list.add(uu);}
		User[] result = new User[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		return result;
	}
	static void        AdminDelEventCat(EventCat ec)
	throws SQLException
	{
		ParamizedExecSQL("delete from rcdb.cats_event where id=?", ec.id);
	}
	static  void       AdminDelFitCat(Fitness fc)
	throws SQLException
	{
		ParamizedExecSQL("delete from rcdb.cats_fit where id=?", fc.id);
	}
	static void        AdminDelImprCat(Improvement ic)
	throws SQLException
	{
		ParamizedExecSQL("delete from rcdb.cats_impr where id=?", ic.id);
	}
	static void        AdminDelUser(User u)
	throws SQLException
	{
		//Provides an override method so that administrators can just outright delete a user.
		ParamizedExecSQL("delete from rcdb.users where id=?",u.id);
	}
	static EventCat    AdminAddEventCat(EventCat ec)
	throws SQLException
	{
		ParamizedExecSQL("INSERT INTO cats_event (id_fit, `Name`, Description, Tags) VALUES (?,?,?,?);",
			ec.id_fit,
			ec.name,
			ec.description,
			ec.tags);
		return ec;
	}
	static Fitness     AdminAddFitCat(Fitness fc)
	throws SQLException
	{
		ParamizedExecSQL("INSERT INTO cats_fit (`Name`, Description) VALUES (?,?);",
			fc.name,
			fc.description);
		return fc;
	}
	static Improvement AdminAddImprCat(Improvement ic)
	throws SQLException
	{
		ParamizedExecSQL("INSERT INTO cats_impr (id_fit, `Name`, Description) VALUES (?,?,?);",
			ic.id_fit,
			ic.name,
			ic.description);
		return ic;
	}
	static User        AdminEditUser(User uu)
	throws SQLException
	{
		ParamizedExecSQL("UPDATE users SET IsStaffMember = ?, IsInstructor = ? WHERE cats_event.id = ?;",
			uu.is_staffmember, 
			uu.is_instructor, 
			uu.id);
		return uu;
	}
	static EventCat    AdminEditEventCat(EventCat ec)
	throws SQLException
	{
		ParamizedExecSQL("UPDATE cats_event SET id_fit = ?, `Name`= ?, Description = ?, Tags = ? WHERE cats_event.id = ?;",
			ec.id_fit, 
			ec.name, 
			ec.description, 
			ec.tags, 
			ec.id);
		return ec;
	}
	static Fitness     AdminEditFitCat(Fitness fc)
	throws SQLException
	{
		ParamizedExecSQL("UPDATE cats_impr SET `Name`= ?, Description = ? WHERE cats_impr.id = ?;",
			fc.name, 
			fc.description, 
			fc.id);
		return fc;
	}
	static Improvement AdminEditImprCat(Improvement ic)
	throws SQLException
	{
		ParamizedExecSQL("UPDATE cats_impr SET id_fit = ?, `Name`= ?, Description = ? WHERE cats_impr.id = ?;",
			ic.id_fit, 
			ic.name, 
			ic.description, 
			ic.id);
		return ic;
	}
	static void        AdminDoMassNotify(String subject, String message)
	throws SQLException
	{
		User[] ua = AdminGetUsers();
		for(int i = ua.length-1; i >= 0; i--){
			NotifyGoogleUser(ua[i], subject, message);
		}
		return;
	}
	static User[]      AdminGetEvent_HasUsers_List(User u)
	throws SQLException
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT users.* FROM users, events_, userevents WHERE (userevents.id_user = ?);",u.id);
		ArrayList<User> list = new ArrayList<User>();
		while(myRs.next()){ int i = 1;
			User uu           = new User();
			uu.id             = myRs.getInt(i++);
			uu.is_staffmember = myRs.getBoolean(i++);
			uu.is_instructor  = myRs.getBoolean(i++);
			uu.googleid       = myRs.getString(i++);
			uu.google_email   = myRs.getString(i++);
			uu.username       = myRs.getString(i++);
			uu.login_token    = myRs.getBytes(i++);//Filled Value Required for User Interactivity Verification
			list.add(uu);}
		User[] result = new User[list.size()];
		for(int i = 0; i < list.size(); i++) result[i] = list.get(i);
		return result;
	}
	static DbEvent[]   AdminGetUser_HasEvents_List(DbEvent e)
	throws SQLException
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT events.* FROM users, events_, userevents WHERE (userevents.id_event = ?);",e.id);
		ArrayList<DbEvent> list = new ArrayList<DbEvent>();
		while(myRs.next()){ int i = 1;
			DbEvent ee          = new DbEvent();
			ee.id             = myRs.getInt(i++);
			ee.id_cat         = myRs.getInt(i++);
			ee.id_instructor  = myRs.getInt(i++);
			ee.name           = myRs.getString(i++);
			ee.description    = myRs.getString(i++);
			ee.when_day      = myRs.getString(i++);
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
		return result;
	}
	static void NotifyGoogleUser(User u, String subject, String message)
	{
		String to = u.google_email;
		String from = "reccntr@siu.edu";
		SendEmailNotification(to, from, subject, message);
	}
	static void CheckUserEvents_DoNotifications()
	throws SQLException
	{
		/**####################################################*/
		/** Uses email address to send notifications by email. */
		ResultSet rs = ParamizedExecSQL("select distinct userevents.* from users, userevents, events_ where userevents.notify = true and NotifyWhen < NOW();");
		UserEvent[] ue_array = TranslateUserEvents(rs);
		for(int ue = 0; ue < ue_array.length; ue++){
			ParamizedExecSQL("update userevents set userevents.notify = false where userevents.id = ?;",ue_array[ue].id);
			ResultSet rsu = ParamizedExecSQL("select distinct users.* where users.id = ?;",ue_array[ue].id_user);
			ResultSet rse = ParamizedExecSQL("select distinct events_.* where events_.id = ?;",ue_array[ue].id_event);
			User ux = TranslateUser(rsu);
			DbEvent ex = TranslateDbEvent(rse);
			String subject = "(Rec Center) REMINDER!! "+ex.name; 
			String message = "REMINDER!! \r\n"+ex.name+"\r\nIs about to proceed at The Rec Center.";
			String to = ux.google_email;
			String from = "reccntr@siu.edu";
			SendEmailNotification(to, from, subject, message);
		}
	}
	public static void SendEmailNotification(String to, String from, String subject, String message)
	{    
	   String host = "localhost";
	   Properties properties = System.getProperties();
	   properties.setProperty("mail.smtp.host", host);
	   Session session = Session.getDefaultInstance(properties);
	   try{
	      MimeMessage m = new MimeMessage(session);
	      m.setFrom(new InternetAddress(from));
	      m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	      m.setSubject(subject);
	      m.setText(message);
	      Transport.send(m);
	   }catch(MessagingException e){
	      e.printStackTrace();
	   }
	}
	public static void SaveGoals(UserGoal[] data)
	{
		//THE DOCS SAY: On android user.home and user.name are pointless.
		java.io.File path = new java.io.File(System.getProperty("user.dir"), "usergoals.bin");
		try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(path))){
	        write.writeInt(data.length);
	        for(int i = 0; i < data.length; i++) write.writeObject(data[i]);
	    }catch(NotSerializableException nse){
	    	throw new IllegalStateException("UserGoals not Serializable?!!", nse);
	    }catch(IOException eio){
	    	throw new IllegalStateException("Error Saving User Goals!!", eio);
	    }
	}
	public static UserGoal[] LoadGoals()
	{
		//THE DOCS SAY: On android user.home and user.name are pointless.
		java.io.File path = new java.io.File(System.getProperty("user.dir"), "usergoals.bin");
		try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path))){
	    	int sz = inFile.readInt();
	    	UserGoal[] data = new UserGoal[sz];
	    	for(int i = 0; i < sz; i++) data[i] = (UserGoal)inFile.readObject();
	        return data;
	    }catch(ClassNotFoundException cnfe){
	    	return null;
	    }catch(FileNotFoundException fnfe){
	    	return null;
	    }catch(IOException e){
	    	return null;
	    }
	}
	static User AdminFindInstructorUser(String find)
	throws SQLException
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT users.* FROM users WHERE (users.Username LIKE '%?%') LIMIT 1;",find);
		if(!myRs.next()) return null;
		int i = 1;
		User uu           = new User();
		uu.id             = myRs.getInt(i++);
		uu.is_staffmember = myRs.getBoolean(i++);
		uu.is_instructor  = myRs.getBoolean(i++);
		uu.googleid       = myRs.getString(i++);
		uu.google_email   = myRs.getString(i++);
		uu.username       = myRs.getString(i++);
		uu.login_token    = myRs.getBytes(i++);
		return uu;
	}
	static EventCat AdminFindEventCategory(String find)
	throws SQLException
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT cats_event.* FROM cats_event WHERE (cats_event.`Name` LIKE '%?%') LIMIT 1;",find);
		if(!myRs.next()) return null;
		int i = 1;
		EventCat ec       = new EventCat();
		ec.id             = myRs.getInt(i++);
		ec.id_fit         = myRs.getInt(i++);
		ec.name           = myRs.getString(i++);
		ec.description    = myRs.getString(i++);
		ec.tags           = myRs.getString(i++);
		return ec;
	}
	static int UpdateEventFromCalendar(DbEvent ev)
	throws SQLException //Returns [-1 for Delete, 0 for No Action, 1 for Insert, 2 for Update]
	{
		ResultSet myRs = ParamizedExecSQL("SELECT DISTINCT events_.* FROM events_ WHERE (events_.CalendarID = ?) LIMIT 1;", ev.calendar_id);
		if(myRs.next()){
			//If the record exists...
			myRs.previous();
			DbEvent record = DB.TranslateDbEvent(myRs);
			myRs.previous();
			if(record.compareEqual(ev)) return 0;
			//If it's different...
			if((ev.when_beg == null) && (ev.when_end == null)){
				//DELETED EVENT!!!
				ParamizedExecSQL("DELETE FROM events_ WHERE events_.CalendarID = ?;", ev.calendar_id);
				return -1;
			}
		}
		if((ev.when_beg != null) && (ev.when_end != null)){
			if(!myRs.next()){
				//Add the event.
				ParamizedExecSQL("INSERT INTO events_ (id_cat, id_instructor, `Name`, Description, WhenDay, WhenBeg, WhenEnd, CostCents, HtmlLink, CalendarID, Status, CalendarLastUpd) "+
								 "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);",
					ev.id_cat,
					ev.id_instructor,
					ev.name,
					ev.description,
					ev.when_day,
					ev.when_beg,
					ev.when_end,
					ev.cost_cents,
					ev.link,
					ev.calendar_id,
					ev.status,
					ev.last_updated);
				return 1;
			}
			ParamizedExecSQL("UPDATE events_ SET id_cat = ?, id_instructor = ?, `Name` = ?, Description = ?, WhenDay = ?, WhenBeg = ?, WhenEnd = ?, CostCents = ?, HtmlLink = ?, Status = ?, CalendarLastUpd = ? WHERE events_.CalendarID = ?;",
				ev.id_cat,
				ev.id_instructor,
				ev.name,
				ev.description,
				ev.when_day,
				ev.when_beg,
				ev.when_end,
				ev.cost_cents,
				ev.link,
				ev.status,
				ev.last_updated,
				ev.calendar_id);
			return 2;
		}else{
			return 0;
		}
	}
}







