/*
 * WWU: CS145 Winter 2016, Lab5
 *
 * BookCollection.java
 *
 * Modified by David Palzer
 *
 * Maintain a collection of books.
 *
 * Dependencies:
 *    Book.java, provided
 */

public class BookCollection{

   public class BookNotFound extends IllegalArgumentException {
      public BookNotFound(String msg) {
         super(msg);
      }
   }

   public class DuplicateBook extends IllegalArgumentException {
      public DuplicateBook(String msg) {
         super(msg);
      }
   }

   public class CollectionFull extends IllegalStateException {
      public CollectionFull(String msg) {
         super(msg);
      }
   }

   // Limit on size of collection.
   public static final int LIMIT = 200;
   private Book[] bookArray;
   private int size;
   private int currentSize;
   
   public BookCollection( int size){
        this.size = size;
        this.bookArray = new Book[size];
        this.currentSize = 0;
   }
   
   addBook(Book book){
    this.bookArray[currentSize] = book;
    currentSize++;
   }
   // TODO: Other data fields as needed.
	
   // Sample method: changePrice
   /* Change the price of a book.
    * 
    * Change the price of the book specified by the given ISBN to
    * the given price. If the isbn cannot be found in the collection
    * a BookNotFound exception is thrown.
    */
   public void changePrice(String isbn, double price) {
      Book theBook = findBook(isbn);
      if (theBook == null) {
         throw new BookNotFound(isbn);
      }
      theBook.setPrice(price);
   }
   
	// TODO: Provide constructor and other methods.
}
