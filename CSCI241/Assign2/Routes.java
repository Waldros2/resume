import java.util.*;
import java.io.*;
import java.util.Scanner;

/*
 * Driver program that reads in a graph and prompts user for source and destination for the desired travel route.
 * I have changed this file as I am using object orientated programming.
 * Add your name here: Scott Waldron
 */

public class Routes {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("USAGE: java Routes <vertex_file> <edge_file>");
			System.exit(1);
		}


      Scanner s = null;

      String vertexfile        = args[0];
      String edgefile           = args[1];

      // Open and Read the vertex file
		Collection<Vertex> vertex = new ArrayList<Vertex>();
		vertex = MyGraph.loadVertices(vertexfile);

      // Open and Read the edge file
		MyGraph.loadEdges(edgefile,vertex);

			// Display the vertices
		System.out.println("Vertices in the graph are: \n");
		MyGraph.displayVertices(vertex);

      // Display the edges with their weights
    System.out.println("Edges in the graph with their weights (distance, time, price) are: \n");
    MyGraph.displayEdges(vertex);

      // Display graph
    MyGraph.displayGraph(vertex);

      // Construct the Preferred Route
      Scanner console = new Scanner(System.in);

		for(;;) {
			System.out.print("What is your Start vertex? ");
			Vertex start_point = new Vertex(console.nextLine());
			for(Vertex node: vertex){
				if (start_point.name.equals(node.name)){
					start_point = node;
				}
			}
			if(!vertex.contains(start_point)) {
				System.out.println("No such vertex in the graph.\n");
				System.exit(0);
			}

			System.out.print("What is your Destination vertex? ");
			Vertex end_point = new Vertex(console.nextLine());
			for(Vertex node: vertex){
				if (end_point.name.equals(node.name)){
					end_point = node;
				}
			}
			if(!vertex.contains(end_point)) {
				System.out.println("No such vertex in the graph\n");
				System.exit(1);
			}
         System.out.print("What is your Optimization parameter? (1 = shortest route, 2 = fastest route, 3 = cheapest route, 4 = display all options) ");
			Scanner in = new Scanner(System.in);
         int choice = in.nextInt();
			if(choice < 1 || choice > 4) {
				System.out.println("Invalid option");
				System.exit(2);
			}


			if (choice == 4) {
   			System.out.println("Shortest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> shortestRoute = new ArrayList<Vertex>();
   			int length = MyGraph.findShortestRoute(start_point, end_point, shortestRoute, vertex);
				System.out.print(start_point + " ");
				for(Vertex hop : shortestRoute)
				System.out.print(hop.toString()+" ");
   			System.out.println(length);
				for(Vertex node: vertex){
					node.reset();
				}

            System.out.println("Fastest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> fastestRoute = new ArrayList<Vertex>();
   			int total_time = MyGraph.findFastestRoute(start_point, end_point, fastestRoute, vertex);
				System.out.print(start_point + " ");
				for(Vertex hop : fastestRoute)
   			System.out.print(hop.toString()+" ");
   			System.out.println(total_time);
				for(Vertex node: vertex){
					node.reset();
				}


          System.out.println("Cheapest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> cheapestRoute = new ArrayList<Vertex>();
   			int price = MyGraph.findCheapestRoute(start_point, end_point, cheapestRoute, vertex);
				System.out.print(start_point + " ");
				for(Vertex hop : cheapestRoute)
   				System.out.print(hop.toString()+" ");
   			System.out.println(price);

				shortestRoute.clear();
				fastestRoute.clear();
				cheapestRoute.clear();
         }
         else if (choice == 1){
            System.out.println("Shortest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> route = new ArrayList<Vertex>();
   			int length = MyGraph.findShortestRoute(start_point, end_point, route, vertex);
				System.out.print(start_point + " ");
				for(Vertex hop : route)
   				System.out.print(hop.toString()+" ");
   			System.out.println("With a total distance of: " + length);
				route.clear();

         }
         else if (choice == 2){
           System.out.println("Fastest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> route = new ArrayList<Vertex>();
   			int total_time = MyGraph.findFastestRoute(start_point, end_point, route, vertex);
				System.out.print(start_point + " ");
				for(Vertex hop : route)
   				System.out.print(hop.toString()+" ");
   			System.out.println(total_time);
				route.clear();
         }
         else {
            System.out.println("Cheapest route from "+start_point+" to "+end_point+" is:\n");
   			List<Vertex> route = new ArrayList<Vertex>();
   			int price = MyGraph.findCheapestRoute(start_point, end_point, route, vertex);
				System.out.println(start_point);
				for(Vertex hop : route)
   			System.out.print(hop.toString()+" ");
   			System.out.println(price);
				route.clear();
         }
				 for(Vertex node: vertex){
					 node.reset();
				 }


		}

	}


}
