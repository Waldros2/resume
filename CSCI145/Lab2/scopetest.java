public class scopetest {

   static int num1 = 40;                        // #1
   static int num2 = 41;                        // #2
   static int num3 = 0;                         // #3

   public static void main(String[] args) {
      add(10, 11);                              // #5
      add(num1, num2);                          // #6
      add(num2, num3);                          // #7
      System.out.println(num3);                 // #8
   }
   
   public static void add(int num1, int num2) { // #9
      int inputNum = num1;                      // #10
      num2 = num2 + inputNum;                   // #11
      //num3 += 1;                              // #12
      System.out.println(num2);                 // #13
   }                                            // #14
} 