/*
 * WWU: CS145 Winter 2016, Lab5
 *
 * BookCollection.java
 *
 * Modified by Scott Waldron
 *
 * Maintain a collection of books.
 *
 * Dependencies:
 *    Book.java, provided
 */
import java.lang.IndexOutOfBoundsException;

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
   //Creates a BookCollection of an array size
   public BookCollection(int size){
        this.size = size;
        this.bookArray = new Book[size];
        this.currentSize = 0;
   }
   //Adds a book to the current array
   public void addBook(Book book){
    this.bookArray[currentSize] = book;
    currentSize++;
   }
   //changePrice of given isbn
   public void changePrice(String isbn, double price) {
      Book theBook = findBook(isbn);
      if (theBook == null) {
        throw new BookNotFound(isbn);
      }
     theBook.setPrice(price);
   }
  //adds up value of stock
   public double getStockValue(){
	   double StockValue = 0;
	   for (int i = 0; i <bookArray.length; i++){
		   if (bookArray[i] != null)
		   StockValue += bookArray[i].getStockValue();
	   }
	   return StockValue;
   }
   //Given an isbn, finds a book in bookArray
   private Book findBook(String isbn){
	   for (int i = 0; i <bookArray.length; i++){
		   if (bookArray[i] != null)
			   if (isbn.equals(bookArray[i].getIsbn())){
			return bookArray[i]; 
		   }
	   }
	   return null;
   }
   //changes Stock with an update file
   public void changeStock(String isbn, int quantity){
	   Book theBook = findBook(isbn);
	   if (theBook == null){
		   throw new BookNotFound(isbn);
	   }
	   theBook.changeStock(quantity);
   }
   //getter for size of array
   public int getSize(){
	   return this.currentSize;
   }
   //getter for book at index i in array
   public Book objectAt(int i){
	   try {return bookArray[i];
	   }
	   catch (IndexOutOfBoundsException ex){
		   return null;
	   }
   }
   //merges the two collections together, adding stock and taking lowest price with like ISBNs. If no match, adds the book to new collection.
   public static BookCollection merge(BookCollection collection1, BookCollection collection2){
	   int CollectionSize1 = collection1.getSize();
	   int CollectionSize2 = collection2.getSize();
	   int CollectionSize3 = CollectionSize1 + CollectionSize2;
	   BookCollection collection3 = new BookCollection(CollectionSize3);
	   
	   for (int i = 0; i < CollectionSize1; i++){
		   Book newBook = new Book(collection1.bookArray[i]);
		   collection3.addBook(newBook); 
	   }
	   for (int i = 0; i < CollectionSize2; i++){
		   boolean check = false;
		   Book targetBook = collection2.bookArray[i];
		   
		   for(int j = 0; j < CollectionSize1; j++){
			   if(targetBook.getIsbn().equals(collection3.bookArray[j].getIsbn())){
				   check = true;
				   double targetPrice;
				   if (targetBook.getPrice() > collection3.bookArray[j].getPrice()){
					   targetPrice = collection3.bookArray[j].getPrice();
				   }else{
					   targetPrice = targetBook.getPrice();
				   }
				   collection3.bookArray[j].changeStock(targetBook.getStock());
				   collection3.bookArray[j].setPrice(targetPrice);
			   }
		   	}
   			if(check == false){
   			collection3.addBook(targetBook);
	   		}
	   }
	   return collection3;
   }
}
