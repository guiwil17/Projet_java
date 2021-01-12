package Operator;

import java.util.Vector;

public class test_suppression 
	{
	public static void main(String[] args)
		{
		CSVManager c = new CSVManager();
		java.util.Date d = new java.util.Date();
		LigneTableau l1 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, true, d);
		LigneTableau l2 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, true, d);
		LigneTableau l3 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, true, d);
		LigneTableau l4 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, true, d);
		LigneTableau l5 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, false, d);
		c.vector.add(l1);
		c.vector.add(l2);
		c.vector.add(l3);
		c.vector.add(l4);
		c.vector.add(l5);
		System.out.println(c.vector.size());
		System.out.println("a");
		c.supprimer_doublons();
		System.out.println(c.vector.size());
		}
	}
