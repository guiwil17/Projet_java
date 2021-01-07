package Operator;

public class LigneTableau 
	{
	private boolean box_conveyor;
	private boolean part_conveyor;
	private boolean grab;
	private boolean c_plus;
	private boolean auto;
	private boolean manual;
	private boolean emergency_stop;
	private boolean reset_button;
	private boolean start;
	private boolean stop;
	private boolean part_at_place;
	private boolean box_at_place;
	private boolean detected;
	private boolean c_limit;
	private java.util.Date date;

	
	public void set_box_conveyor(boolean b)
		{
		box_conveyor = b;
		}
	
	public void set_part_conveyor(boolean b)
		{
		part_conveyor = b;
		}
	
	public void set_grab(boolean b)
		{
		grab = b;
		}
	
	public void set_c_plus(boolean b)
		{
		c_plus = b;
		}
	
	public void set_auto(boolean b)
		{
		auto = b;
		}
	
	public void set_manual(boolean b)
		{
		manual = b;
		}
	
	public void set_emergency_stop(boolean b)
		{
		emergency_stop = b;
		}
	
	public void set_reset_button(boolean b)
		{
		reset_button = b;
		}
	
	public void set_start(boolean b)
		{
		start = b;
		}
	
	public void set_stop(boolean b)
		{
		stop = b;
		}

	public void set_part_at_place(boolean b)
		{
		part_at_place = b;
		}
	
	public void set_box_at_place(boolean b)
		{
		box_at_place = b;
		}
	
	public void set_detected(boolean b)
		{
		detected = b;
		}
	
	public void set_c_limit(boolean b)
		{	
		c_limit = b;
		}
	
	public void set_date(java.util.Date d)
		{
		date = d;
		}
	

	}
