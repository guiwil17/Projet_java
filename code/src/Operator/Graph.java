package Operator;
import java.util.Vector;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Graph 
	{
	private MutableValueGraph<GraphNode, String> graphe;
	Graph()
		{
		graphe = ValueGraphBuilder.directed().build();
		}
	
	public void split(Vector<String> v)
		{
		int taille = v.size()-1; //-1 pour ne pas prendre le dernier élément
		for(int i=0; i<taille; i++)
			{
			String[] element = v.elementAt(i).split(";");
			String[] element2 = v.elementAt(i+1).split(";");
			GraphNode a = new GraphNode(element[0], i);
			GraphNode b = new GraphNode(element2[0], i+1);
			graphe.putEdgeValue(a, b, element[1]);
			}
		// Debug
		System.out.println(graphe.toString());
		}

	public String toString()
		{
		//TODO : Modifier le string graphe.toString() pour afficher seulement la partie intéressante avec les graphes
		}
	}
