package RCDB;
import java.sql.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

public class CALENDAR_TO_RCDB {
	
	public static String[] monthname = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public static String[] dayname = {"Monday", "Tuesday", "Wedneday", "Thursday", "Friday", "Saturday", "Sunday"};
	public static String[] daypostfix = {"th","st","nd","rd","th","th","th","th","th","th",
										 "th","th","th","th","th","th","th","th","th","th",
										 "th","st","nd","rd","th","th","th","th","th","th",
										 "th","st","nd","rd","th","th","th","th","th","th"};
	
	public static int     UPDATE_CHECK_TIME = 1000;//30000
	
	private static final String         CLIENT_SECRET_PATH = "libs/client_secret.json";              /**CHANGED TEMPORARILY TO A FOLDER INSIDE THE DEVELOPMENT FOLDER was user.home*/
	private static final java.io.File       DATA_STORE_DIR = new java.io.File(System.getProperty("user.dir"), ".credentials/calendar-java-quickstart.json");//System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
	private static final String           APPLICATION_NAME = "Google Calendar API Java Quickstart";  /** Application name. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;                                          /** Global instance of the {@link FileDataStoreFactory}. */
    private static final JsonFactory          JSON_FACTORY = JacksonFactory.getDefaultInstance();    /** Global instance of the JSON factory. */
    private static HttpTransport            HTTP_TRANSPORT;                                          /** Global instance of the HTTP transport. */
    private static final List<String>               SCOPES = Arrays.asList(CalendarScopes.CALENDAR); /** Global instance of the scopes required by this quickstart. */
    private static Credential credential;
    private static String refresh_token;
    /** If modifying these scopes, delete your previously saved credentials at ~/.credentials/calendar-java-quickstart.json */
    
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    public static DateTime Now()
    {
    	java.util.Calendar c = java.util.Calendar.getInstance();
    	DateTime d = new DateTime(c.getTime());
    	return d;
    }
    public static String StripNullStrings(String s)
    {
    	if(s == null) return "";
    	return s;
    }
    public static java.sql.Timestamp GoogleDateTimeToSqlTimestamp(DateTime d)
    {
    	if(d == null) return null;
    	String rfc3339v = d.toStringRfc3339();//2002-10-02T10:00:00
    	       rfc3339v = (rfc3339v.substring(0, 10)+' '+rfc3339v.substring(11,18));//meets format yyyy-[m]m-[d]d hh:mm:ss[.f...]
    	return java.sql.Timestamp.valueOf(rfc3339v);
    }
    public static String SqlTimestampToDateString(java.sql.Timestamp d)
    {
    	long timestamp = d.getTime();
		java.util.Calendar cal = java.util.Calendar.getInstance(); cal.setTimeInMillis(timestamp);
		String s = dayname[cal.get(java.util.Calendar.DAY_OF_WEEK)-1]+", "+monthname[cal.get(java.util.Calendar.MONTH)]+" "+cal.get(java.util.Calendar.DATE)+daypostfix[cal.get(java.util.Calendar.DATE)];
		return s;
    }
    public static Credential authorize()
    throws Exception
    {
        InputStream                    in = CALENDAR_TO_RCDB.class.getResourceAsStream(CLIENT_SECRET_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in)); // Load client secrets.
        GoogleAuthorizationCodeFlow  flow = new GoogleAuthorizationCodeFlow.Builder
        									(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
							                .setDataStoreFactory(DATA_STORE_FACTORY)
							                .setAccessType("offline")
							                .build();// Build flow and trigger user authorization request.
        Credential                      c = new AuthorizationCodeInstalledApp
        		                            (flow, new LocalServerReceiver())
                                            .authorize("user");
        return c;
    }
    public static com.google.api.services.calendar.Calendar getCalendarService() 
    throws Exception 
    {
        credential = authorize();
        refresh_token = credential.getRefreshToken();
        credential.setRefreshToken(refresh_token);
        return  new com.google.api.services.calendar.Calendar.Builder
                	(HTTP_TRANSPORT, JSON_FACTORY, credential)
                	.setApplicationName(APPLICATION_NAME)
                	.build();
    }
    public static void main(String[] args)
    throws Exception
    {
    	try{
            DB.ConnectToDatabase();
        }catch(Exception e){
        	throw new IllegalStateException("Could not connect to database!", e);
        }
		com.google.api.services.calendar.Calendar service;
    	try{
        	service = getCalendarService();
	    }catch(Exception e){
	    	throw new IllegalStateException("Could not connect to Google Calendar!", e);
	    }
    	/**####################################################################################*/
        /** THIS IS AN UPDATE LOOP THAT UPDATES THE CALENDAR WITH NEW EVENTS EVENT 30 SECONDS. */
    	/**####################################################################################*/
    	System.out.println("Running Check Cycles...");
    	int line_counter = 0;
    	DateTime old_now = Now();
    	do{
    		Thread.sleep(UPDATE_CHECK_TIME);//30 seconds per update check.
    		DateTime new_now = Now();//Save the current time.
    		Events events = service.events().list("primary")
                .setUpdatedMin(old_now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
    		old_now = new_now;//Set the time we performed the check for the next one.
    		List<Event> items = events.getItems();
    		if(items.size() > 0){
    			TranslateUpdatedEventsToDatabase(items);
    		}
    		DB.CheckUserEvents_DoNotifications();//Check the entire database for notifications to do!!
            credential.setRefreshToken(refresh_token);
            credential.refreshToken();
            System.out.print("%"); line_counter++;
            if(line_counter == 128){
            	System.out.println("");
            	line_counter = 0;
            }
    	}while(true);
    }
    static void TranslateUpdatedEventsToDatabase(List<Event> items)
    throws SQLException
    {
    	for(Event event : items){
    		DbEvent ee        = new DbEvent();
			ee.name           = StripNullStrings(event.getSummary());
			ee.description    = StripNullStrings(event.getDescription());
			ee.when_beg       = ((event.getStart()!=null)?GoogleDateTimeToSqlTimestamp(event.getStart().getDateTime()):null);
			ee.when_end       = ((event.getStart()!=null)?GoogleDateTimeToSqlTimestamp(event.getEnd().getDateTime()):null);
			ee.link           = StripNullStrings(event.getHtmlLink());
			ee.calendar_id    = StripNullStrings(event.getId());
			ee.status         = event.getStatus();
			ee.last_updated   = GoogleDateTimeToSqlTimestamp(event.getUpdated());
			/** Automatic days of week handler!! */
			ee.when_day       = ((event.getStart()!=null)?SqlTimestampToDateString(GoogleDateTimeToSqlTimestamp(event.getStart().getDateTime())):"");
			/** Automatic fields based on description text!! */
			//At the end of the description, place the following:
			//Instructor[KeyText]	to set Instructor (uses Google DisplayName in Database Entries)
			//Category[KeyText]		to set Event Category (uses Database Entries)
			//Cost[$123.45]			to set Cost amount (cents cannot be omitted)
			String s = ee.description;
			Pattern p = Pattern.compile("Instructor:\\[[a-zA-Z]*?\\]");
			Pattern q = Pattern.compile("Category:\\[[a-zA-Z]*?\\]");
			Pattern r = Pattern.compile("Cost:\\[\\$[0-9]*?\\.[0-9]*?\\]");
			if(r.matcher(s).find()){
				String s0 = s.substring(p.matcher(s).start(), p.matcher(s).end());
				ee.cost_cents = (int)(Double.valueOf(s0.substring(7,s0.length()-1)) * 100.0);
			}
			if(p.matcher(s).find())
			{
				String s0 = s.substring(p.matcher(s).start(), p.matcher(s).end());
				s0 = s0.substring(12,s0.length()-1);
				User uu = DB.AdminFindInstructorUser(s0);
				ee.id_instructor  = uu.id;
			}
			if(q.matcher(s).find())
			{
				String s0 = s.substring(p.matcher(s).start(), p.matcher(s).end());
				s0 = s0.substring(10,s0.length()-1);
				EventCat ec = DB.AdminFindEventCategory(s0);
				ee.id_cat         = ec.id;
			}
			switch(DB.UpdateEventFromCalendar(ee)){
				case -1: System.out.println("\n[DELETE ITEM]:  \""+ee.calendar_id+"\""); break;
				case  1: System.out.println("\nCreate:         \""+ee.calendar_id+"\",  \""+ee.name+"\""); break;
				case  2: System.out.println("\nUPDATE:         \""+ee.calendar_id+"\",  \""+ee.name+"\""); break;
			}
			
        }
    }
}