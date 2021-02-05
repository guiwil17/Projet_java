package Operator;

import com.google.common.base.Objects;

public class GraphNode //Sommet du graphe, identifié par un nom et un id
	{
	private String name;
	private Integer id;

	GraphNode(String name, Integer id) //Constructeur à partir des deux paramètres
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

//Permet de différencier deux GraphNode, utilisée par la classe MutableValueGraph	  
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

//Utilisée par la classe MutableValueGraph	  

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
