public class examPractice{

    private class car<G>{
	G car;
	String color, model;
	int year;
	double price;

	public car(String color, String model, int year, double price){
	    this.color = color;
	    this.model = model;
	    this.year = year;
	    this.price = price;
	}
    }
    car<String> car = new car (black, mercedes, 2010, 29,000);

    public static void main(String args[]){
	System.out.println("the car is" + car.color + car.model + car.year + car.price);
    }
}
