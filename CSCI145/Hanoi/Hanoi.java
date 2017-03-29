public class Hanoi {
	
	static final String usage = "ERROR: only input integer of discs between 0 and 64";
	
	static int disc;
	static char S = 'S';
	static char D = 'D';
	static char A = 'A';
	
	public static void main(String[] args) {
		if (handleArguments(args)){
			solveTower(disc, S, D, A);
			
			double doubleMoves = (Math.pow(2, disc) - 1);
			int moves = (int) doubleMoves;
			
			System.out.println();
			System.out.println("This puzzle was solved in " + moves + " moves");
		}
		
	}
	static boolean handleArguments(String [] args){
		if (args.length != 1){
			System.out.println(usage);
			return false;
		}
		if (args.length == 1){
			try{
				disc = Integer.parseInt(args[0]);
			}catch (NumberFormatException ex){
				System.out.println(usage);
				return false;
			}
		}
		if (disc == 0 || disc > 64){
			System.out.println(usage);
			return false;
		}
		return true;
	}
	static void solveTower(int disc, char S, char D, char A){
		if (disc == 1) {
			System.out.println("Move disc" + disc + " from " + S + " to " + D);
		}
		else{
			solveTower(disc - 1, S, A, D);
			System.out.println("Move disc" + disc + " from " + S + " to " + D);
			solveTower(disc - 1, A, D, S);
		}
	}
}
