/**
 * This class represents a node for a binary search tree. 
 * 
 * @author David (Jung Won) Yang
 * 
 * @version 4/22/2017
 */
public class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> 
{
	//Data of a generic type stored by the node.
	private E data;
	
	//References to the two children. 
	private BSTNode<E> left;
	private BSTNode<E> right;
	
	
	/**
	 * Default constuctor for the class. Creates a Node will a null data element. 
	 */
	public BSTNode()
	{
	}
	
	
	/**
	 * Constructor that creates a BSTnode with the data element initialized to a specified value.
	 * 
	 * @param data is the specified data element that the data instance variable will 
	 * be initialized to.
	 */
	public BSTNode(E data)
	{
		this.data = data;
	}
	
	
	/**
	 * Getter for the Data instance variable. 
	 * 
	 * @return the data element stored in the node.
	 */
	public E getData()
	{
		return data;
	}
	
	/**
	 * Getter for the left node.  
	 * 
	 * @return returns a reference to the left node. 
	 */
	public BSTNode<E> getLeft()
	{
		return left;
	}
	
	/**
	 * Getter for the right node.  
	 * 
	 * @return returns a reference to the right node. 
	 */
	public BSTNode<E> getRight()
	{
		return right;
	}
	
	/**
	 * Setter for the data element in the node. 
	 * 
	 * @param data is the data element that will be stored in the node. 
	 */
	public void setData(E data)
	{
		this.data=data;
	}
	
	/**
	 * Setter for the left node. 
	 * 
	 * @param left is the left node that this node will point to.
	 */
	public void setLeft(BSTNode<E> left)
	{
		this.left=left;
	}
	
	/**
	 * Setter for the next node. 
	 * 
	 * @param rightis the right node that this node will point to.
	 */
	public void setRight(BSTNode<E> right)
	{
		this.right=right;
	}
	
	/**
	 * This method compares the the data of two nodes using the compareTo method
	 * of the data. 
	 * 
	 * @param other is the node that we are comparing with. 
	 */
	public int compareTo(BSTNode<E> other)
	{
		return data.compareTo(other.getData());
	}
}
