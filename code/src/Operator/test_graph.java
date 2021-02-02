package Operator;
import java.util.Vector;



public class test_graph 
	{
	public static void main(String[] args) 
		{
		Graph g = new Graph();
		Vector<String> v=new Vector<String>();
		v.add("FE_box_at_place;1µs;");
		v.add("RE_box_at_place;5µs;");
		v.add("FE_box_at_place;10µs;");
		v.add("RE_box_at_place;50µs;");
		g.split(v);
		}
	}
