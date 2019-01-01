/**
 * This class represents a tree in NYC. An instance contains data about a trees, and can compare two trees. 
 * 
 * @author David (Jung Won) Yang
 * 
 * @version 4/22/2017
 */
public class Tree implements Comparable<Tree>
{
	private int tree_id; //non-negative integer
	private int tree_dbh; //non-negative integer
	
	//"Alive", "Dead", "Stump", or empty String or null.
	private String status;
	//"Good","Fair","poor", or empty String or null.
	private String health;
	
	private String spc_common; //Tree species (cannot be null)
	private int zipcode; //5 digit zip code. 
	
	//Valid values: "Manhattan", "Bronx", "Brooklyn", "Queens",  "Staten Island". 
	private String boroname;
	
	private double x_sp;
	private double y_sp;
	
	/**
	 * Contructor for the Tree class. Initializes the data fields of a tree object. 
	 * 
	 * @param id //Id number of the tree. non-negative integer
	 * @param diam //Tree diameter. non-negative integer.
	 * @param status //Status of the tree. Valid Values: "Alive", "Dead", "Stump", or empty String or null.
	 * @param health //Health condition of the tree. Valid values: "Good","Fair","Poor", or empty String or null.
	 * @param spc //Tree name (cannot be null)
	 * @param zip //5 digit zip code in which the tree is located. 
	 * @param boro //Boro in which the tree is located. Valid values: "Manhattan", "Bronx", "Brooklyn", "Queens",  "Staten Island". 
	 * @param x
	 * @param y
	 * 
	 * @throws IllegalArgumentException if the argument passed to the constructor does not meet the specifications stated. 
	 */
	public Tree (int id, int diam, String status, String health, String spc, int zip, String boro, double x, double y) 
	{
		//Check if id is non-negative before intializing.
		if (id>=0)
		{
			this.tree_id = id;
		}
		else
		{
			throw new IllegalArgumentException("Tree id does not meet specifications.");
		}
		
		//Check if diam is non-negative before intializing.
		if(diam>=0)
		{
			this.tree_dbh = diam;
		}
		else
		{
			throw new IllegalArgumentException("Tree diameter does not meet specifications.");
		}
		
		//Check if status param is a valid value. 
		if(status==null||status.equalsIgnoreCase("Alive")||status.equalsIgnoreCase("Dead")
				||status.equalsIgnoreCase("Stump")||status.equalsIgnoreCase("")) 
		{
			this.status = status;
		}
		else
		{
			throw new IllegalArgumentException("Tree status does not meet specifications.");
		}
		
		//Check if health param is a valid value. 
		if( health==null||health.equalsIgnoreCase("Good")||health.equalsIgnoreCase("Fair")
				||health.equalsIgnoreCase("Poor")||health.equalsIgnoreCase(""))
		{
			this.health = health;
		}
		else
		{
			throw new IllegalArgumentException("Tree heal does not meet specifications.");
		}
		
		//Check if spc param is not referencing null. 
		if(spc==null)
		{
			throw new IllegalArgumentException("species name cannot be null.");
		}
		else
		{
			this.spc_common = spc;
		}
		
		//Make sure zip is not greater than a 5 digit integer, and non-negative. 
		if (zip<100000 && zip>=0)
		{
			this.zipcode = zip;
		}
		else
		{
			throw new IllegalArgumentException("Zipcode does not meet specifications.");
		}
		
		//Check if boro is a valid value. 
		if(boro!=null && (boro.equalsIgnoreCase("Manhattan")||boro.equalsIgnoreCase("Bronx")||boro.equalsIgnoreCase("Brooklyn")
		||boro.equalsIgnoreCase("Queens")||boro.equalsIgnoreCase("Staten Island")))
		{
			this.boroname = boro;
		}
		else
		{
			throw new IllegalArgumentException("Borough name does not meet specifications.");
		}		
		
		this.x_sp = x;
		this.y_sp = y;
	}
	
	/**
	 * Getter for the name of the boro that the Tree is located in. 
	 * 
	 * @return the boro name of the Tree object.
	 */
	public String getBoroname()
	{
		return this.boroname;
	}
	
	
	/**
	 * Getter for the species of the Tree.
	 *  
	 * @return the species of the Tree object.
	 */
	public String getSpc_common()
	{
		return this.spc_common;
	}
	
	
	/**
	 * Getter for the ID of the Tree.
	 * 
	 * @return the ID of the Tree object. 
	 */
	public int getTree_id()
	{
		return this.tree_id;
	}
	
	
	/**
	 * This method overrides the to string method in object, and returns the ID of a Tree as a String. 
	 * 
	 * @return the ID of the Tree object as a String. 
	 */
	@Override
	public String toString()
	{
		return String.valueOf(this.tree_id);
	}
	
	
	/**
	 * This method implements the comparable method of the Comparable<Tree> interface. The method compares
	 * two trees based on the primary key of the species name, and the secondary key of ID. The comparison is case
	 * insensitive. 
	 * 
	 *@param other is the tree that is passed for comparison.
	 *
	 *@return 1 is returned if the species of the Tree is lexicographically greater the species of the Tree that is being passed 
	 *(case insensitive). If their species are the same, then the ID of the former is compared with the ID of the lattter, 
	 *and if the integer value it is greater, then 1 is returned. 
	 * -1 is returned when the species of the Tree is lexicographically lower than the species of the Tree being passed, or
	 * when the value of the ID is lowever. 
	 * 0 is returned if the species of both Trees are lexicographically the same, and both IDs are the same. 
	 */
	public int compareTo(Tree other)
	{
		if (other == null)
			throw new NullPointerException("Cannot compare with a null item.");
		
		//Check which Tree species is lexicographically greater.
		if (this.spc_common.compareToIgnoreCase(other.getSpc_common())>0)
		{
			return 1;
		}
		else if (this.spc_common.compareToIgnoreCase(other.getSpc_common())<0)
		{
			return -1;
		}
		//If the species are the same check which Tree has a higher ID value.  
		else
		{
			if ( this.tree_id > other.getTree_id() )
			{
				return 1;
			}
			else if ( this.tree_id < other.getTree_id() )
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	
	
	/**
	 * This method overrides the equals method in java.lang.Object class. The method checks if the Tree object that the method
	 * is called on is equal to the Tree object passed. 
	 * 
	 * @param other is the instance of Object that is be compared with the Tree. Must be a Tree as well.. 
	 * 
	 * @return true if the two Tree objects have the same species and ID names.
	 * false if the species and the IDs are different, or if the the species are the same, but the Ids are different.
	 * 
	 * @throws IllegalArgumentException if the species are different but the IDs are the same. Also throw the exception 
	 * when the Tree object passed (other) is not an instance of Tree. 
	 */
	@Override
	public boolean equals(Object other) 
	{
		//If the "other" Tree passed is not an instance of Tree, throw exception. 
		if (!(other instanceof Tree))
		{
			throw new IllegalArgumentException("Error: the object passed is not a Tree object.");
		}
		
		//Downcast other from Object to Tree, and assign to Tree variable. 
		Tree otherTree = (Tree)other;
		
		
		//If species are different but IDs are the same, throw exception.
		if ( !(this.spc_common.equalsIgnoreCase((otherTree).getSpc_common())) && (this.tree_id==(otherTree).getTree_id()) )
		{
			throw new IllegalArgumentException("Error: two trees cannot have the same IDs but be of different species.");
		}
		//If species and IDs are the same.
		else if ( (this.spc_common.equalsIgnoreCase((otherTree).getSpc_common())) && (this.tree_id==(otherTree).getTree_id()) )
		{
			return true;
		}
		//If both are not the same, or species are the same but IDs are different.
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Compares the species names of a tree with another tree to see if it is lexicographically
	 * greater than the other. 
	 * 
	 * @param t is the tree that is being compared with. 
	 * @return -1 if less than the species name of the specified tree. 
	 * 1 if greater.
	 * 0 if equal to. 
	 */
	public int compareName(Tree t)
	{
		if (t == null)
			throw new NullPointerException("Cannot compare with a null item.");
		//Species name is smaller.
		if (this.spc_common.toLowerCase().compareTo(t.getSpc_common().toLowerCase())<0)
			return -1;
		//Species name is greater. 
		else if (this.spc_common.toLowerCase().compareTo(t.getSpc_common().toLowerCase())>0)
			return 1;
		//Species name are equal. 
		else 
			return 0;
	}
	
	
	/**
	 * Compares the species names of a tree with another tree to see if the names are equal. 
	 * 
	 * @param t is the tree that is being compared with. 
	 * @return true if the names are equal.
	 * false if not. 
	 */
	public boolean sameName(Tree t)
	{
		if (t==null)
			throw new NullPointerException("Cannot compare with a null item.");
		
		if(this.spc_common.equalsIgnoreCase(t.getSpc_common()))
			return true;
		else
			return false;
	}
}
