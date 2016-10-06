/*
 * WWU: CS145 Winter 2014, Lab5
 *
 * Book.java
 *
 * Modified by Chris Reedy (Chris.Reedy@wwu.edu).
 *
 * A basic class for a Book object.
 *
 * Dependencies:
 *    Book.java, provided
 *    BookCollection.java, to be completed by students
 */

public class Book{

   public class InsufficientStock extends IllegalArgumentException {
      public InsufficientStock(String msg) {
         super(msg);
      }
   }

	// Fields for the Book class
	private String title;
	private String author;
	private String publisher;
	private int year;
	private int edition;
	private String isbn;
	private double price;
	private int stock;
	private String format;

	/*
    * Construct a new book.
    *
    * Construct a Book object with the given data. The stock for the book
    * is set to zero.
    */
	Book(String title, String author, String publisher, int year, int
                         edition, String isbn, double price, String format){

		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.edition = edition;
		this.isbn = isbn;
		this.price = price;
		this.stock = 0;
		this.format = format; 
	}

   /*
    * Construct a new Book object by copying the data from an existing
    * Book object.
    */
   public Book(Book other) {
      this.title = other.title;
		this.author = other.author;
		this.publisher = other.publisher;
		this.year = other.year;
		this.edition = other.edition;
		this.isbn = other.isbn;
		this.price = other.price;
		this.stock = other.stock;
	   this.format = other.format;
   }

	/* Return book title */
	public String getTitle(){
		return title;
	}

	/* Return book author */
	public String getAuthor(){
		return author;
	}

	/* Return book publisher */
	public String getPublisher(){
		return publisher;
	}

	/* Return book year of publication */
	public int getPublicationYear(){
		return year;
	}

	/* Return book edition */
	public int getEdition(){
		return edition;
	}

	/* Return International Standard Book Number (ISBN) */
	public String getIsbn(){
		return isbn;
	}

	/* Return book price */
	public double getPrice(){
		return price;
	}

	/* Return book stock on hand */
	public int getStock(){
		return stock;
	}

	/* Return book format */
	public String getFormat(){
		return format;
	}

	/* Return the value of book stock on hand.
    *
    * The value is computed as the stock on hand times
    * the price.
    */
	public double getStockValue(){
		return stock * price;
	}

	/* Return a string representation of the book. */
	public String toString(){

      String s = String.format(
            "%-30s %-30s %-30s %-4d %-10s %5.2f %-2d %-10s",
            title, author, publisher, year, isbn, price, stock, format);

		return s;
	}

	/* Set the price */
	public void setPrice(double newPrice){
		price = newPrice;
	}

	/* Change the stock on hand.
    *
    * The parameter change is the change is stock on hand. Thus,
    * the new stock is the old stock + change. An InsufficientStock
    * exception is thrown if the total stock would become negative.
    */
	public void changeStock(int change){

		if( (stock + change) < 0 ){
			throw new InsufficientStock(
               "Cannot sell " + (-change) + " books with only " + stock + "on hand");
		} else {
			stock += change;
		}
	}
}
