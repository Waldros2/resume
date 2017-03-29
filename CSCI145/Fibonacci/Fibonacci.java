
public class Fibonacci {

	static final String usage = "ERROR: only input integer between 0 and 46";
	
	static int [] numbers;
	static int n;
	static int current;
	public static void main(String[] args) {
		if (handleArguments(args)){
			numbers = new int[n];
			Fibonacci(n);
		}

	}
	static boolean handleArguments(String []args){
		if (args.length != 1){
			System.out.println(usage);
			return false;
		}
		if (args.length == 1){
			try{
				n = Integer.parseInt(args[0]);
			}catch (NumberFormatException ex){
				System.out.println(usage);
				return false;
			}
		}
		if (n < 0 || n >46){
			System.out.println(usage);
		}
		return true;
	}
	
	static int Fibonacci(int n){
		if (n == 0){
			return numbers[0];
		}
		else{
			int current = numbers[n];
			if (current == 0){
				current = Fibonacci(n - 1) + Fibonacci(n - 2);
				numbers[n] = current;
			}
		}
		return current;
	}
}
