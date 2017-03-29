/*
Vertex class for implementation
Scott Waldron
*/
import java.util.*;

/*
I realized I had to implement Comparable because if I am creating a PriorityQueue of type <Vertex>, it needs something to compare it to.
*/

public class Vertex implements Comparable<Vertex>{
  public String name;
  public int minDistance = Integer.MAX_VALUE;
  public int minTime = Integer.MAX_VALUE;
  public int minPrice = Integer.MAX_VALUE;
  public Vertex previous;
  public ArrayList<Edge> adjList = new ArrayList<Edge>();

  public Vertex(String name){
    this.name = name;
  }
  public Vertex(Vertex vertex){
    this.name = vertex.name;
    this.minDistance = vertex.minDistance;
    this.minTime = vertex.minTime;
    this.minPrice = vertex.minPrice;
    this.previous = vertex.previous;
    this.adjList = vertex.adjList;
  }
  @Override
  public String toString(){
    return this.name;
  }
  public int compareTo(Vertex different){
    return Integer.compare(minDistance, different.minDistance);
  }
  public void reset(){
    this.minDistance = Integer.MAX_VALUE;
    this.minTime = Integer.MAX_VALUE;
    this.minPrice = Integer.MAX_VALUE;
  }
  public int getWeight(int i){
    if (i == 1){
      return minDistance;
    }
    else if (i == 2) {
      return minTime;
    }
    else{
      return minPrice;
    }
  }
}
