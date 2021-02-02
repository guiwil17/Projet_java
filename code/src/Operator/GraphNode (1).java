package Operator;

import com.google.common.base.Objects;

public class GraphNode 
	{
	private String name;
	private Integer id;

	GraphNode(String name, Integer id) 
		{
		this.name = name;
		this.id = id;
	    }

	  public String name() 
	  	{
	    return name;
	  	}

	  public Integer id() 
	  	{
	    return id;
	  	}
	  
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

	  @Override
	  public int hashCode() 
	  	{
		return Objects.hashCode(name, id);
	  	}

	  @Override
	  public String toString() 
	  	{
	    return "(" + name + ")";
	  	}
	}
