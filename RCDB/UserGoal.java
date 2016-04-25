import java.io.Serializable;

public class UserGoal implements Serializable {
	public String what, how, how_much, by_when;
	public boolean is_achieved;
	public static final long serialVersionUID = 0;
}
