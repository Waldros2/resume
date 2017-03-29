/*
edge class for implementation
Scott Waldron
*/

public class Edge{
	public Vertex end;
	public int distance;
	public  int time;
	public  int price;

	@Override
	public String toString(){
		return this.end.name;
	}
	public Edge(Vertex end, String distance, String time, String price){
		this.end = end;
		this.distance = Integer.parseInt(distance);
		this.time = Integer.parseInt(time);
		this.price = Integer.parseInt(price);
	}
}
