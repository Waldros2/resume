import java.util.Scanner;

class yesorno {

   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      System.out.print("What is your answer? ");
      String answer = console.nextLine();
      answer = answer.trim();
     
      if (answer.equalsIgnoreCase("yes"))
         System.out.println("You answered yes.");
      else if (answer.equalsIgnoreCase("no"))
         System.out.println("You answered no.");
      else if (answer.equals(""))
         System.out.println("You need to enter a word.");
      else
         System.out.println(answer + " isn't yes or no.");
   
      // Changes end here.
   }
}
