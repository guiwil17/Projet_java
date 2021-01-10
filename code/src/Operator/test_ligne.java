package Operator;

public class test_ligne 
	{
	public static void main(String args[])
		{
		java.util.Date d1;
		d1 = new java.util.Date();
		LigneTableau l1;
		l1 = new LigneTableau(true, true, true, true, true, true, true, true, true, true, true, true, true, true, d1);
		LigneTableau l2;
		l2 = new LigneTableau(false, true, true, true, true, true, true, true, true, true, true, true, true, true, d1);
		System.out.println(l1.is_equal_to(l2));
		}
	}
