import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class BookInventory {

   static class BookData {
      Book book;
      Queue<BackOrder> backOrders;
      
      BookData(Book book) {
         this.book = book;
         this.backOrders = new Queue<>();
      }
   }
   
   static class BackOrder {
      int amount;
      int customer;
      
      BackOrder(int amount, int customer) {
         this.amount = amount;
         this.customer = customer;
      } 
   }

   static ArrayList<BookData> inventory;
   
   public static void main(String[] args) {
      if (args.length != 2) {
         System.out.println("Please enter a book file name and a transactions file name.");
         return;
      }
      
      getInventory(args[0]);
      
      double value = processTransactions(args[1]);   
   
      System.out.println();
      System.out.printf("Total value of orders filled is $%.2f%n", value);
      System.out.println();
   }
   
   static void getInventory(String inventoryFile) {
      inventory = new ArrayList<>();
      Scanner input = null;
      try {
         input = new Scanner(new File(inventoryFile));
      } catch (FileNotFoundException ex) {
         System.out.println("Error: File " + inventoryFile + " not found. Exiting program.");
         System.exit(1);
      }
      
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineData = new Scanner(line);
         try {
            String isbn = lineData.next();
            double price = lineData.nextDouble();
            int stock = lineData.nextInt();
            Book book = new Book(isbn, price, stock);
            inventory.add(new BookData(book));
         } catch (java.util.InputMismatchException ex) {
            System.out.println("Line: " + line);
            System.out.println("Mismatched token: " + lineData.next());
            throw ex;
         }
      }
   }
   
   static double processTransactions(String transactionFile) {
      Scanner input = null;
      try {
         input = new Scanner(new File(transactionFile));
      } catch (FileNotFoundException ex) {
         System.out.println("Error: File " + transactionFile + " not found. Exiting program.");
         System.exit(1);
      }
      
      double totalValue = 0.0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineData = new Scanner(line);
         String transactionType = lineData.next();
         double transactionValue = 0.0;
         
         try {
            if (transactionType.equals("STOCK"))
               transactionValue = doStockTransaction(lineData);
            else if (transactionType.equals("ORDER"))
               transactionValue = doOrderTransaction(lineData);
            else {
               System.out.println("Unrecognized transaction type: " + line);
               System.exit(1);
            }
         } catch (java.util.InputMismatchException ex) {
            System.out.println("Line: " + line);
            System.out.println("Mismatched token: " + input.next());
            throw ex;
         }

         totalValue += transactionValue;
         // showBackOrders();
      }
      return totalValue;
   }
   
   static double doStockTransaction(Scanner input) {
      String isbn = input.next();
      int addedStock = input.nextInt();
      
      BookData data = findIsbn(isbn);
      if (data == null) {
         System.out.println("ISBN " + isbn + " not found in inventory");
         System.exit(1);
      }
      int oldStock = data.book.getStock();
      data.book.changeStock(addedStock);
      System.out.printf("Stock for book %s increased from %d to %d%n", isbn, oldStock, data.book.getStock());
      
      double value = 0.0;
      while (data.book.getStock() > 0 && !data.backOrders.isEmpty()) {
         BackOrder order = data.backOrders.peek();
         int ordersize = (order.amount <= data.book.getStock()) ? order.amount : data.book.getStock();
         int remaining = order.amount - ordersize;
         value += fillOrder(data, order.customer, ordersize, true);
         if (remaining > 0) {
            order.amount = remaining;
         } else {
            data.backOrders.remove();
         }
      }
               
      return value;
   }
   
   static double doOrderTransaction(Scanner input) {
      String isbn = input.next();
      int total = input.nextInt();
      int customer = input.nextInt();
      
      BookData data = findIsbn(isbn);
      if (data == null) {
         System.out.println("ISBN " + isbn + " not found in inventory");
         System.exit(1);
      }
      int stock = data.book.getStock();
      int ordersize = (stock < total) ? stock : total;
      int backorder = total - ordersize;
      double value = 0.0;
      if (ordersize > 0)
         value = fillOrder(data, customer, ordersize, false);
      if (backorder > 0)
         backOrder(data, customer, backorder);
          
      return value;
   }
   
   static double fillOrder(BookData data, int customer, int ordersize, boolean backOrder) {
      data.book.changeStock(-ordersize);
      double value = ordersize * data.book.getPrice();
      String ordertype = backOrder ? "Back order" : "Order";
      String copies = (ordersize == 1) ? "copy" : "copies";
      System.out.printf("%s filled for customer %d for %d %s of book %s%n",
            ordertype, customer, ordersize, copies, data.book.getIsbn());
      return value;
   }
   
   static void backOrder(BookData data, int customer, int backorder) {
      data.backOrders.add(new BackOrder(backorder, customer));
      String copies = (backorder == 1) ? "copy" : "copies";
      System.out.printf("Back order for customer %d for %d %s of book %s%n",
            customer, backorder, copies, data.book.getIsbn());
   }
   
   static BookData findIsbn(String isbn) {
      for (int i = 0; i < inventory.size(); i++) {
         if (inventory.get(i).book.getIsbn().equals(isbn))
            return inventory.get(i);
      }
      return null; 
   }
   
//    static void showBackOrders() {
//       for (BookData data : inventory) {
//          if (!data.backOrders.isEmpty()) {
//             System.out.printf("For isbn %s:%n", data.book.getIsbn());
//             Queue<BackOrder> newQueue = new Queue<>();
//             while (!data.backOrders.isEmpty()) {
//                BackOrder order = data.backOrders.remove();
//                newQueue.add(order);
//                System.out.printf("  customer: %s, amount: %d%n", order.customer, order.amount);
//             }
//             data.backOrders = newQueue;
//          }
//       }
//    }     
}