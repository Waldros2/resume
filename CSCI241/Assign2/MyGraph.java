import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 * Add your name here: Scott Waldron
 */
public class MyGraph {

/*
Takes the vertexfile and loads the information into a Collection of type <Vertex>.
*/

public static Collection<Vertex> loadVertices(String vertexfile) {
  Collection <Vertex> vertices = new ArrayList<Vertex>();
  File file = new File(vertexfile);
  try {
    Scanner sc = new Scanner (file);
    while (sc.hasNext()){
      Vertex vertex = new Vertex (sc.next());
      vertices.add(vertex);
    }
    sc.close();
  }
  catch (FileNotFoundException ex){
    System.out.println("Error: Unable to open file " + vertexfile);
    System.exit(1);
  }
  return vertices;
}

/*
Takes the edgefile and my vertex collection and loads the edges into the vertex's adjlist.
*/

public static void loadEdges(String edgefile, Collection<Vertex> vertices) {
  File file = new File(edgefile);
  try {
    Scanner sc = new Scanner (file);
    while (sc.hasNext()){
	     Vertex copy = new Vertex("Copy");
       String source = sc.next().toString();
       String target = sc.next().toString();
       String distance = sc.next().toString();
       String time = sc.next().toString();
       String price = sc.next().toString();
       for(Vertex vertex: vertices){
         if (vertex.name.equals(target)){
           copy = vertex;
         }
       }
       for(Vertex vertex: vertices){
         if (vertex.name.equals(source)){
           vertex.adjList.add(new Edge(copy, distance, time, price));
         }
       }
    }
    sc.close();
  }
  catch (FileNotFoundException ex){
    System.out.println("Error: Unable to open file " + edgefile);
    System.exit(1);
  }
  return;
}

/*
Prints the graph's vertices from my collection of type <Vertex>.
*/

public static void displayVertices(Collection<Vertex> vertices) {
  for(Vertex vertex: vertices){
    System.out.println(vertex.name);
  }
  System.out.println();
  return;
}

/*
Prints the graph's edges from my collection of type <Vertex> by looking at the adjList of each vertex.
*/

public static void displayEdges(Collection<Vertex> vertices) {
  for(Vertex vertex: vertices){
    System.out.println(vertex.name+" to:");
    for (Edge edge: vertex.adjList){
      System.out.println(edge.end.name+ " "+ edge.distance+" "+edge.time+" "+edge.price);
    }
  }
  System.out.println();
  return;
}

/*
Prints the graph's adjacencyList from my collection of type <Vertex> by printing the name and the adjList.
*/

public static void displayGraph(Collection<Vertex> vertices) {
  for(Vertex vertex: vertices){
    System.out.println(vertex.name + ": "+ vertex.adjList);
  }
  return;
}

/*
I don't implement this function as I was told I can use object orientated programming and can manipulate the Routes.java file. I find and load adjacent vertices
into my vertex adjList when I read in the edgefile. I have brought down the code above to demonstrate where I do this.
*/
public static void findAdjacentVertices(Collection<Vertex> vertices, String edgefile) {
  File file = new File(edgefile);
  try {
    Scanner sc = new Scanner (file);
    while (sc.hasNext()){
	     Vertex copy = new Vertex("Copy");
       String source = sc.next().toString();
       String target = sc.next().toString();
       String distance = sc.next().toString();
       String time = sc.next().toString();
       String price = sc.next().toString();
       for(Vertex vertex: vertices){                  //here I find the correct vertex
         if (vertex.name.equals(target)){
           copy = vertex;
         }
       }
       for(Vertex vertex: vertices){                  //here I create a new edge in the adjList of that vertex
         if (vertex.name.equals(source)){
           vertex.adjList.add(new Edge(copy, distance, time, price));
         }
       }
    }
    sc.close();
  }
  catch (FileNotFoundException ex){
    System.out.println("Error: Unable to open file " + edgefile);
    System.exit(1);
  }
  return;
}

/*
I don't implement checkIsAdjacent because I was told I can use object orientated programming and can manipulate the Routes.java file. I have filled it out with
how to implement it but I never call this function.
*/
public static int[] checkIsAdjacent(Vertex a, Vertex b, Collection<Vertex> vertices){
  int[] values = new int[3];
  values[0] = -1;
  values[1] = -1;
  values[2] = -1;
  for(Edge edge: a.adjList){
    if (edge.end.name.equals(b.name)){
      values[0] = edge.distance;
      values[1] = edge.time;
      values[2] = edge.price;
    }
  }
  return values;
}
/*
findShortestRoute does all the computing power. It uses a PriorityQueue to find the shortest path to a target looking at distance. Once finished, it updates
end_point with a final minDistance. If the route doesn't exist, it returns a -1. It also backtracks it's way through the previous vertices and adds them to
a list to print out the route.
*/
public static int findShortestRoute(Vertex start_point, Vertex end_point, List<Vertex> route, Collection<Vertex> vertices) {
  start_point.minDistance = 0;
	PQ possiblePaths = new PQ(1, vertices.size());
	possiblePaths.insert(start_point);

	while(!possiblePaths.isEmpty()){
	   Vertex a = possiblePaths.extractMin();
     for (Edge edge: a.adjList){
       Vertex b = edge.end;
       int distance = edge.distance;
       int distanceWithA = a.minDistance + distance;
       if (distanceWithA < b.minDistance){
         possiblePaths.remove(b);
			   b.minDistance = distanceWithA;
				 b.previous = a;
				 possiblePaths.insert(b);
			}
		}
	}
  start_point = end_point;
  while(start_point.previous != null){
    route.add(start_point);
    start_point = start_point.previous;
  }
  Collections.reverse(route);
  if(end_point.minDistance < Integer.MAX_VALUE){
    return end_point.minDistance;
  }
  else{
    return -1;
  }
}
/*
findCheapestRoute does all the computing power. It uses a PriorityQueue to find the shortest path to a target looking at price. Once finished, it updates
end_point with a final minPrice. If the route doesn't exist, it returns a -1. It also backtracks it's way through the previous vertices and adds them to
a list to print out the route.
*/
public static int findCheapestRoute(Vertex start_point, Vertex end_point, List<Vertex> route, Collection<Vertex> vertices) {
  start_point.minPrice = 0;
	PQ possiblePaths = new PQ(2, vertices.size());
	possiblePaths.insert(start_point);

	while(!possiblePaths.isEmpty()){
	   Vertex a = possiblePaths.extractMin();
     for (Edge edge: a.adjList){
       Vertex b = edge.end;
       int price = edge.price;
       int priceWithA = a.minPrice + price;
       if (priceWithA < b.minPrice){
         possiblePaths.remove(b);
			   b.minPrice = priceWithA;
				 b.previous = a;
				 possiblePaths.insert(b);
			}
		}
	}
  start_point = end_point;
  while(start_point.previous != null){
    route.add(start_point);
    start_point = start_point.previous;
  }
  Collections.reverse(route);
  if(end_point.minPrice < Integer.MAX_VALUE){
    return end_point.minPrice;
  }
  else{
    return -1;
  }
}
/*
findFastestRoute does all the computing power. It uses a PriorityQueue to find the fastest path to a target looking at distance. Once finished, it updates
end_point with a final minTime. If the route doesn't exist, it returns a -1. It also backtracks it's way through the previous vertices and adds them to
a list to print out the route.
*/
public static int findFastestRoute(Vertex start_point, Vertex end_point, List<Vertex> route, Collection<Vertex> vertices) {
  start_point.minTime = 0;
  PQ possiblePaths = new PQ(3, vertices.size());
  possiblePaths.insert(start_point);

  while(!possiblePaths.isEmpty()){
     Vertex a = possiblePaths.extractMin();
     for (Edge edge: a.adjList){
       Vertex b = edge.end;
       int time = edge.time;
       int timeWithA = a.minTime + time;
       if (timeWithA < b.minTime){
         possiblePaths.remove(b);
         b.minTime = timeWithA;
         b.previous = a;
         possiblePaths.insert(b);
      }
    }
  }
  start_point = end_point;
  while(start_point.previous != null){
    route.add(start_point);
    start_point = start_point.previous;
  }
  Collections.reverse(route);
  if(end_point.minTime < Integer.MAX_VALUE){
    return end_point.minTime;
  }
  else{
    return -1;
  }
}

}
