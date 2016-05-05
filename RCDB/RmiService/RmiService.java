package RmiService;
import RCDB.*;

public interface RmiService {
	public String        TestItOut                  (String data);
	//###################################################################################################
	public EventCat[]    GetEventCategories         (EventCat cc); /** SEARCH FUNCTION; needs filled in data! */
	public Fitness[]     GetFitnessCategories       (Fitness f); /** SEARCH FUNCTION; needs filled in data! */
	public Improvement[] GetImprovementCategories   (Improvement ii); /** SEARCH FUNCTION; needs filled in data! */
	public DbEvent[]     GetEvents                  (); /** SEARCH FUNCTION; needs filled in data! */
	public DbEvent[]     GetEventsWhere             (DbEvent e); /** SEARCH FUNCTION; needs filled in data! */
	public UserEvent[]   GetUserEvents              (User u); /* RETRIEVAL FUNCTION */
	public User          NewUser                    (User u); //Returns the completed User Structure.
	public void          DelUser                    (User u);
	public UserEvent     NewUserEvent               (UserEvent e, User u);
	public void          DelUserEvent               (UserEvent e, User u);
	public void          DelUserEvents              (User u);
	public User          Login                      (User u);
	//###################################################################################################
	public User[]        AdminGetUsers              (String key); /* RETRIEVAL FUNCTION */
	public void          AdminDelEventCat           (String key, EventCat ec);
	public  void         AdminDelFitCat             (String key, Fitness fc);
	public void          AdminDelImprCat            (String key, Improvement ic);
	public void          AdminDelUser               (String key, User u);
	public EventCat      AdminAddEventCat           (String key, EventCat ec);
	public Fitness       AdminAddFitCat             (String key, Fitness fc);
	public Improvement   AdminAddImprCat            (String key, Improvement ic);
	public User          AdminEditUser              (String key, User uu);
	public EventCat      AdminEditEventCat          (String key, EventCat ec);
	public Fitness       AdminEditFitCat            (String key, Fitness fc);
	public Improvement   AdminEditImprCat           (String key, Improvement ic);
	public void          AdminDoMassNotify          (String key, String subject, String message);
	public User[]        AdminGetEvent_HasUsers_List(String key, User u); /** SEARCH FUNCTION */
	public DbEvent[]     AdminGetUser_HasEvents_List(String key, DbEvent e); /** SEARCH FUNCTION */
}
