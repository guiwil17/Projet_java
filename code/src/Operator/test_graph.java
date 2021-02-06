package Operator;
import java.util.Vector;



public class test_graph 
	{
	public static void main(String[] args) 
		{
		Graph g = new Graph();
		Vector<String> v=new Vector<String>();
		v.add("A;1µs;");
		v.add("B;5µs;");
		v.add("C;10µs;");
		v.add("D;50µs;");
		g.split(v);
		System.out.println(g.GraphToString());
		System.out.println(g.get_equation(v));
		}
	}
