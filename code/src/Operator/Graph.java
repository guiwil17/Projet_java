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
		int taille = v.size()-1; //On ne parcours pas le dernier élément (out of bound)
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

	public Vector<String> get_equation(Vector<String> v) //Retourne un string qui modelise l'equation
		{
		String equation = "";
		String[] element0 = v.elementAt(0).split(";"); //On recupere le premier element du vector
		equation+="(In, "+element0[0]+", nct) ";	//Il n'a pas de contrainte temporelle avant lui
		Vector <String> membre = new Vector<String>();
		Vector <String> tab_equation = new Vector<String>();
		String elm = v.elementAt(0).split(";").toString();
		membre.add(elm);
		for(int i=0; i<v.size()-1; i++) //On parcours tous les elements sauf le dernier
			{
			
			String[] element = v.elementAt(i).split(";");
			String[] element2 = v.elementAt(i+1).split(";");
			membre.add(element[0]);
			if(membre.indexOf(element2[0]) == -1) {
				equation+="*\n("+element[0]+", "+element2[0]+", "+element[1]+") ";
			}
			else {
				tab_equation.add(equation);
				equation = "(In, "+element[0]+", nct) ";	
				membre.clear();
			}			
			 //On ajoute le string qui contient le predecesseur, l'element cible, et la contrainte temporelle qui lie les deux 
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
