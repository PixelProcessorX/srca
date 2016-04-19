
public class DbEvent implements ToString {
	public int id;
	public int id_cat;
	public int id_instructor;
	public String name;
	public String description;
	public String when_days;
	public java.sql.Timestamp when_beg;
	public java.sql.Timestamp when_end;
	public int cost_cents;
	public String link;
	public String calendar_id;
	public String status;
	public java.sql.Timestamp last_updated;
	//For Search Functionality
	public java.sql.Timestamp _when_beg_stt;
	public java.sql.Timestamp _when_beg_end;
	public java.sql.Timestamp _when_end_stt;
	public java.sql.Timestamp _when_end_end;
	public int _cost_cents_min;
	public int _cost_cents_max;
	public int _cat_fit;
	public int _cat_impr;
	//===========================================================================
	public String toString(){
		return (Integer.toString(id) + " " + 
				Integer.toString(id_cat) + " " + 
				Integer.toString(id_instructor) + " " + 
				calendar_id + " " + 
				name + " " + 
				description + " " + 
				when_days + " " + 
				when_beg.toString() + " to " + 
				when_end.toString() + " " + 
				Integer.toString(cost_cents) + " Cents; Event is: " +
				status);
	}
	DbEvent(){
		id = -1;
		id_cat = -1;
		id_instructor = -1;
		name = "NULL";
		description = "NULL";
		when_days = "NULL";
		when_beg = null;
		when_end = null;
		cost_cents = -1;
		_when_beg_stt = java.sql.Timestamp.valueOf("1900-01-01 00:00:00.000000");
		_when_beg_end = java.sql.Timestamp.valueOf("9999-01-01 00:00:00.000000");
		_when_end_stt = java.sql.Timestamp.valueOf("1900-01-01 00:00:00.000000");
		_when_end_end = java.sql.Timestamp.valueOf("9999-01-01 00:00:00.000000");
		_cost_cents_min = 0;
		_cost_cents_max = 99999999;
		_cat_fit = -1;
		_cat_impr = -1;
		last_updated = null;
	}
}
