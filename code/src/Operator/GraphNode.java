package Operator;

import com.google.common.base.Objects;

public class GraphNode 
	{
	private String name;

	GraphNode(String name) 
		{
		this.name = name;
	    }

	  public String name() 
	  	{
	    return name;
	  	}

	  @Override
	  public boolean equals(Object other) 
	  	{
	    if (other instanceof GraphNode) 
	    	{
	    	GraphNode that = (GraphNode) other;
	    	return this.name.equals(that.name);
	    	}
	    return false;
	  	}

	  @Override
	  public int hashCode() 
	  	{
		return Objects.hashCode(name);
	  	}

	  @Override
	  public String toString() 
	  	{
	    return "(" + name + ")";
	  	}
	}
