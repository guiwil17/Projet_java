package Operator;

import java.util.Vector;

public class Main {
	public static void main(String[] args) 
		{
		CSVManager m = new CSVManager();
		m.supprimer_doublons();
        m.elimine_number();
            
        Graph g = new Graph();
		Vector<String> v = m.ListEvent();
		
		g.split(v);
		System.out.println("--------------- Affichage du graphe --------------\n");
		System.out.println(g.GraphToString());
		System.out.println("\n--------------- Affichage de l'équation --------------\n");
		System.out.println(g.get_equation(v));

	}
}
