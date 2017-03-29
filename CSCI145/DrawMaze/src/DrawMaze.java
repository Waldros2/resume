/*
 * Draw Maze Scenario
 * Author: Scott Waldron (waldros2@students.wwu.edu)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class DrawMaze {
	static int cellSize = 30;
	static int borderWidth = 40;
	static int sleepTime = 50;
	
	static File inputFile;

	public static void main(String[] args) throws FileNotFoundException{ 
		handleArguments (args);
		

	}
	static final String usage1 = "Usage: input_file_name, 10<=cellSize (optional), 5<=borderWidth (optional),";
	static final String usage2 = "       0ms<sleepTime<10000ms(optional)";
	
	static boolean handleArguments(String[] args){
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
		inputFile = new File (args[0]);
		if (!inputFile.canRead()){
			System.out.println("The file " + args[0] + " cannot be opened for input.");
			return false;
		}
		if (args.length == 1)
			return true;
		return true;
	
	}
	
	
}
