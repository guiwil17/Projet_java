package Operator;

import com.google.common.base.Objects;

public class GraphNode //Sommet du graphe, identifie par un nom et un id
	{
	private String name;
	private Integer id;

	GraphNode(String name, Integer id) //Constructeur a partir des deux parametres
		{
		this.name = name;
		this.id = id;
	    }

//Getteurs
	
	  public String name() 
	  	{
	    return name;
	  	}

	  public Integer id() 
	  	{
	    return id;
	  	}

//Permet de differencier deux GraphNode, utilisee par la classe MutableValueGraph	  
	  @Override
	  public boolean equals(Object other) 
	  	{
	    if (other instanceof GraphNode) 
	    	{
	    	GraphNode that = (GraphNode) other;
	    	return this.name.equals(that.name)&&this.id.equals(that.id);
	    	}
	    return false;
	  	}

//Utilisee par la classe MutableValueGraph	  

	  @Override
	  public int hashCode() 
	  	{
		return Objects.hashCode(name, id);
	  	}

//Renvoie le nom du GraphNode
	  @Override
	  public String toString() 
	  	{
	    return "(" + name + ")";
	  	}
	}
