/*
 * CSCI 145
 * MARCH 11, 16
 * ASSIGNEMNT 3
 * AUTHOR: SCOTT WALDRON
 * FILE: BOOK INVENTORY.JAVA
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class BookInventory {
   // Transaction (stock/order) is like change from BookStuff

   public static Book findBook(String isbn, ArrayList<Book> book) {
      for (int i = 0; i < book.size(); i++) {
         Book aBook = book.get(i);
         if(aBook.getIsbn().equals(isbn)) {
            return aBook;
         }
      }
      return null;
   }
   
   public static void main(String args[]){

      // check for the number of command line arguments
      if(args.length != 2){
         System.out.println("Please enter two file names: books transactions.");
         return; // Terminate program.
      }
      
      /* open the first file and add each book to the ArrayList books */
      System.out.println("Reading " + args[0]);
      ArrayList<Book> books = getBooks(args[0]);

      /* make some changes to books*/
      System.out.println("Using " + args[1] + " to change inventory");
      doTransactions(books, args[1]);
      
      System.out.println("\nTotal value of orders filled is $" + Book.getTotalValue());
   }
   /* Add books from input file to collection. */
   public static ArrayList<Book> getBooks(String fileName){
      ArrayList<Book> books = new ArrayList<Book>();
      Scanner input = null;
      try {
         input = new Scanner(new File(fileName));
      } catch (FileNotFoundException ex) {
         System.out.println("Error: File " + fileName + " not found. Exiting program.");
         System.exit(1);
      }

      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineData = new Scanner(line);
         try {
            String isbn = lineData.next();
            double price = lineData.nextDouble();
            int stock = lineData.nextInt();
            Queue<Order> order = new Queue<Order>();
            Book book = new Book(isbn, price, stock, order);
            books.add(book);
            
         } catch (java.util.InputMismatchException ex) {
            System.out.println("Line: " + line);
            System.out.println("Mismatched token: " + lineData.next());
            throw ex;
         }
      }
      return books;
  }
   /* Use the transaction file to manipulate the books */
   public static void doTransactions(ArrayList<Book> books, String changeFile){
      Scanner input = null;
      try {
         input = new Scanner(new File(changeFile));
      } catch (FileNotFoundException ex) {
         System.out.println("Error: File " + changeFile + " not found. Exiting program.");
         System.exit(1);
      }
      while (input.hasNext()) {
    	  String type = input.next();
    	  String isbn = input.next();
    	  int quantity = input.nextInt();
    	  Book book = findBook(isbn, books);
    	  if (type.equals("STOCK")) {
    		  book.changeStock(quantity);
          }
    	  else if(type.equals("ORDER")){
    		  String customerID = input.next();
    		  Order order = new Order(customerID, quantity);
    		  book.fillOrder(order);
    	  }	
      }
      
   }
}