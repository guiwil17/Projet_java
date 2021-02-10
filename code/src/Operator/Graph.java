package Operator;
import java.util.Vector;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Graph 
	{
	private MutableValueGraph<GraphNode, String> graphe;
	Graph() //Constructeur par defaut du graphe
		{
		graphe = ValueGraphBuilder.directed().build(); //On construit graphe sous la forme d'un graphe oriente
		}
	
	public void split(Vector<Vector<String>> v) //Peuplement du graphe
		{
		int taille = v.size()-1; //On ne parcours pas le dernier Ã©lÃ©ment (out of bound)
		for(int i=0; i<taille; i++) //On parcours le vecteur de "lignes" de l'excel
			{
			Vector<String> colonne_actuelle = v.elementAt(i); 
			Vector<String> colonne_suivante = v.elementAt(i+1);
			Vector<GraphNode> v_graph_suivant = new Vector<GraphNode>(); //Permet de stocker en memoire les noeuds du graphe pour la colonne suivante
			for(int j=0; j<colonne_suivante.size(); j++) //On peuple le vecteur v_graph_suivant
				{
				GraphNode noeud = new GraphNode(colonne_suivante.elementAt(j).split(";")[0], i*1000+j);
				v_graph_suivant.add(noeud);
				}
			for(int j=0; j<colonne_actuelle.size(); j++) //Pour chaque valeur de la colonne actuelle, on cree un noeud et on ajoute une liaison avec tous les noeuds de la colonne suivante
				{
				GraphNode a = new GraphNode(colonne_actuelle.elementAt(j).split(";")[0], i*1000+j);
				for(int k=0; k<v_graph_suivant.size(); k++)
					{
					graphe.putEdgeValue(a, v_graph_suivant.elementAt(k), colonne_actuelle.elementAt(j).split(";")[1]);
					}
				}
			}
		}

	public Vector<String> get_equation(Vector<Vector<String>> v) //Retourne un Vector de string qui modelise les equations, chaque String du vector est une equation 
		{
		String equation = "";
		Vector <String> membre = new Vector<String>(); // Vector qui permet de savoir les evenements qui se sont déjà passsé dans l'equation afin d'éviter les repetitions
		Vector <String> tab_equation = new Vector<String>(); //Vector qui permet de stocker les equations po
		
		
		// Initialisation de l'equation 
		int compteur = 0;
		for(int i=0; i < v.elementAt(0).size()-1;i++)//On recupere le premier vector de String qui correspond à l'état initial du système
		{
			String[] element0 = v.elementAt(0).elementAt(i).split(";"); 
			equation+="(In, "+element0[0]+", nct) * ";	//Il n'a pas de contrainte temporelle avant lui
			String elm = v.elementAt(0).elementAt(i).split(";").toString();
			compteur ++;
		}
		String[] element0 = v.elementAt(0).elementAt(compteur).split(";"); //On recupere le dernier element du vector sans lui mettre le symbole * à la fin pour faire correspondre l'affichage dans la console 
		equation+="(In, "+element0[0]+", nct) ";
		
		for (int j=0; j < v.size()-1; j++ ) {	//On parcourt le premier vector qui contient les differents vector de string
			 			
			
			for (int w=0; w < v.elementAt(j).size(); w++ ) { //On parcourt tous les elements du vector j 
				String[] element = v.elementAt(j).elementAt(w).split(";");
				
				membre.add(element[0]);
				
				
				for(int i=0; i<v.elementAt(j+1).size(); i++) //On parcourt tous les elements du vector j+1 
					{
				
					
					String[] element2 = v.elementAt(j+1).elementAt(i).split(";");
			
					
					//On verifie si m'element existe deja dans le vecteur, si c'est le cas on reinitialise l'equation et on ajoute celle qu'on a cree dans le vecteur de stockage des equations
					if(membre.indexOf(element2[0]) != -1) {
						tab_equation.add(equation);
						equation = "(In, "+element[0]+", nct) ";	
						membre.clear();
						membre.add(element[0]);
						equation+="*\n("+element[0]+", "+element2[0]+", "+element[1]+") ";
					}
					// Si il existe pas, on ajoute une nouvelle condition à l'equation
					else {
						equation+="*\n("+element[0]+", "+element2[0]+", "+element[1]+") "; //On ajoute le string qui contient le predecesseur, l'element cible, et la contrainte temporelle qui lie les deux 
					}
					}
				
			}
		}
		return tab_equation;
		
		}
	
	public String GraphToString() //Retourne un string qui modelise un graphe sous la forme <(sommet1) -> (sommet2)>=valeur de l'arc
		{
		String[] temp = graphe.toString().split(":");

		//On remplace les , par des retour chariot pour plus de clarte
		String tableau = temp[4].replaceAll(", ","\n");
		
		//On supprime les sous-chaines " {" et "}" au debut et a la fin de la chaine
		tableau = tableau.substring(2); 
		tableau = tableau.substring(0, tableau.length()-1);
		
		return tableau; 
		}
	}
