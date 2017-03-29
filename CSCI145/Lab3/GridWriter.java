/*
 * Grid Writer program.
 * Author: David Palzer (david.palzer@wwu.edu)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class GridWriter {

   // Minimum and maximum widths for the grid.
   static int minGridWidth;
   static int maxGridWidth;
   
   // Input file object.
   static File inputFile;

   public static void main(String[] args) throws FileNotFoundException {
      if (handleArguments(args))
         processInput();
   }
   
   static final String usage = "Usage: GridWriter min_width max_width input_file_name";

   /*
    * Validate the command line arguments and do setup based on the
    * arguments. Three command line arguments are expected:
    *   1. A positive integer that sets minGridWidth
    *   2. A positive integer that sets maxGridWidth
    *   3. A String that names the input file.
    *
    * Return true if processing was sucessful and false otherwise.
    */
   static boolean handleArguments(String[] args) {
      // Check for correct number of arguments
      if (args.length != 3) {
         System.out.println("Wrong number of command line arguments.");
         System.out.println(usage);
         return false;
      }
      
      // Get the minimum and maximum grid width from the first two
      // command line arguments.
      try {
         minGridWidth = Integer.parseInt(args[0]);
         maxGridWidth = Integer.parseInt(args[1]);
      } catch (NumberFormatException ex) {
         System.out.println("min_width and max_width must be integers.");
         System.out.println(usage);
         return false;
      }
      
      // Open the input file and get its length
      inputFile = new File(args[2]);
      if (!inputFile.canRead()) {
         System.out.println("The file " + args[2] + " cannot be opened for input.");
         return false;
      }

      return true;
   }
   
   /*
    * Get and process the input. For each width call loadUnloadGrid.
    */
   static void processInput() throws FileNotFoundException {
      Scanner input = new Scanner(inputFile);
      String line = input.nextLine();
      
      // Try each width in the appropriate range
      for (int width = minGridWidth; width <= maxGridWidth; width++) {
         // Determine heigth of grid
         int height = line.length() / width;
         
         // Add one to height if there's a partial last row
         if (line.length() % width != 0)
            height += 1;
            
         loadUnloadGrid(line, width, height);
      }
   }
   
   /*
    * Load and unload the input into a grid column by column. Unload
    * the characters row by row. Parameters
    *   line -- the input string
    *   width -- the number of columns in the grid
    *   height -- the number of rows in the grid
    */
   static void loadUnloadGrid(String line, int width, int height) {
      char grid[][] = new char[height][width];
      
      // Determine number long columns
      int longColumn = line.length() % width;
      if (longColumn == 0)
         longColumn = width;
         
      // System.out.printf("line length: %d, height: %d, width: %d, longColumn: %d%n",
      //       line.length(), height, width, longColumn);
         
      //Load the input data into the grid by column
      int charCount = 0;
      for (int c = 0; c < width; c++) {
         for (int r = 0; r < height; r++) {
            if (r < height - 1 || c < longColumn) {
               grid[r][c] = line.charAt(charCount);
               charCount += 1;
            }
         }
      }
      
      // Output data from the grid by rows
      System.out.printf("Grid width %d: \"", width);
      for (int r = 0; r < height - 1; r++) {
         for (int c = 0; c < width; c++) {
            System.out.print(grid[r][c]);
         }
      }
      // Special handling for last row
      for (int c = 0; c < longColumn; c++) {
         System.out.print(grid[height - 1][c]);
      }
      System.out.println("\"");
   }
}
   
