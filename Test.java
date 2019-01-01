
public class Test 
{

	public static void main(String[] args) 
	{
		MyBST<Integer> myList = new MyBST<Integer>();
		
		myList.add(12);
		myList.add(5);
		myList.add(1);
		myList.add(7);
		myList.add(9);
		myList.add(15);
		myList.add(13);
		myList.add(17);
		myList.add(19);
		
		System.out.println(myList);
		myList.remove(12);
		myList.remove(15);
		System.out.println(myList);
	}

}
