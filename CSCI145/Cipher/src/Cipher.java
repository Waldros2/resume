/*
 * Double Array Encryption
 * Author: Scott Waldron (waldros2@students.wwu.edu)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Cipher {
	static int gridWidth;
	
	static File inputFile;
	
	public static void main(String[] args) throws FileNotFoundException {
		if (handleArguments(args))
			processInput();
	}

	static final String usage = "Usage: Cipher gridWidth input_file_name";
	
	static boolean handleArguments(String[] args){
		if (args.length != 2) {
			System.out.println("Wrong number of command line arguments");
			System.out.println(usage);
			return false;
		}
		
		try {
			gridWidth = Integer.parseInt(args[0]);
		}catch (NumberFormatException ex){
			System.out.println("width must be an integer");
			System.out.println(usage);
			return false;
		}
		inputFile = new File(args[1]);
		if (!inputFile.canRead()){
			System.out.println("The file " + args[1] + " cannot be opened for input.");
			return false;
		}
		return true;
	}
	
	static void processInput() throws FileNotFoundException {
		Scanner input = new Scanner(inputFile);
		String line = input.nextLine();
		
		for (int width = gridWidth; width <= gridWidth; width++){
			int height = line.length() / width;
			if (line.length() % width != 0)
				height +=1;
			loadUnloadGrid(line, width, height);
		}
	}
	static void loadUnloadGrid(String line, int width, int height){
		char grid[][] = new char [width][height];
		
		int longRow = line.length() % width;
		if (longRow == 0)
			longRow = width;
		
		int charCount = 0;
		for (int a = 0; a < height; a++){
			for (int b = 0; b < width; b++){
				if (charCount < line.length()) {
					grid[b][a] = line.charAt(charCount);
					charCount += 1;
				}
			}
		}
		System.out.println("Width key is " + gridWidth);
		for (int a = 0; a < width; a++){
			for (int b = 0; b < height; b++){
				System.out.print(grid[a][b]);
			}
		}
	}
}
