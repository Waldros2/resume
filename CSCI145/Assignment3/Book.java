class Book {

   public class InsufficientStock extends IllegalArgumentException {
      public InsufficientStock(String msg) {
         super(msg);
      }
   }   

   private String isbn;
   private double price;
   private int stock;
   
   Book(String isbn, double price, int stock) {
      this.isbn = isbn;
      this.price = price;
      this.stock = stock;
   }
   
   String getIsbn() {
      return isbn;
   }
   
   double getPrice() {
      return price;
   }
   
   int getStock() {
      return stock;
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

