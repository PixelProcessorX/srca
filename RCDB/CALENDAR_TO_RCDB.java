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

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class CALENDAR_TO_RCDB {
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
            Connection c = DB.ConnectToDatabase();
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
            System.out.println("CheckCycle();");
    	}while(true);
    }
    static void TranslateUpdatedEventsToDatabase(List<Event> items)
    throws SQLException
    {
    	for(Event event : items){
        	
    		
    		
    		
    		
        }
    }
}