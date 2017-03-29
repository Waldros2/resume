import java.util.Scanner;

public class fencepost {

   public static void main(String[] args) {
      System.out.print("Enter a line of text: ");
      Scanner console = new Scanner(System.in);
      String input = console.nextLine();
      
      System.out.print("You entered the words: ");
      Scanner linescanner = new Scanner(input);
      while (linescanner.hasNext()) {
         System.out.print(linescanner.next());
         if (linescanner.hasNext()){
            System.out.print(", ");
         }else {
            System.out.print(".");
         }
      }
      System.out.println();
   }
}