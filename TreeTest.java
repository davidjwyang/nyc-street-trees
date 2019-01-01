import static org.junit.Assert.*;


import org.junit.Test;

@SuppressWarnings("unused")
public class TreeTest {

	/* test validation of first parameter (tree_id) 	*/
	@Test
	public void testTreeConstructorPositive() {
		
		try { 
			Tree t = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t ); 
			
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments)."+e.getMessage());
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
		
		
	}

	/* test validation of first parameter (tree_id) 	*/
	@Test
	public void testTreeConstructor1() {
		try { 
			Tree t = new  Tree ( -5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			fail("IllegalArgumentException not thrown for invalid tree_id.");
		}
		catch (IllegalArgumentException e ) {
			//do nothing, expected behavior
		}
		catch (Exception e ) {
			fail("Incorrect exception thrown.");
		}		
		
	}
	
	/* test validation of second parameter (diam) 	*/
	@Test
	public void testTreeConstructor2() {
		try { 
			Tree t = new  Tree ( 5, -3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			fail("IllegalArgumentException not thrown for invalid diam");
		}
		catch (IllegalArgumentException e ) {
			//do nothing, expected behavior
		}
		catch (Exception e ) {
			fail("Incorrect exception thrown.");
		}
	}
	

	/* test validation of third parameter (status) 	*/
	@Test
	public void testTreeConstructor3Positive() {
		String []  status_values = {"alive", "Alive", "dead", "Dead",
				"stump", "Stump", "", null}; 
		String s = "";
		try { 
			for (int i = 0; i < status_values.length; i++) {
				s =  status_values[i]; 
				Tree t = new  Tree ( 5, 3,s, "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			}
		}
		catch (IllegalArgumentException e ) {
			fail("IllegalArgumentException  thrown for valid status: "
					+ s);
		}
		catch (Exception e ) {
			fail("Incorrect exception thrown.");
		}
	}
	

	/* test validation of third parameter (status) 	*/
	@Test
	public void testTreeConstructor3Negative() {
		String []  status_values = {"hello", "WRONG", "Alive or Dead"}; 
		for (int i = 0; i < status_values.length; i++) { 
			try { 
				Tree t = new  Tree ( 5, 3, status_values[i], "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
				fail("IllegalArgumentException not thrown for invalid status: "
						+ status_values[i] );
			}
			catch (IllegalArgumentException e ) {
				//do nothing, expected behavior
			}
			catch (Exception e ) {
				fail("Incorrect exception thrown.");
			}
		}
	}
	

	/* test validation of fourth parameter (health) 	*/
	@Test
	public void testTreeConstructor4Positive() {
		String []  health_values = {"good", "Good", "fair", "Fair",
				"poor", "Poor", "", null}; 
		String h = "";
		
		try { 
			for (int i = 0; i < health_values.length; i++) { 
				h = health_values[i]; 
					Tree t = new  Tree ( 5, 3, "Alive", h, "Oak",
						 10003, "Manhattan",  100, 100 ); 
				}
		}
		catch (IllegalArgumentException e ) {
			fail("IllegalArgumentException thrown for valid health: "
					+ h );
		}
		catch (Exception e ) {
			fail("Exception thrown for valid health = "
					+ h );
		}
	}
	

	/* test validation of fourth parameter (health) 	*/
	@Test
	public void testTreeConstructor4Negative() {
		String []  health_values = {"tree", "good or fair", "Pooor"}; 
		
		for (int i = 0; i < health_values.length; i++) { 
			try { 
				Tree t = new  Tree ( 5, 3, "Alive",  health_values[i], "Oak",
					 10003, "Manhattan",  100, 100 ); 
				fail("IllegalArgumentException not thrown for invalid health: "
						+ health_values[i] );
			}
			catch (IllegalArgumentException e ) {
				//do nothing, expected behavior
			}
			catch (Exception e ) {
				fail("Incorrect exception thrown.");
			}
		}
	}
	

	/* test validation of fifth parameter (spc_common) 	*/
	@Test
	public void testTreeConstructor5Positive() {
		String []  spc_name = {"Ginkgo", "Willow Oak", "willow oak"}; 
		String s = ""; 
		try { 
			for (int i = 0; i < spc_name.length; i++) { 
				s = spc_name[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", s,
					 10003, "Manhattan",  100, 100 ); 
			}
		}
		catch (IllegalArgumentException e ) {
			fail("IllegalArgumentException thrown for valid status: " + s );		
		}
		catch (Exception e ) {
			fail("Exception thrown for valid status = " 	+ s );		
		}
	}
	

	/* test validation of fifth parameter (spc_common) 	*/
	@Test
	public void testTreeConstructor5Negative() {
		String []  spc_name = { null}; 
		String s = ""; 
		for (int i = 0; i < spc_name.length; i++) { 
			try { 
				s = spc_name[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", s,
					 10003, "Manhattan",  100, 100 ); 
				fail("IllegalArgumentException not thrown for invalid spc_name: " + s );		
			}
			catch (IllegalArgumentException e ) {
				//do nothing, expected behavior
			}
			catch (Exception e ) {
				fail("Incorrect exception thrown.");
			}
		}
	}
	

	/* test validation of sixth parameter (zipcode) 	*/
	@Test
	public void testTreeConstructor6Positive() {
		int []  zips = {10003, 8608, 96}; 
		int z=0; 
		try { 
			for (int i = 0; i < zips.length; i++) { 
				z = zips[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 z, "Manhattan",  100, 100 ); 
			}
		}
		catch (IllegalArgumentException e ) {
			fail("IllegalArgumentException  thrown for valid zip code:  "
					+ String.format( "%06d", z ) );		
		}
		catch (Exception e ) {
			fail("Exception  thrown for valid zip code:  "
					+ String.format( "%06d", z ) );	
		}
	}
	

	/* test validation of sixth parameter (zipcode) 	*/
	@Test
	public void testTreeConstructor6Negative() {
		int []  zips = {999999, -100, 100000}; 
		int z=0; 
		for (int i = 0; i < zips.length; i++) { 
			try { 
				z = zips[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 z, "Manhattan",  100, 100 ); 
				fail("IllegalArgumentException not thrown for invalid zip code:  "
						+ z );		
			}
			catch (IllegalArgumentException e ) {
				//do nothing, expected behavior
			}
			catch (Exception e ) {
				fail("Incorrect exception thrown.");
			}
		}
	}


	/* test validation of seventh parameter (boroname) 	*/
	@Test
	public void testTreeConstructor7Positive() {
		String []  boros = { "Manhattan", "Bronx", "Brooklyn", "Queens", "Staten Island", "queens", "BRONX" }; 
		String s = ""; 
		try { 
			for (int i = 0; i < boros.length; i++) { 				
				s = boros[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, s,  100, 100 ); 
			}
		}
		catch (IllegalArgumentException e ) {
			fail("IllegalArgumentException thrown for valid boroname: " + s );		
		}
		catch (Exception e ) {
			fail("Exception thrown for valid boroname = " 	+ s );		
		}
	}
	

	/* test validation of seventh parameter (boroname) 	*/
	@Test
	public void testTreeConstructor7Negative() {
		String []  boros = { "New York", "", null, "Staten" }; 
		String s = ""; 
		for (int i = 0; i < boros.length; i++) { 
			try { 
				s = boros[i]; 
				Tree t = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, s,  100, 100 ); 
				fail("IllegalArgumentException not thrown for invalid boroname: " + s );		
			}
			catch (IllegalArgumentException e ) {
				//do nothing, expected behavior
			}
			catch (Exception e ) {
				fail("Incorrect exception thrown: " + e.toString());	
			}
		}
	}

	
	/* test if class implements Comparable interface */
	@Test 
	public void testComparableImplemented () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			
			assertTrue( "Tree class does not implement Comparable<Tree>", 
					t1  instanceof Comparable<?> ) ;
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}

	
	/* test if class implements Comparable interface */
	@Test 
	public void testComparableWhenEqual1 () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue( "Tree class does not implement Comparable<Tree>", 
					t1  instanceof Comparable<?> ) ;

			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 5) failed to return 0. ", t1.compareTo(t2) == 0 ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}


	
	/* test if class implements Comparable interface */
	@Test 
	public void testComparableCaseInsensitive () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 5, 3, "Alive", "Good", "OAK",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue( "Tree class does not implement Comparable<Tree>", 
					t1  instanceof Comparable<?> ) ;

			assertTrue("Comparison of t1(Oak,5) and t2(OAK, 5) failed to return 0. ", t1.compareTo(t2) == 0 ); 
			
				
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	

	
	/* test if class implements Comparable interface */
	@Test 
	public void testComparablePrimaryKey() { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 12, 3, "Alive", "Good", "Birch",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue( "Tree class does not implement Comparable<Tree>", 
					t1  instanceof Comparable<?> ) ;

			assertTrue("Comparison of t1(Oak,5) and t2(Birch, 12) failed to return a positive value. ", t1.compareTo(t2) > 0  ); 
			assertTrue("Comparison of t2(Birch, 12) and t1(Oak,5) failed to return a negative value. ", t2.compareTo(t1) < 0  ); 
			
				
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	

	
	/* test if class implements Comparable interface */
	@Test 
	public void testComparableSecondaryKey() { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue( "Tree class does not implement Comparable<Tree>", 
					t1  instanceof Comparable<?> ) ;

			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return a nevtive value. ", t1.compareTo(t2) < 0  ); 
			assertTrue("Comparison of t2(Oak, 15) and t1(Oak,5) failed to return a positive value. ", t2.compareTo(t1) > 0  ); 
			
				
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}



	
	/* test implementation of equals method*/
	@Test 
	public void testEqualsWhenEqual1 () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 5) failed to return true. ", t1.equals(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}



	/* test implementation of equals method*/
	@Test 
	public void testEqualsWhenEqual2 () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 5, 3, "Alive", "Good", "OAK",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue("Comparison of t1(Oak,5) and t2(OAK, 5) failed to return true. ", t1.equals(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}



	/* test implementation of equals method*/
	@Test 
	public void testEqualsWhenEqual3 () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 5, 4, "Alive", "Poor", "Oak",
					 10003, "Brooklyn",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 5) (other data fields differ) failed to return true. ", t1.equals(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}


	/* test implementation of equals method*/
	@Test 
	public void testEqualsWhenNotEqual1 () { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 7, 3, "Alive", "Good", "Birch",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue("Comparison of t1(Oak,5) and t2(Birch, 7) failed to return false. ", !t1.equals(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}



	/* test implementation of equals method*/
	@Test 
	public void testEqualsWhenNotEqual2() { 
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertNotNull( "Reference null after constructor returns.", t1 );
			assertNotNull( "Reference null after constructor returns.", t2 );
			
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", !t1.equals(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	
	@Test
	public void testSameName1(){
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", t1.sameName(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	
	@Test
	public void testSameName2(){
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Willow",
					 10003, "Manhattan",  100, 100 ); 
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", !t1.sameName(t2)  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}

	@Test
	public void testCompareName1(){
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", t1.compareName(t2) ==0  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	
	@Test
	public void testCopmareName2(){
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Willow",
					 10003, "Manhattan",  100, 100 ); 
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", t1.compareName(t2) < 0); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	
	@Test
	public void testSameName3(){
		try { 
			Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
					 10003, "Manhattan",  100, 100 ); 
			Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "ak",
					 10003, "Manhattan",  100, 100 ); 
			assertTrue("Comparison of t1(Oak,5) and t2(Oak, 15) failed to return false. ", t1.compareName(t2) > 0  ); 
							
		}
		catch (IllegalArgumentException e ) {
			fail("Exception thrown incorrectly (for valid arguments).");
		}
		catch (Exception e ) {
			fail("Exception thrown incorrectly (for valid arguments)..");
		}
	}
	

}