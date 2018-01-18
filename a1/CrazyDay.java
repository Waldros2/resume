import java.io.File;
import java.util.Scanner;

class CrazyDay{
	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("CrazyDay usage: java CrazyDay <inputfile.txt>");
		}
		else{
			Record record;
			record = new Record();
			File file = 
		}
	
	}

	private static class Record{
		
		String ticker;
		String Date;
		float openingPrice;
		float highPrice;
		float lowPrice;
		float closingPrice;
		int sharesTraded;
		float adjClosingPrice;
	
		private Record(){
			
			this.ticker = "";
			this.Date = "";
			this.openingPrice = 0;
			this.highPrice = 0;
			this.lowPrice = 0;
			this.closingPrice = 0;
			this.sharesTraded = 0;
			this.adjClosingPrice = 0;
		}
	
	}
}
