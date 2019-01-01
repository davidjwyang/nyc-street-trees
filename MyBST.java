import java.util.*;
/**
 * This class represents a binary search tree. 
 * 
 * @author David (Jung Won) Yang
 * 
 * @version 4/22/2017
 */
public class MyBST<E extends Comparable <E>> 
{
	//Reference to the root node.
	protected BSTNode<E> root;

	/**
	 * Default constructor for the class. Creates an empty binary search tree.
	 */
	public MyBST()
	{
	}
	
	
	/**
	 * Adds an specified item to the binary search tree. 
	 * 
	 * @param e is the element we are adding to the tree. 
	 * @return true if the tree does not already have the element.
	 * false if the element is already in the tree. 
	 * @throws ClassCastException if the item is a type that cannot be in the set.
	 * @throws NullPointerException if the element is null. 
	 */
	public boolean add (E e) throws ClassCastException, NullPointerException
	{
		//Check if element is null. 
		if (e==null)
		{
			throw new NullPointerException("Cannot add a null item.");
		}
		//If root is null, add the item to the root. 
		else if(root==null)
		{
			root = new BSTNode<E>(e);
			return true;
		}
		//Call the recursive method. 
		return add(root,e);
	}
	/**
	 * Recursive method for the add method. Recursively looks for 
	 * a location to add the item, and adds. 
	 * 
	 * @param current is the current node in the traversal. We check its children
	 * for further travering or adding. 
	 * @param data is the item we are adding to the tree. 
	 * @return true if the item was added to the tree.
	 * false if the data is already in the tree.
	 */
	private boolean add(BSTNode<E> current, E data)
	{
		//If data is less than data of the node, call the function on the left node
		//if it is not equal to null, and if it is equal, add item to left node. 
		if (data.compareTo(current.getData())<0)
		{
			if (current.getLeft()!=null)
				return add(current.getLeft(),data);
			else
			{
				current.setLeft(new BSTNode<E>(data));
				return true;
			}
		}
		//If data is greater than data of the node, call the function on the right node
		//if it is not equal to null, and if it is equal, add item to right node. 
		else if( data.compareTo(current.getData())>0 )
		{
			if (current.getRight()!=null)
				return add(current.getRight(),data);
			else
			{
				current.setRight(new BSTNode<E>(data));
				return true;
			}	
		}
		//If item is already in the tree, return false.
		else
			return false;
	}
	
	
	/**
	 * Remove the specified element from the binary tree. 
	 * 
	 * @param o is the element that we are looking to remove. 
	 * @return true if the element was removed. 
	 * false if the item was not removed. 
	 * @throws ClassCastException if the specified element cannot be compared with 
	 * the elements in the tree.
	 * @throws NullPointerException if the element specified is null 
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o)
	{
		if (o==null) throw new NullPointerException("The element to be removed cannot be null.");
		//If tree is empty, return false. 
		if (root==null)
			return false;
		//If the specified element is in the root remove the root.  
		else if( ((E)o).compareTo(root.getData())==0 )
		{
			//Replace the root with another element. 
			BSTNode<E> replacementNode=removeNode(root);
			replacementNode.setRight(root.getRight());
			replacementNode.setLeft(root.getLeft());
			root=replacementNode;
			return true;
		}
		//Call the recursive method that will find and remove the element specified.
		return remove(root,(E)o);
	}
	/**
	 * Recursive method of remove method. Finds specified element, and removes it. 
	 * 
	 * @param current the current node in the traveral. We check the children for
	 * the specified element. 
	 * @param data is the element that we are looking to remove. 
	 * @return true if the element is removed. 
	 * false if the element is not in the tree.  
	 */
	private boolean remove(BSTNode<E> current, E data)
	{
		//Node to replace the data that we will remove.
		BSTNode<E> replacementNode;
		
		if( data.compareTo(current.getData())<0 )
		{
			if (current.getLeft()==null)
				return false;
			//Left node is specified element, so remove and replace with another element. 
			if (data.compareTo(current.getLeft().getData())==0)
			{
				replacementNode = removeNode(current.getLeft());
				current.setLeft(replacementNode);
				return true;
			}
			//Call recursive method to further traverse down the tree. 
			return remove(current.getLeft(),data);
		}
		else
		{
			if (current.getRight()==null)
				return false;
			//Right node is specified element so remove, and replace with another element. 
			if ( data.compareTo(current.getRight().getData())==0 )
			{
				replacementNode = removeNode(current.getRight());
				current.setRight(replacementNode);
				return true;
			}
			//Call recursive method to further traverse down the tree. 
			return remove(current.getRight(),data);
		}
	}
	/**
	 * Finds and returns a node with an element to replace the element that is to be removed.
	 * May return null if the node with the element to be removed does not have any children. 
	 * 
	 * @param node is the node that contains the element to be removed. 
	 * @return node with the element that will replace the element to be removed. 
	 */
	private BSTNode<E> removeNode(BSTNode<E> node)
	{
		//If the left node is null return right node. If right is null return left.
		//If both are null, null will be returned. 
		if(node.getLeft()==null)
			return node.getRight();
		if(node.getRight()==null)
			return node.getLeft();
	 
		//Otherwise we have two child nodes.
		//Starting from the left node, find the right most node and save the data. 
		E data = last(node.getLeft());
		//Remove the element that was just found.  
		remove(data);
		//Create a node with the element, and return it. 
		BSTNode<E> replacementNode = new BSTNode<E>(data);
		replacementNode.setRight(node.getRight());
		replacementNode.setLeft(node.getLeft());
		return replacementNode;
	}
	
	
	/**
	 * Checks if the tree contains the specified element. 
	 * 
	 * @param o is the element that we look for in the tree. 
	 * @return true if the tree contains the specified element.
	 * false if it does. 
	 * @throws ClassCastException if the specified element is not the same 
	 * actual type as the elements in the tree. 
	 * @throws NullPointerException if the element specified is null. 
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o)
	{
		//Check if element specified is null. 
		if (o==null)
			throw new NullPointerException("Cannot look for a null element.");
		
		//Call recursive method. 
		return contains((E)o,root);	
	}
	/**
	 * Recursive method for contains method. Checks if specified element is in the tree. 
	 * @param o is the element that we look for in the tree. 
	 * @param current is the current node in the traversal. 
	 * @return true if the tree contains the element.
	 * false if it doesn't. 
	 */
	private boolean contains(E o, BSTNode<E> current)
	{
		//If node is null, then the element is not in the tree. 
		if (current==null) 
			return false;
		//Compare to the element to determine whether to go right or left. 
		else if (o.compareTo(current.getData())<0)
			return contains(o,current.getLeft());
		else if (o.compareTo(current.getData())>0)
			return contains(o,current.getRight());
		//Found element. 
		else 
			return true;
	}
	
	
	/**
	 * Returns the lowest element in the tree.
	 * 
	 * @return lowest element in the tree.
	 * @throws NoSuchElementException if the tree is empty. 
	 */
	public E first() throws NoSuchElementException
	{
		if (root==null)
			throw new NoSuchElementException("The tree is empty.");
		return first(root);
	}
	/**
	 * Helper method for the first method. Finds, and returns the lowest element. 
	 * 
	 * @param startNode is the first element of the tree/subtree where we find the 
	 * lowest element for.  
	 * @return lowest element of the tree/subtree. 
	 */
	private E first(BSTNode<E> startNode)
	{
		BSTNode<E> current = startNode;
		//While the left element of the node is not null, keep traversing leftward. 
		while(current.getLeft()!=null)
		{
			current=current.getLeft();
		}
		
		return current.getData();
	}
	
	
	/**
	 * Returns the highest element in the tree. 
	 * 
	 * @return highest element in the tree.
	 * @throws NoSuchElementException if the tree is empty. 
	 */
	public E last() throws NoSuchElementException
	{
		if (root==null)
			throw new NoSuchElementException("The tree is empty.");
		return last(root);
	}
	/**
	 * Helper method for the last method. Finds, and returns the highest element. 
	 * 
	 * @param startNode is the first element of the tree/subtree where we find the 
	 * high element for. 
	 * @return highest element of tree/subtree. 
	 */
	private E last(BSTNode<E> startNode)
	{
		BSTNode<E> current = startNode;
		//While the left element of the node is not null, keep traversing leftward.
		while(current.getRight()!=null)
		{
			current=current.getRight();
		}
		
		return current.getData();
	}
	
	
	
	/**
	 * Overrides the toString method in Object. Returns the String representation of 
	 * the elements of the the Tree as list (in preorder).
	 * @returns String representation of the elements as a list.  
	 */
	@Override
	public String toString()
	{
		ArrayList<String> elementList = new ArrayList<String>();
		preorderString(root,elementList);
		String stringOfElements = elementList.toString();
		return stringOfElements;
	}
	/**
	 * Adds elements of the tree to a list, in preorder. 
	 * 
	 * @param current is the current node in our traversal that we are adding(given its not null). 
	 * @param elementList is the list of elements of the tree. 
	 */
	private void preorderString(BSTNode<E> current, ArrayList<String> elementList)
	{
		//If current node is not null, add the element, and recursively call for 
		//left and right nodes. 
		if(current!=null)
		{
			elementList.add(current.getData().toString());
			preorderString(current.getLeft(),elementList);
			preorderString(current.getRight(),elementList);

		}
	}
}
