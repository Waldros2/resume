import java.util.Scanner;

class returntest {

   public static void main(String[]args) {
      Scanner console = new Scanner(System.in);
      int n = console.nextInt();
      System.out.printf("n = %d, sign = %d%n", n, sign(n));
   }
   
   public static int sign(int n) {
      if (n > 0)
         return 1;
      else if (n == 0)
         return 0;
      else
         return -1;
   }
}