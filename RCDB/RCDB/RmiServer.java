package RCDB;
import java.io.IOException;
import java.net.Socket;

import RmiService.RmiService;
import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

public class RmiServer implements RmiService
{
	private static int serverPort = 7777;
	public void ListenForClients()
	{
		try{
	        CallHandler callHandler = new CallHandler();
	        callHandler.registerGlobal(RmiService.class, this);
	        Server server = new Server();
	        server.bind(serverPort, callHandler);
	        server.addServerListener(new IServerListener(){
	            @Override public void clientDisconnected(Socket socket){
	                System.out.println("DIS: " + socket.getInetAddress());}
				@Override public void clientConnected(Socket socket){
				    System.out.println("CON: " + socket.getInetAddress());}
				});
			System.out.println("Server Listening");
	    }catch(LipeRMIException | IOException e){
	        e.printStackTrace();
	    }
	}
	@Override public String TestItOut(String data)
	{
	    System.out.println("TestItOut(); called.");
	    return "Your data: " + data;
	}
	@Override
	public EventCat[] GetEventCategories(EventCat cc) {
		try{
			return DB.GetEventCategories(cc);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Fitness[] GetFitnessCategories(Fitness f) {
		try{
			return DB.GetFitnessCategories(f);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Improvement[] GetImprovementCategories(Improvement ii) {
		try{
			return DB.GetImprovementCategories(ii);
		}catch(Exception e){}
		return null;
	}
	@Override
	public DbEvent[] GetEvents() {
		try{
			return DB.GetEvents();
		}catch(Exception e){}
		return null;
	}
	@Override
	public DbEvent[] GetEventsWhere(DbEvent e) {
		try{
			return DB.GetEventsWhere(e);
		}catch(Exception ex){}
		return null;
	}
	@Override
	public UserEvent[] GetUserEvents(User u) {
		try{
			return DB.GetUserEvents(u);
		}catch(Exception e){}
		return null;
	}
	@Override
	public User NewUser(User u) {
		try{
			return DB.NewUser(u);
		}catch(Exception e){}
		return null;
	}
	@Override
	public void DelUser(User u) {
		try{
			DB.DelUser(u);
		}catch(Exception e){}
		return;
	}
	@Override
	public UserEvent NewUserEvent(UserEvent e, User u) {
		try{
			return DB.NewUserEvent(e, u);
		}catch(Exception ex){}
		return null;
	}
	@Override
	public void DelUserEvent(UserEvent e, User u) {
		try{
			DelUserEvent(e, u);
		}catch(Exception ex){}
		return;
	}
	@Override
	public void DelUserEvents(User u) {
		try{
			DelUserEvents(u);
		}catch(Exception e){}
		return;
	}
	@Override
	public User Login(User u) {
		try{
			return Login(u);
		}catch(Exception e){}
		return null;
	}
	//###################################################################################################
	//###################################################################################################
	//###################################################################################################
	@Override
	public User[] AdminGetUsers(String key) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminGetUsers();
		}catch(Exception e){}
		return null;
	}
	@Override
	public void AdminDelEventCat(String key, EventCat ec) {
		if(!DB.CompareToAdminKey(key)) return;
		try{
			DB.AdminDelEventCat(ec);
		}catch(Exception e){}
		return;
	}
	@Override
	public void AdminDelFitCat(String key, Fitness fc) {
		if(!DB.CompareToAdminKey(key)) return;
		try{
			DB.AdminDelFitCat(fc);
		}catch(Exception e){}
		return;
	}
	@Override
	public void AdminDelImprCat(String key, Improvement ic) {
		if(!DB.CompareToAdminKey(key)) return;
		try{
			DB.AdminDelImprCat(ic);
		}catch(Exception e){}
		return;
	}
	@Override
	public void AdminDelUser(String key, User u) {
		if(!DB.CompareToAdminKey(key)) return;
		try{
			DB.AdminDelUser(u);
		}catch(Exception e){}
		return;
	}
	@Override
	public EventCat AdminAddEventCat(String key, EventCat ec) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminAddEventCat(ec);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Fitness AdminAddFitCat(String key, Fitness fc) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminAddFitCat(fc);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Improvement AdminAddImprCat(String key, Improvement ic) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminAddImprCat(ic);
		}catch(Exception e){}
		return null;
	}
	@Override
	public User AdminEditUser(String key, User uu) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminEditUser(uu);
		}catch(Exception e){}
		return null;
	}
	@Override
	public EventCat AdminEditEventCat(String key, EventCat ec) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminEditEventCat(ec);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Fitness AdminEditFitCat(String key, Fitness fc) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminEditFitCat(fc);
		}catch(Exception e){}
		return null;
	}
	@Override
	public Improvement AdminEditImprCat(String key, Improvement ic) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminEditImprCat(ic);
		}catch(Exception e){}
		return null;
	}
	@Override
	public void AdminDoMassNotify(String key, String subject, String message) {
		if(!DB.CompareToAdminKey(key)) return;
		try{
			DB.AdminDoMassNotify(subject, message);
		}catch(Exception e){}
		return;
	}
	@Override
	public User[] AdminGetEvent_HasUsers_List(String key, User u) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			return DB.AdminGetEvent_HasUsers_List(u);
		}catch(Exception e){}
		return null;
	}
	@Override
	public DbEvent[] AdminGetUser_HasEvents_List(String key, DbEvent e) {
		if(!DB.CompareToAdminKey(key)) return null;
		try{
			DB.AdminGetUser_HasEvents_List(e);
		}catch(Exception ex){}
		return null;
	}
}
