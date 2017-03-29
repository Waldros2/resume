import java.util.*;
import java.io.*;

/*
* A babyNames program that requires Driver to operate. It handles using hashMap and the linear structure within this class while using a AVL tree, it uses a seperate AVLtree class for
* implementation.
* Scott Waldron
* Chachi Lor
*/

public class babyNames{

//Loads the file into an ArrayList using a delimiter

  public static Collection<Node> linearize(String babyFile){
    long startTime = System.currentTimeMillis();
    Collection <Node> names = new ArrayList<Node>();
    File file = new File (babyFile);
    int mCount = 0;
    int fCount = 0;
    try {
      Scanner sc = new Scanner (file);
      while (sc.hasNext()){
        String curr = sc.next();
        String[] temp;
        String delimiter = ",";
        temp = curr.split(delimiter);
        if(temp[1].equals("F")){
          fCount++;
          Node node = new Node (temp[0], temp[1], Integer.parseInt(temp[2]), fCount);
          names.add(node);
        }
        else{
          mCount++;
          Node node = new Node (temp[0], temp[1], Integer.parseInt(temp[2]), mCount);
          names.add(node);
        }
      }
      sc.close();
    }
    catch (FileNotFoundException ex){
      System.out.println("Error: Unable to open file " + babyFile);
      System.exit(1);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to load in the names linearly.");
    return names;
  }

//Loads the file into an AVLtree using a delimiter

  public static AVLtree treeify(String babyFile){
    long startTime = System.currentTimeMillis();
    AVLtree tree = new AVLtree();
    File file = new File (babyFile);
    int mCount = 0;
    int fCount = 0;
    try {
      Scanner sc = new Scanner (file);
      while(sc.hasNext()){
        String curr = sc.next();
        String[] temp;
        String delimiter = ",";
        temp = curr.split(delimiter);
        if(temp[1].equals("F")){
          fCount++;
          Node node = new Node(temp[0], temp[1], Integer.parseInt(temp[2]), fCount);
          tree.insert(node);
        }
        else{
          mCount++;
          Node node = new Node (temp[0], temp[1], Integer.parseInt(temp[2]), mCount);
          tree.insert(node);
        }
      }
      sc.close();
    }
    catch(FileNotFoundException ex){
      System.out.println("Error: Unable to open file " + babyFile);
      System.exit(1);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to load in the names into a AVL tree.");
    return tree;
  }

//Loads the file into a hashMap containing only males using a delimiter

  public static HashMap <String, Node> hashMappingMale (String babyFile){
    HashMap <String, Node> namesMale = new HashMap <String, Node>();
    File file = new File (babyFile);
    int mCount = 0;
    try {
      Scanner sc = new Scanner (file);
      while (sc.hasNext()){
        String curr = sc.next();
        String[] temp;
        String delimiter = ",";
        temp = curr.split(delimiter);
        if(temp[1].equals("M")){
          mCount++;
          Node node = new Node (temp[0], temp[1], Integer.parseInt(temp[2]), mCount);
          namesMale.put(node.name, node);
        }
      }
      sc.close();
    }
    catch (FileNotFoundException ex){
      System.out.println("Error: Unable to open file " + babyFile);
      System.exit(1);
    }
    return namesMale;
  }

//Loads the file into a hashMap containing only females using a delimiter

  public static HashMap <String, Node> hashMappingFemale (String babyFile){
    HashMap <String, Node> namesFemale = new HashMap <String, Node>();
    File file = new File (babyFile);
    int fCount = 0;
    try {
      Scanner sc = new Scanner (file);
      while (sc.hasNext()){
        String curr = sc.next();
        String[] temp;
        String delimiter = ",";
        temp = curr.split(delimiter);
        if(temp[1].equals("F")){
          fCount++;
          Node node = new Node (temp[0], temp[1], Integer.parseInt(temp[2]), fCount);
          namesFemale.put(node.name, node);
        }
      }
      sc.close();
    }
    catch (FileNotFoundException ex){
      System.out.println("Error: Unable to open file " + babyFile);
      System.exit(1);
    }
    return namesFemale;
  }

//Search for a name linearly and print whether or not they are found
//Prints name, rank, gender and frequency if name exists for either gender

  public static void lineSearch(String target, Collection<Node> names){
    long startTime = System.currentTimeMillis();
    boolean mCheck = false;
    boolean fCheck = false;
    Node mNode = new Node();
    Node fNode = new Node();
    for(Node node: names){
      if(node.name.equals(target) && node.gender.equals("M")){
        mNode = node;
        mCheck = true;
      }
      if(node.name.equals(target) && node.gender.equals("F")){
        fNode = node;
        fCheck = true;
      }
    }
    if (mCheck == true){
      System.out.println("The name " + mNode.name + " had frequency " + mNode.frequency + " and rank "+ mNode.rank + " among boys of the current year.");
    }
    if (fCheck == true){
      System.out.println("The name " + fNode.name + " had frequency " + fNode.frequency + " and rank "+ fNode.rank + " among girls of the current year.");
    }
    if (mCheck == false && fCheck == false){
      System.out.println("The name " + target + " does not exist in the database.");
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to search the database linearly.");
  }

//Prints top 10 most popular names for each gender including frequency and overall percentage

  public static void linePopular(Collection<Node> names){
    long startTime = System.currentTimeMillis();
    Collection<Node> populars = new ArrayList<Node>();
    int total = 0;
    for(Node node: names){
      total += node.frequency;
      if(node.rank <= 10){
        populars.add(node);
      }
    }
    double sum = (double) total;
    for(Node node: populars){
      double sumPercent = 100.0*(node.frequency/sum);
      sumPercent = Math.round(sumPercent*100);
      sumPercent = sumPercent/100;
      System.out.println(node.name + ", " + node.frequency + ", " + "(" + sumPercent +"%)");
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to grab the popular names linearly.");
  }

//Prints 5 unique names for each gender including frequency and overall percentage.

  public static void lineUniqueName(Collection<Node> names){
    long startTime = System.currentTimeMillis();
    int mCount = 0;
    int fCount = 0;
    int total = 0;
    for(Node node: names){
      total += node.frequency;
    }
    double sum = (double) total;
    for(Node node: names){
      if(node.frequency == 5 && node.gender.equals("F") && fCount < 5){
        fCount++;
        double sumPercent = 100.0*(node.frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(node.name + " " + node.gender + ": " + node.frequency + " (" + sumPercent+ "%)");
      }
      if (node.frequency == 5 && node.gender.equals("M") && mCount < 5){
        mCount++;
        double sumPercent = 100.0*(node.frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(node.name + " " + node.gender + ": " + node.frequency + " (" + sumPercent+ "%)");
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to grab the unique names linearly.");
  }

//Prints the names alphabetically including frequency or both male and female and overall percentage

  public static void lineDisplay(Collection<Node> names){
    long startTime = System.currentTimeMillis();
    List <Node> sortedNames = new ArrayList<Node>();
    int total = 0;
    sortedNames.addAll(names);

    for(Node node: names){
      total += node.frequency;
    }
    double sum = (double) total;
    Collections.sort(sortedNames, new Comparator <Node>(){
      @Override
      public int compare(Node n1, Node n2){
	return n1.name.compareTo(n2.name);
      }
    });
    int i = 0;
    while (i < sortedNames.size()-1){
      if (sortedNames.get(i).name.equals(sortedNames.get(i+1).name)){
        double sumPercent = 100.0*((sortedNames.get(i).frequency + sortedNames.get(i+1).frequency)/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " M: " + sortedNames.get(i+1).frequency + " (" + sumPercent+ "%)");
        i = i+2;
      }
      else if (sortedNames.get(i).gender.equals("F")){
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
      else{
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " M: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to print the names linearly.");
  }

//Search for a name through a keyset and print whether or not they are found
//Prints name, rank, gender and frequency if name exists for either gender

  public static void hashSearch (HashMap <String, Node> males, HashMap <String, Node> females, String name){
    long startTime = System.currentTimeMillis();
    boolean mCheck = false;
    boolean fCheck = false;
    for (String key: males.keySet()){
      if (key.equals(name)){
        mCheck = true;
        System.out.println("The name " + key + " had frequency " + males.get(key).frequency + " and rank " + males.get(key).rank + " among boys of the current year.");
      }
    }
    for (String key : females.keySet()){
      if (key.equals(name)){
        fCheck = true;
        System.out.println ("The name " + key + " had frequency " + females.get(key).frequency + " and rank " + females.get(key).rank + " among girls of the current year.");
      }
    }
    if (mCheck == false && fCheck == false){
      System.out.println("The name " + name + " does not exist in the database.");
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to search the database with HashMap.");
  }

//Prints top 10 most popular names for each gender including frequency and overall percentage

  public static void hashPopular (HashMap <String, Node> males, HashMap <String, Node> females){
    long startTime = System.currentTimeMillis();
    Collection<Node> populars = new ArrayList<Node>();
    int total = 0;
    for(String key: males.keySet()){
      total += males.get(key).frequency;
      if(males.get(key).rank <= 10){
        populars.add(males.get(key));
      }
    }
    for(String key: females.keySet()){
      total += females.get(key).frequency;
      if(females.get(key).rank <= 10){
        populars.add(females.get(key));
      }
    }
    double sum = (double) total;
    for(Node node: populars){
      double sumPercent = 100.0*(node.frequency/sum);
      sumPercent = Math.round(sumPercent*100);
      sumPercent = sumPercent/100;
      System.out.println(node.name + ", " + node.gender + ", " + node.frequency + ", " + "(" + sumPercent +"%)");
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to grab the popular names with a HashMap.");
  }

//Prints 5 unique names for each gender including frequency and overall percentage.

  public static void hashUniqueName(HashMap <String, Node> males, HashMap <String, Node> females){
    long startTime = System.currentTimeMillis();
    int mCount = 0;
    int fCount = 0;
    int total = 0;
    for(String key: males.keySet()){
      total += males.get(key).frequency;
    }
    for(String key: females.keySet()){
      total += females.get(key).frequency;
    }
    double sum = (double) total;
    for(String key: females.keySet()){
      if(females.get(key).frequency == 5 && fCount < 5){
        fCount++;
        double sumPercent = 100.0*(females.get(key).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(females.get(key).name + " " + females.get(key).gender + ": " + females.get(key).frequency + " (" + sumPercent+ "%)");
      }
    }
    for (String key: males.keySet()){
        if (males.get(key).frequency == 5 && mCount < 5){
          mCount++;
          double sumPercent = 100.0*(males.get(key).frequency/sum);
          sumPercent = Math.round(sumPercent*100);
          sumPercent = sumPercent/100;
          System.out.println(males.get(key).name + " " + males.get(key).gender + ": " + males.get(key).frequency + " (" + sumPercent+ "%)");
        }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took "+ (endTime - startTime) + " milliseconds to grab the unique names with a HashMap.");
  }

//Prints the names alphabetically including frequency or both male and female and overall percentage by looking through both HashMaps and sorting first.

  public static void hashDisplay(HashMap <String, Node> males, HashMap <String, Node> females){
    long startTime = System.currentTimeMillis();
    List <Node> sortedNames = new ArrayList<Node>();
    int total = 0;
    for(String key: males.keySet()){
      total += males.get(key).frequency;
      sortedNames.add(males.get(key));
    }
    for(String key: females.keySet()){
      total += females.get(key).frequency;
      sortedNames.add(females.get(key));
    }
    double sum = (double) total;
    Collections.sort(sortedNames, new Comparator <Node>(){
      @Override
      public int compare(Node n1, Node n2){
	return n1.name.compareTo(n2.name);
      }
    });

    int i = 0;
    while (i < sortedNames.size()-1){
      if (sortedNames.get(i).name.equals(sortedNames.get(i+1).name)){
        double sumPercent = 100.0*((sortedNames.get(i).frequency + sortedNames.get(i+1).frequency)/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " M: " + sortedNames.get(i+1).frequency + " (" + sumPercent+ "%)");
        i = i+2;
      }
      else if (sortedNames.get(i).gender.equals("F")){
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
      else{
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " M: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to print the names with a HashMap.");
  }

//Search for a name through the tree starting with the root. Call inOrderSearch found in the AVLtree class.

  public static void treeSearch (AVLtree tree, String name){
    long startTime = System.currentTimeMillis();
    tree.inOrderSearch(name);
    if (tree.found == false){
      System.out.println("The name " + name + " does not exist in the database.");
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to search for the name using an AVL tree.");
  }

//Display all the names in the tree by adding them into a List through an in Order Search and then sorting.

  public static void treeDisplay (AVLtree tree){
    long startTime = System.currentTimeMillis();
    ArrayList <Node> sortedNames = new ArrayList<Node>();
    tree.inOrder(sortedNames);
    int total = tree.total;
    double sum = (double) total;
    Collections.sort(sortedNames, new Comparator <Node>(){
      @Override
      public int compare(Node n1, Node n2){
	return n1.name.compareTo(n2.name);
      }
    });

    int i = 0;
    while (i < sortedNames.size()-1){
      if (sortedNames.get(i).name.equals(sortedNames.get(i+1).name)){
        double sumPercent = 100.0*((sortedNames.get(i).frequency + sortedNames.get(i+1).frequency)/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " M: " + sortedNames.get(i+1).frequency + " (" + sumPercent+ "%)");
        i = i+2;
      }
      else if (sortedNames.get(i).gender.equals("F")){
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " F: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
      else{
        double sumPercent = 100.0*(sortedNames.get(i).frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(sortedNames.get(i).name + " M: " + sortedNames.get(i).frequency + " (" + sumPercent+ "%)");
        i = i + 1;
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to print the names using an AVL tree.");
  }

//Calls popular in the AVLtree class to find the top 10 most popular names per gender

  public static void treePopular(AVLtree tree){
    long startTime = System.currentTimeMillis();
    tree.popular();
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to print the most popular names using an AVL tree.");
  }

//Calls unique in the AVLtree class to find 5 most unique names per gender

  public static void treeUniqueName(AVLtree tree){
    long startTime = System.currentTimeMillis();
    tree.unique();
    long endTime = System.currentTimeMillis();
    System.out.println("It took " + (endTime - startTime) + " milliseconds to print the most unique names using an AVL tree.");
  }
}
