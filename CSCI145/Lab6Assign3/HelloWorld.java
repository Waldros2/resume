import java.util.Scanner;
class HelloWorld{

    static Scanner input = new Scanner(System.in);
    static String name;
    static String lastName;
    
    public static void main(String args[]){
	System.out.println("Please enter your name");
	name = input.next();
	System.out.println("Please enter your last name");
	lastName = input.next();
	System.out.println("Hello " + name + " " + lastName + ", nice to meet you.");
	loveJade();
    }
    static void loveJade(){
	System.out.println("       |       ");
	System.out.println("  L  O   V  E  ");
	System.out.println("     Y O U     ");
	System.out.println("  J  A   D  E  ");
    }
}
