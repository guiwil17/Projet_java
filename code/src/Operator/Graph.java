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
		int taille = v.size();
		for(int i=0; i<taille; i++)
			{
			String[] element = v.elementAt(i).split(";");
			GraphNode a = new GraphNode(element[0]);
			GraphNode b = new GraphNode("test");
			graphe.putEdgeValue(a, b, element[1]);
			}
		System.out.println(graphe.toString());
		}
	}
