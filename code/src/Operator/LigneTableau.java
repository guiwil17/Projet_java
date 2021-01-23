package Operator;
import java.util.Date;

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
	private long date;

	LigneTableau(){
		this.box_conveyor = false;
		this.part_conveyor = false;
		this.grab = false;
		this.c_plus = false;
		this.auto = false;
		this.manual = false;
		this.emergency_stop = false;
		this.reset_button = false;
		this.start = false;
		this.stop = false;
		this.part_at_place = false;
		this.box_at_place = false;
		this.detected = false;
		this.c_limit = false;
		this.date =0;
	}
  
	// Constructeur de l'objet LigneTableau, à partir des paramètres CSV sans les réels et entiers
	LigneTableau(boolean box_conveyor, boolean part_conveyor, boolean grab, boolean c_plus, boolean auto, boolean manual, boolean emergency_stop, boolean reset_button, boolean start, boolean stop, boolean part_at_place, boolean box_at_place, boolean detected, boolean c_limit, long date)
		{
		this.box_conveyor = box_conveyor;
		this.part_conveyor = part_conveyor;
		this.grab = grab;
		this.c_plus = c_plus;
		this.auto = auto;
		this.manual = manual;
		this.emergency_stop = emergency_stop;
		this.reset_button = reset_button;
		this.start = start;
		this.stop = stop;
		this.part_at_place = part_at_place;
		this.box_at_place = box_at_place;
		this.detected = detected;
		this.c_limit = c_limit;
		this.date = date;
		}
	// Met les paramètres sous forme de string
	public String to_string()
		{
		String s = box_conveyor+" - "+part_conveyor+" - "+grab+" - "+c_plus+" - "+auto+" - "+manual+" - "+emergency_stop+" - "+reset_button+" - "+start+" - "+stop+" - "+part_at_place+" - "+box_at_place+" - "+detected+" - "+c_limit+" - "+date+"\n";
		return s;
		}
	
	public boolean is_equal_to(LigneTableau l2)
		{
		boolean b = false;
		if(box_conveyor == l2.get_box_conveyor() && part_conveyor == l2.get_part_conveyor() && grab == l2.get_grab() && c_plus == l2.get_c_plus() && auto == l2.get_auto() && manual == l2.get_manual() && emergency_stop == l2.get_emergency_stop() && reset_button == l2.get_reset_button() && start == l2.get_start() && stop == l2.get_stop() && part_at_place == l2.get_part_at_place() && box_at_place == l2.get_box_at_place() && detected == l2.get_detected() && c_limit == l2.get_c_limit())
			{
			b = true;
			}
		return b;
		}
	
	// Setteurs des param?tres de chaque ligne du CSV	

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
	
	public void set_date(long d)
		{
		date = d;
		}
	
// Getteurs des param?tres de chaque ligne du CSV

	
	public boolean get_box_conveyor()
		{
		return box_conveyor;
		}
	
	public boolean get_part_conveyor()
		{
		return part_conveyor;
		}
	
	public boolean get_grab()
		{
		return grab;
		}
	
	public boolean get_c_plus()
		{
		return c_plus;
		}
	
	public boolean get_auto()
		{
		return auto;
		}
	
	public boolean get_manual()
		{
		return manual;
		}
	
	public boolean get_emergency_stop()
		{
		return emergency_stop;
		}
	
	public boolean get_reset_button()
		{
		return reset_button;
		}
	
	public boolean get_start()
		{
		return start;
		}
	
	public boolean get_stop()
		{
		return stop;
		}
	
	public boolean get_part_at_place()
		{
		return part_at_place;
		}
	
	public boolean get_box_at_place()
		{
		return box_at_place;
		}
	
	public boolean get_detected()
		{
		return detected;
		}
	
	public boolean get_c_limit()
		{	
		return c_limit;
		}
	
	public long get_date()
		{
		return date;
		}
	}