package Operator;
import java.util.Vector;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Graph 
	{
	private MutableValueGraph<GraphNode, String> graphe;
	Graph() //Constructeur par défaut du graphe
		{
		graphe = ValueGraphBuilder.directed().build(); //On construit graphe sous la forme d'un graphe orienté
		}
	
	public void split(Vector<String> v) //Peuplement du graphe
		{
		int taille = v.size()-1; //-1 pour ne pas prendre le dernier élément
		for(int i=0; i<taille; i++) //On parcours tous les éléments sauf le dernier
			{
			//element[0] contient le nom du sommet, element[1] contient la contraite temporelle, même chose pour element2
			String[] element = v.elementAt(i).split(";"); 
			String[] element2 = v.elementAt(i+1).split(";");
			
			//On crée un sommet de graphe avec sa valeur et un id pour chaque élément
			GraphNode a = new GraphNode(element[0], i); 
			GraphNode b = new GraphNode(element2[0], i+1);
			
			//On ajoute les sommets au graphe et la contrainte temporelle entre les deux, qui sera la valeur de l'arc
			graphe.putEdgeValue(a, b, element[1]);
			}
		}

	public String get_equation(Vector<String> v) //Retourne un string qui modélise l'équation
		{
		String equation = "";
		String[] element0 = v.elementAt(0).split(";"); //On récupère le premier élément du vector
		equation+="(In, "+element0[0]+", nct) ";	//Il n'a pas de contrainte temporelle avant lui
		
		for(int i=0; i<v.size()-1; i++) //On parcours tous les éléments sauf le dernier
			{
			String[] element = v.elementAt(i).split(";");
			String[] element2 = v.elementAt(i+1).split(";");
			equation+="*\n("+element[0]+", "+element2[0]+", "+element[1]+") "; //On ajoute le string qui contient le prédecesseur, l'élément cible, et la contrainte temporelle qui lie les deux 
			}
		return equation;
		}
	
	public String GraphToString() //Retourne un string qui modélise un graphe sous la forme <(sommet1) -> (sommet2)>=valeur de l'arc
		{
		String[] temp = graphe.toString().split(":");

		//On remplace les , par des retour chariot pour plus de clareté
		String tableau = temp[4].replaceAll(", ","\n");
		
		//On supprime les sous-chaînes " {" et "}" au début et à la fin de la chaîne
		tableau = tableau.substring(2); 
		tableau = tableau.substring(0, tableau.length()-1);
		
		return tableau; 
		}
	}
