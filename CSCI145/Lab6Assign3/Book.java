class Book {

   public class InsufficientStock extends IllegalArgumentException {
      public InsufficientStock(String msg) {
         super(msg);
      }
   }   

   private String isbn;
   private double price;
   private int stock;
   private Queue<Order> orders;
   static double totalValue;
   
   Book(String isbn, double price, int stock, Queue<Order> orders) {
      this.isbn = isbn;
      this.price = price;
      this.stock = stock;
      this.orders = new Queue<Order>();
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
   static double getTotalValue(){
	   return totalValue;
   }
   //Adds to stock if shipment receieved and gives to front of queue if book is on back order
	public void changeStock(int change){
		System.out.println("Stock for book " + this.isbn + " has increased from " + this.stock + " to " + (this.stock+change));
		this.stock += change;
		
			while(!orders.isEmpty() && this.stock > 0){
				if (this.stock > orders.peek().quantity){
					System.out.println("Back order filled for customer " + orders.peek().customerID + " for " + orders.peek().quantity + " copies of book " + this.isbn);
					totalValue += orders.peek().quantity*this.price;
					stock -= orders.peek().quantity;
					orders.remove();
				}else if (this.stock < orders.peek().quantity){
					System.out.println("Back order filled for customer " + orders.peek().customerID + " for " + this.stock + " copies of book " + this.isbn);
					totalValue += this.stock*this.price;
					int update = orders.peek().quantity - stock;
					this.stock = 0;
					orders.peek().quantity = update;
				}
			}
   }
	//Fills orders completely or gives what is on hand and creates a back order queue for the remainder
	public void fillOrder(Order order){
		if (stock>= order.quantity){
			System.out.println("Order filled for customer " + order.customerID + " for " + order.quantity + " copies of book " + this.isbn);
			totalValue += order.quantity*this.price;
			this.stock -= order.quantity;
		}
		else if(this.stock > 0 && this.stock < order.quantity){
        	System.out.println("Order filled for customer " + order.customerID + " for " + this.stock + " copies of book " + this.isbn);
            totalValue += this.stock*this.price;
        	order.quantity -= this.stock;
			System.out.println("Back order for customer " + order.customerID + " for " + order.quantity + " copies of book " + this.isbn);
			this.stock = 0;
			orders.add(order);
			//changeStock(order.quantity, "ORDER");
		}else{
			orders.add(order);
			System.out.println("Back order for " + order.customerID + " for " +  order.quantity + " copies of book " + this.isbn);
			//changeStock(order.quantity, "ORDER");
		}
	}
}

