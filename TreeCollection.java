import java.util.*;
/**
 * This class represents a collection of trees from New York City. It stores Tree objects inside a binary
 * search tree, and operates on these Trees. 
 * 
 * @author David (Jung Won) Yang
 *
 * @version 4/22/2017
 */
public class TreeCollection extends MyBST<Tree>
{
	//Total number of trees in the dataset. 
	int treeTotal=0;
	//ArrayList with list of species. 
	ArrayList<String> speciesList;
	//Hash map with total trees in each borough. 
	HashMap<String,Integer> borocounts;
	
	
	/**
	 * Default constructor for the class. Creates an empty tree collection and initializes 
	 * the data fields. 
	 */
	public TreeCollection()
	{
		treeTotal=0;
		speciesList = new ArrayList<String>();
		borocounts = new HashMap<String,Integer>();
		
		//Create keys for all 5 boros, and initialize values to zero. 
		borocounts.put("brooklyn",0);
		borocounts.put("bronx",0);
		borocounts.put("manhattan",0);
		borocounts.put("queens",0);
		borocounts.put("staten island",0);
	}
	
	/**
	 * Override the add method in the MyBST class. Add the specified Tree to the BST 
	 * and increment the totalTree count, and the count of the borough of the speficied
	 * tree. Also add speciesName if it is not already in specieslist. 
	 * 
	 * @param e is the element we are adding to the BST. 
	 * @return true if the tree does not already have the element.
	 * false if the element is already in the tree. 
	 * @throws ClassCastException 
	 * @Throws NullPointerException if the element is null. 
	 */
	@Override
	public boolean add (Tree e) throws ClassCastException, NullPointerException
	{
		if (super.add(e)==true)
		{
			treeTotal++;
			borocounts.put( e.getBoroname().toLowerCase() , borocounts.get(e.getBoroname().toLowerCase())+1 );
			if ( !(speciesList.contains(e.getSpc_common().toLowerCase())) )
			{
				speciesList.add(e.getSpc_common().toLowerCase());
			}
			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * Returns the total number of trees in the BST. 
	 * 
	 * @return total number of trees. 
	 */
	public int getTotalNumberOfTrees()
	{
		return treeTotal;
	}
	
	
	/**
	 * Returns the total number of trees in the BST that are of the specified species (matching 
	 * species are counted as well). 
	 * 
	 * @param speciesName is the specified species that we count for.
	 * @return
	 */
	public int getCountByTreeSpecies(String speciesName)
	{
		//Find all matching species in the BST. 
		ArrayList<String> matchingSpecies = (ArrayList<String>)getMatchingSpecies(speciesName);
		int total =0;
		//Sum up counts for all matching species. 
		for (String species : matchingSpecies)
		{
			total += getSpeciesCount(species.toLowerCase(), root);
		}
		return total;
	}
	/**
	 * Recursive method to help getCountByTreeSpecies method. Gets the count of a 
	 * specified species (each of the matching species must be passed individually). 
	 * @param species is the specified species that we count trees for. 
	 * @param node is the current node in our traversal of the BST. 
	 * @return 0 if the node equals null. 
	 * The recursive call with the left node, if species is less than the species of the tree 
	 * in the current node. 
	 * The recursive call with the right node, if species is greater than the species of the tree 
	 * in the current node. 
	 * 1, plus the recursive call with the right node, plus the resursive call with the left node 
	 * if the species is equal to the species of the tree in the current node. 
	 */
	private int getSpeciesCount(String species, BSTNode<Tree> node)
	{
		if(node==null)
			return 0;
		//Get count from left node down.
		if(species.compareTo(node.getData().getSpc_common().toLowerCase())<0)
			return getSpeciesCount(species,node.getLeft());
		//Get count from right node down.
		else if(species.compareTo(node.getData().getSpc_common().toLowerCase())>0)
			return getSpeciesCount(species,node.getRight());
		//Add 1 and get counts from both nodes down. 
		return 1 + getSpeciesCount(species,node.getLeft()) + getSpeciesCount(species,node.getRight());
	} 
	
	/**
	 * Returns the total number of trees in the BST for a specified borough. 
	 * 
	 * @param boroName is the borough for which we return the total number of trees. 
	 * @return the total number of trees for a specified borough. 
	 */
	public int getCountByBorough(String boroName)
	{
		return borocounts.get(boroName.toLowerCase());
	}
	
	
	/**
	 * Returns the total number of trees in the BST that are of the specified species (matching 
	 * species are counted as well) and the specified borough. 
	 * 
	 * @param speciesName is the specified species that we count for.
	 * @param boroName is the specified borough that we count for. 
	 * @return
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName)
	{
		//Find all matching species in the BST.
		ArrayList<String> matchingSpecies = (ArrayList<String>)getMatchingSpecies(speciesName);
		int total =0;
		//Sum up counts for all matching species. 
		for (String species : matchingSpecies)
		{
			total += getSpeciesBoroughCount(species.toLowerCase(), boroName.toLowerCase(), root);
		}
		return total;
		
	}
	/**
	 * Recursive method to help getCountByTreeSpecies method. Gets the count of a 
	 * specified species (each of the matching species must be passed individually). 
	 * @param species is the specified species that we count trees for. 
	 * @param boro is the specified borough that we count trees for.
	 * @param node is the current node in our traversal of the BST. 
	 * @return 0 if the node equals null. 
	 * The recursive call with the left node, if species is less than the species of the tree 
	 * in the current node. 
	 * The recursive call with the right node, if species is greater than the species of the tree 
	 * in the current node. 
	 * 1, plus the recursive call with the right node, plus the resursive call with the left node 
	 * if the species and borough is equal to the species and borough of the tree. 
	 * The recursive call with the right node, plus the resursive call with the left node 
	 * if the species is equal to the species of the tree, but the borough is not equal. 
	 */
	private int getSpeciesBoroughCount(String species, String boro, BSTNode<Tree> node)
	{
		if(node==null)
			return 0;
		//Get count from left node down.
		if(species.compareTo(node.getData().getSpc_common().toLowerCase())<0)
			return getSpeciesBoroughCount(species,boro,node.getLeft());
		//Get count from right node down.
		else if(species.compareTo(node.getData().getSpc_common().toLowerCase())>0)
			return getSpeciesBoroughCount(species,boro,node.getRight());
		//Add 1 and get counts from both nodes down. 
		else if ( boro.compareTo(node.getData().getBoroname().toLowerCase())==0 )
			return 1 + getSpeciesBoroughCount(species,boro,node.getLeft()) + 
					getSpeciesBoroughCount(species,boro,node.getRight());
		//get counts from both nodes down.
		else 
			return getSpeciesBoroughCount(species,boro,node.getLeft()) + 
					getSpeciesBoroughCount(species,boro,node.getRight());
	} 
	
	/**
	 * Finds and returns all matching species of a specified species. Matching species are 
	 * species for which the specified species is a substring of. 
	 * 
	 * @param speciesName the specified species that we find matching species for. 
	 * @return a collection that contains all matching species of the specified species. 
	 */
	public Collection<String> getMatchingSpecies(String speciesName)
	{
		ArrayList<String> matchingSpecies = new ArrayList<String>();
		//Iterate through species list and find matching species. 
		for (String species:speciesList)
		{
			if ( species.contains(speciesName.toLowerCase()) )
				matchingSpecies.add(species);
		}
		return matchingSpecies;
	}
	
	/**
	 * Overrides the toString method in the MyBST class. Gives a sentence of information
	 * on the collection. 
	 * 
	 * @return a sentence briefly describing the collection. 
	 */
	public String toString()
	{
		return "Collection of trees in NYC Streets\nNumber of trees: " + this.treeTotal;
	}

}
