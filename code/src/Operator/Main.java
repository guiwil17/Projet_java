package Operator;

import java.util.Vector;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) 
		{
		CSVManager m = new CSVManager();
		m.elimine_number();
		m.supprimer_doublons();
       
            
        Graph g = new Graph();
		Vector<String> v = m.ListEvent();
		
		
		g.split(v);
		System.out.println("--------------- Affichage du graphe --------------\n");
		System.out.println(g.GraphToString());
		Vector<String> equation = g.get_equation(v);
		Iterator<String> itr = equation.iterator();
		int compteur = 0;  
		while ( itr.hasNext ()) {
			System.out.print ( "\n");
			System.out.println("\n--------------- Affichage de l'équation n°"+ compteur +" --------------\n");
			System.out.print ( itr.next ());
			compteur ++;
			}
		System.out.println();
	}
}
