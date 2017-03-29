import java.util.*;
import java.io.*;

/*
 * Driver program that reads in a chart of baby names and prompts user for desired data structure and want information to extract.
 * Scott Waldron
 * Chachi Lor
 */
public class Driver{

  public static void main(String[] args) {
    if(args.length  != 1){
      System.err.println("USAGE: java Driver <name_file>");
      System.exit(1);
    }
    String babyFile = args[0];

    for(;;) {
      System.out.print("What is desired data structure? (1 = tree, 2 = hashMap, 3 = linear data structure) ");
      Scanner sc = new Scanner(System.in);
      int choice = sc.nextInt();
      if(choice < 1 || choice > 3){
        System.out.println("Invalid option");
        System.exit(2);
      }
      if (choice == 1){
        AVLtree tree = new AVLtree();
        tree = babyNames.treeify(babyFile);
        System.out.print("What information is wanted? (1 = search for a name, 2 = most popular name, 3 = unique name, 4 = display name) ");
        int choice2 = sc.nextInt();
        if(choice2 < 1 || choice2 > 4){
          System.out.println("Invalid option");
          System.exit(2);
        }
        if(choice2 == 1){
          System.out.println("What name are you searching for?");
          String name = sc.next();
          babyNames.treeSearch(tree, name);
        }
        else if(choice2 == 2){
          babyNames.treePopular(tree);
        }
        else if(choice2 == 3){
          babyNames.treeUniqueName(tree);
        }
        else{
          babyNames.treeDisplay(tree);
        }
      }
      else if (choice == 2){
        long startTime = System.currentTimeMillis();
        HashMap <String, Node> hashMapMale = new HashMap <String, Node>();
        HashMap <String, Node> hashMapFemale = new HashMap <String, Node>();
        hashMapMale = babyNames.hashMappingMale(babyFile);
        hashMapFemale = babyNames.hashMappingFemale(babyFile);
        long endTime = System.currentTimeMillis();
        System.out.println("It took "+ (endTime - startTime) + " milliseconds to load in the names with a HashMap.");

        System.out.print("What information is wanted? (1 = search for a name, 2 = most popular name, 3 = unique name, 4 = display name) ");
        int choice2 = sc.nextInt();
        if(choice2 < 1 || choice2 > 4){
          System.out.println("Invalid option");
          System.exit(2);
        }
        if(choice2 == 1){
          System.out.println("What name are you searching for?");
          String name = sc.next();
          babyNames.hashSearch(hashMapMale, hashMapFemale, name);
        }
        else if(choice2 == 2){
          babyNames.hashPopular(hashMapMale, hashMapFemale);
        }
        else if(choice2 == 3){
          babyNames.hashUniqueName(hashMapMale, hashMapFemale);
        }
        else{
          babyNames.hashDisplay(hashMapMale, hashMapFemale);
        }
      }
      else{
        Collection<Node> names = new ArrayList<Node>();
        names = babyNames.linearize(babyFile);
        System.out.print("What information is wanted? (1 = search for a name, 2 = most popular name, 3 = unique name, 4 = display name) ");
        int choice2 = sc.nextInt();
        if(choice2 < 1 || choice2 > 4){
          System.out.println("Invalid option");
          System.exit(2);
        }
        if(choice2 == 1){
          System.out.println("What name are you searching for?");
          String name = sc.next();
          babyNames.lineSearch(name, names);
        }
        else if(choice2 == 2){
          babyNames.linePopular(names);
        }
        else if(choice2 == 3){
          babyNames.lineUniqueName(names);
        }
        else{
          babyNames.lineDisplay(names);
        }
      }
    }
  }
}
