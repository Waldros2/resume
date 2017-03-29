import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MazeSolver {

   // The name of the file describing the maze
   static String mazefile;
   static File inputMaze;
   
   static int cellSize =    30;
   static int borderWidth = 40;
   static int sleepTime =   50;
	
   static final String usage1 = "Usage: input_file_name, 10<=cellSize (optional), 5<=borderWidth (optional),";
   static final String usage2 = "       0ms<sleepTime<10000ms(optional)";
   
   public static void main(String[] args) throws FileNotFoundException {
      if (handleArguments(args)) {
      readMazeFile();
    	  /*readMazeFile(mazefile);
         char[][] maze = new char[3][];
         maze[0] = new char[]{'+','-','+'};
         maze[1] = new char[]{'|','S','|'};
         maze[2] = new char[]{'+','-','+'};
         DrawMaze.draw(1,1, maze);
      
         if (solveMaze())
            System.out.println("Solved!");
         else
            System.out.println("Maze has no solution.");*/
      }
   }
   
   // Handle the input arguments
   static boolean handleArguments(String[] args) {
			
	   if (args.length < 1){
		   System.out.println("ERROR: Need an input file");
		   System.out.println(usage1);
		   System.out.println(usage2);
		   return false;
	   }
	   if (args.length > 4) {
			System.out.println("Wrong number of command line arguments");
			System.out.println(usage1);
			System.out.println(usage2);
			return false;
		}
			
		if (args.length == 4){
			try {
				cellSize = Integer.parseInt(args[1]);
				borderWidth = Integer.parseInt(args[2]);
				sleepTime = Integer.parseInt(args[3]);
			}catch (NumberFormatException ex){
				System.out.println("ERROR: Parameters must be integers");
				System.out.println(usage1);
				System.out.println(usage2);
				return false;
			}	
		
		}	
		
		if (cellSize < 10 || borderWidth < 5 || sleepTime >10000){
			
			System.out.println("ERROR: Parameters must be within range");
			System.out.println(usage1);
			System.out.println(usage2);
			return false;
		}
		
		inputMaze = new File (args[0]);
		if (!inputFile.canRead()){
			System.out.println("ERROR: The file " + args[0] + " cannot be opened for input.");
			System.out.println(usage1);
			System.out.println(usage2);
			return false;
		}
		
		return true;
	}
}
   
   // Read the file describing the maze.
   static void readMazeFile(String mazefile) throws FileNotFoundException {
	   Scanner input = new Scanner(inputMaze);
	   if (input.hasNextInt()){
		   int height = input.nextInt();
		   int width = input.nextInt();
		   System.out.println(height);
		   System.out.println(width);
		   return true;
	   }
	   else
		   return false;
		   
	   
   }

   // Solve the maze.      
   static boolean solveMaze() {
      return true;
   }
}
