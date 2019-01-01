import java.util.*; import java.io.*;
/**
 * This class recieves input for the path of a csv file through command line arguments. The csv file must contain a
 * a data set of trees in NYC streets in the proper format. It then displays information about the trees stored in the dataset. 
 * 
 * @author David (Jung Won) Yang
 * 
 * @version 4/22/2017
 */
public class NYCStreetTrees 
{
	/**
	 * Main method of the program. Reads text from a csv file which contains the data set on the trees in NYC. 
	 * It stores the data onto the TreeList class which contains an ArrayList of Trees. The program recieves user
	 * input for a species of tree, processes the data in an ArrayList, and then returns: the number of trees in NYC, 
	 * the number of trees of a given species, number of trees in each borough, the number of trees of the given species 
	 * in each borough, and last all species that matches the species given (contains the species specified), 
	 * 
	 * @param args contains the command line argument which is the path of the csv file. 
	 * 
	 * @throws
	 */
	public static void main(String[] args) 
	{
		//If command line argument is not given, terminate the program. 
		if (args.length==0)
		{
			System.err.println("Usage Error: The program excepts file name as a command line argument.");
			System.exit(1);
		}
		
		TreeCollection nycTrees = new TreeCollection();
		
		File treesInNYC = new File(args[0]);
		
		//Make a Scanner object to read to the file. If file cannot be opened, terminate the program. 
		Scanner fileInput =  null;
		try
		{
			fileInput = new Scanner(treesInNYC);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error: the file " + args[0] + " cannot be opened.");
			System.exit(1);
		}
		
		//Create Tree objects for each tree in the data set, and intitialize the data field with the 
		//values in the data set. 
		while (fileInput.hasNextLine())
		{
			//Input a line (a tree) from the dataset, and split the entries into an ArrayList.  
			String textLine = fileInput.nextLine();
			ArrayList<String> treeEntries = splitCSVLine(textLine);
			
			//For the ArrayList elements that correspond to the data fields of the Tree class, 
			//assign them to variables and pass those variables to the constructor of the Tree class.
			try
			{
				int tree_id = Integer.parseInt(treeEntries.get(0));
				int tree_dbh = Integer.parseInt(treeEntries.get(3));
				String status = treeEntries.get(6);
				String health  = treeEntries.get(7);
				String spc_common = treeEntries.get(9);
				int zipcode = Integer.parseInt(treeEntries.get(25));
				String boroname = treeEntries.get(29);
				double x_sp = Double.parseDouble(treeEntries.get(39));
				double y_sp = Double.parseDouble(treeEntries.get(40));
					
				Tree tree = new Tree(tree_id,tree_dbh,status,health,spc_common,zipcode,boroname,x_sp,y_sp);
				nycTrees.add(tree);
					
			}
			//Skip over the tree if it contains faulty data that doesnt follow the criteria of the data fields.  
			catch (IllegalArgumentException e)
			{
			}
		}
		
		fileInput.close();
		
		displayCountForSpecies(nycTrees);
	}
	
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround  multi-word entries that may contain commas). 
	 * 
	 * @param textLine  line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	
	
	/**
	 * Recieves user input for a species, and displays: 
	 * All matching species.
	 * The count for the species in NYC and each of the boroughs.
	 * The count for all trees in NYC and for each of the boroughs, 
	 * The percentage of trees that are of the species in NYC and each of the boroughs.
	 * 
	 * @param nycTrees is a TreeList object that contains data for the trees in NYC. This list
	 * is processed to get the counts.
	 */
	public static void displayCountForSpecies(TreeCollection nycTrees)
	{
		//User input for the species of which we count trees for.  
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
		String species = input.nextLine();
				
		//Keep repeating as long as user does not input "quit".
		while (!(species.equalsIgnoreCase("quit")))
		{	
			int countByTreeSpecies = nycTrees.getCountByTreeSpecies(species);
					
			//Specify if there are no trees of the given species. 
			if (countByTreeSpecies == 0)
			{
				System.out.println("There are no records of " + species + " on NYC streets");
			}
			else
			{	
				//Display a list of all the matching species.
				ArrayList<String> matchingSpecies = (ArrayList<String>)nycTrees.getMatchingSpecies(species);
				System.out.println("All matching species:");
				for (int i=0; i<matchingSpecies.size(); i++)
				{
					System.out.println("  " + matchingSpecies.get(i));
				}
							
				System.out.println("\nPopularity in the city:");
							
				//Display the count of the given species in NYC streets, the count of all trees in NYC streets,
				//and the percetage of trees that are of the given species NYC. 
				float percentage;
				if ( (float)nycTrees.getTotalNumberOfTrees()==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpecies/(float)nycTrees.getTotalNumberOfTrees())*100;
				System.out.printf("  NYC%11s \t %,10d(%,d)" + "%7.2f" + "%s\n" 
						, ":" , countByTreeSpecies, nycTrees.getTotalNumberOfTrees() , percentage,"%");
							
				//Display the count of the given species in the streets of each borough, the count of all trees 
				//in each borough,and the percetage of trees that are of the given species in each borough. 
				int countByTreeSpeciesManhattan = nycTrees.getCountByTreeSpeciesBorough(species,"Manhattan");
				int countByManhattan = nycTrees.getCountByBorough("Manhattan");
				if ( (float)countByManhattan==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpeciesManhattan/(float)countByManhattan)*100;
				System.out.printf("  Manhattan%5s \t %,10d(%,d)" + "%8.2f" + "%s\n" 
						, ":" , countByTreeSpeciesManhattan, countByManhattan, percentage,"%");
							
				
				int countByTreeSpeciesBronx = nycTrees.getCountByTreeSpeciesBorough(species,"Bronx");
				int countByBronx = nycTrees.getCountByBorough("Bronx");
				if ( (float)countByBronx*100==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpeciesBronx/(float)countByBronx)*100;
				System.out.printf("  Bronx%9s \t %,10d(%,d)" + "%8.2f" + "%s\n" 
						, ":" , countByTreeSpeciesBronx, countByBronx, percentage,"%");
							
				
				int countByTreeSpeciesBrooklyn = nycTrees.getCountByTreeSpeciesBorough(species,"Brooklyn");
				int countByBrooklyn = nycTrees.getCountByBorough("Brooklyn");
				if ( (float)countByBrooklyn*100==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpeciesBrooklyn/(float)countByBrooklyn)*100;
				System.out.printf("  Brooklyn%6s \t %,10d(%,d)" + "%7.2f" + "%s\n" 
					, ":" , countByTreeSpeciesBrooklyn, countByBrooklyn, percentage,"%");
					
				
				int countByTreeSpeciesQueens = nycTrees.getCountByTreeSpeciesBorough(species,"Queens");
				int countByQueens = nycTrees.getCountByBorough("Queens");
				if ( (float)countByQueens*100==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpeciesQueens/(float)countByQueens)*100;
				System.out.printf("  Queens%8s \t %,10d(%,d)" + "%7.2f" + "%s\n" 
						, ":" , countByTreeSpeciesQueens, countByQueens, percentage,"%");
							
				int countByTreeSpeciesStatenIsland = nycTrees.getCountByTreeSpeciesBorough(species,"Staten Island");
				int countByStatenIsland = nycTrees.getCountByBorough("Staten Island");
				if ( (float)countByStatenIsland*100==0 )
				{
					percentage = 0;
				}
				else
					percentage = ((float)countByTreeSpeciesStatenIsland/(float)countByStatenIsland)*100;
				System.out.printf("  Staten Island%s \t %,10d(%,d)" + "%7.2f" + "%s\n" 
						, ":" , countByTreeSpeciesStatenIsland, countByStatenIsland, percentage,"%");
			}
					
			//Prompt user input again. 
			System.out.println("\nEnter the tree species to learn more about it (\"quit\" to stop):");
			species = input.nextLine();
		}		
		input.close();		
	}

}
