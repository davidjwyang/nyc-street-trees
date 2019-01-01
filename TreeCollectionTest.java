import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TreeCollectionTest {

	public Tree t1 = new  Tree ( 5, 3, "Alive", "Good", "Oak",
			 10003, "Manhattan",  100, 100 ); 
	public Tree t2 = new  Tree ( 15, 3, "Alive", "Good", "Red Oak",
			 10003, "Manhattan",  100, 100 ); 
	public Tree t3 = new  Tree ( 25, 3, "Alive", "Good", "Red Oak",
			 10003, "Bronx",  100, 100 ); 
	public Tree t4 = new  Tree ( 35, 3, "Alive", "Good", "Oaktree",
			 10003, "Brooklyn",  100, 100 ); 
	public Tree t5 = new  Tree ( 10, 3, "Alive", "Good", "Birch",
			 10003, "Manhattan",  100, 100 ); 
	public Tree t6 = new  Tree ( 20, 3, "Alive", "Good", "White Birch",
			 10003, "Bronx",  100, 100 ); 
	public Tree t7 = new  Tree ( 30, 3, "Alive", "Good", "White Birch",
			 10003, "Queens",  100, 100 ); 
	public Tree t8 = new  Tree ( 40, 3, "Alive", "Good", "Birch Oak",
			 10003, "Bronx",  100, 100 ); 
	
	//test of the class extends the ArrayList class 
	@Test
	public void testMyBSTExtended () {
		try{ 
			TreeCollection tl = new TreeCollection();
			assertNotNull( "Reference null after constructor returns.", tl );
			
			assertTrue( "TreeList class does not implement MyBST<?>", 
					tl  instanceof MyBST<?> ) ;
			
		}
		catch (Exception ex ) {
			fail("Exception thrown incorrectly for default constructor call. ");
		}
	}

	//test constructor 
	@Test
	public void testConstructor() {
		try{ 
			TreeCollection tl = new TreeCollection();
			assertNotNull( "Reference null after constructor returns.", tl );
		}
		catch (Exception ex ) {
			fail("Exception thrown incorrectly for default constructor call. ");
		}
	}


	
	//////////////////////////////////////////////////////////////////////////////////////////
	// test each of the get... methods with different tree objects 
	//////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetCountByTreeSpecies1() {

		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"Red Oak\" not equal to 1 after adding t1(Oak) and t2(Red Oak)", tl.getCountByTreeSpecies("Red Oak") == 1 );
	}


	@Test
	public void testGetCountByTreeSpecies2() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"Oak\" not equal to 2 after adding t1(Oak) and t2(Red Oak)", tl.getCountByTreeSpecies("Oak") == 2 );
		tl.add(t5);
		tl.add(t7);
		assertTrue("return value for \"BirchOak\" not equal to 0 after adding t1(Oak), t2(Red Oak), t5 (Birch) and t7(White Birch)",
				tl.getCountByTreeSpecies("BirchOak") == 0 );
	}



	@Test
	public void testGetCountByTreeSpecies3() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"Birch\" not equal to 0 after adding t1(Oak) and t2(Red Oak)", tl.getCountByTreeSpecies("Birch") == 0 );
		tl.add(t5);
		tl.add(t7);
		assertTrue("return value for \"Oak\" not equal to 2 after adding t1(Oak), t2(Red Oak), t5 (Birch) and t7(White Birch)",
				tl.getCountByTreeSpecies("Oak") == 2 );
	}
	

	@Test
	public void testGetCountByBorough1() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		assertTrue("return value for \"Manhattan\" not equal to 0 for empty TreeList", tl.getCountByBorough("Manhattan")== 0 );
	}
	
	@Test
	public void testGetCountByBorough2() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"Manhattan\" not equal to 2 after adding t1(Manhattan) and t2(Manhattan)", tl.getCountByBorough("Manhattan")== 2 );
		tl.add(t4);
		tl.add(t7);
		assertTrue("return value for \"Manhattan\" not equal to 0 after adding t1(Manhattan) and t2(Manhattan), t4 (Brooklyn) and t7(Queens)",
				tl.getCountByBorough("Manhattan") == 2 );
	}

	
	@Test
	public void testGetCountByBorough3() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"Bronx\" not equal to 0 after adding t1(Manhattan) and t2(Manhattan)", tl.getCountByBorough("Bronx")== 0 );
		tl.add(t4);
		tl.add(t8);
		assertTrue("return value for \"Bronx\" not equal to 1 after adding t1(Manhattan) and t2(Manhattan), t4 (Brooklyn) and t8(Bronx)",
				tl.getCountByBorough("Bronx") == 1 );
	}

	
	@Test
	public void testGetCountByTreeSpeciesBorough1() {

		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		assertTrue("return value for \"Manhattan\" not equal to 0 for empty TreeList", 
				tl.getCountByTreeSpeciesBorough("oak", "Manhattan")== 0 );
		
	}
	
	@Test
	public void testGetCountByTreeSpeciesBorough2() {
		TreeCollection tl = new TreeCollection();
		assertNotNull( "Reference null after constructor returns.", tl );
		
		tl.add(t1);
		tl.add(t2);
		assertTrue("return value for \"oak\", \"Manhatta\" not equal to 2 after adding t1(Oak, Manhattan) and t2(Red Oak, Manhattan)",
				tl.getCountByTreeSpeciesBorough("oak", "Manhattan")== 2 );
		tl.add(t4);
		tl.add(t8);
		assertTrue("return value for\"oak\", \"Manhatta\"  not equal to 2  after adding t1(Oak, Manhattan) and t2(Red Oak, Manhattan), t4 (Oaktree, Brooklyn) and t8(Birch Oak, Bronx)",
				tl.getCountByTreeSpeciesBorough("oak", "Manhattan")== 2 );
	}

	@Test
	public void testGetTotalNumberOfTrees() {
		try{ 
			TreeCollection tl = new TreeCollection();
			assertNotNull( "Reference null after constructor returns.", tl );
			
			tl.add(t1);
			tl.add(t2);
			assertTrue("return value not equal to 2 after two trees added", tl.getTotalNumberOfTrees() == 2 );
			
		}
		catch (Exception ex ) {
			fail("Exception thrown incorrectly for default constructor call. ");
		}
	}

}
