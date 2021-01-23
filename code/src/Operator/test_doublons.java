package Operator;

public class test_doublons {

		public static void main(String args[])
			{  //Test la suporession de doublons dans un vector d'entiers
			CSVManager c = new CSVManager();
			LigneTableau l1 = new LigneTableau();
			LigneTableau l2 = new LigneTableau();
			LigneTableau l3 = new LigneTableau();
			LigneTableau l4 = new LigneTableau();
			LigneTableau l5 = new LigneTableau();
			c.vector.addElement(l1);
			c.vector.addElement(l2);
			c.vector.addElement(l3);
			c.vector.addElement(l4);
			c.vector.addElement(l5);
			for(int i=0; i<c.vector.size(); i++)
				{
				System.out.print(c.vector.elementAt(i).to_string());
				}
			System.out.println();
			System.out.println("Suppression des doublons");
			c.supprimer_doublons();
			System.out.println("suppression faite");
			for(int i=0; i<c.vector.size(); i++)
				{
				System.out.print(c.vector.elementAt(i).to_string());
				}
			}
		}


