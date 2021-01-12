package Operator;

public class test_doublons {

		public static void main(String args[])
			{
			CSVManager c = new CSVManager();
			c.vector.addElement(1);
			c.vector.addElement(1);
			c.vector.addElement(1);
			c.vector.addElement(1);
			c.vector.addElement(2);
			c.vector.addElement(2);
			c.vector.addElement(2);
			c.vector.addElement(2);
			c.vector.addElement(2);
			c.vector.addElement(2);
			c.vector.addElement(3);
			for(int i=0; i<c.vector.size(); i++)
				{
				System.out.print(c.vector.elementAt(i));
				}
			System.out.println();
			System.out.println("--Méthode--");
			c.supprimer_int();
			System.out.print("suppression faite");
/*			for(int i=0; i<c.vector.size(); i++)
				{
				System.out.print(c.vector.elementAt(i));
				}*/
			}
		}


