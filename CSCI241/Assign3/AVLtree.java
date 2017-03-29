import java.util.ArrayList;
import java.util.List;
/*
* AVLtree class for implementation of an efficient Binary Search Tree
* Scott Waldron
* Chachi Lor
*/

public class AVLtree{

  private Node root;
  public boolean found = false;
  public int total;
  public int mCount;
  public int fCount;
  public AVLtree(){
    root = null;
  }
  public boolean isEmpty(){
    return root == null;
  }
  public void insert(Node node){
    insert(this.root, node);
  }

//Find a place to insert new Node. Once found, adjusts pointers and calls balance to maintain AVLtree height restriction.

  private void insert (Node r, Node n){
    if(r == null){
      this.root = n;
    }
    else {
      if(n.frequency <= r.frequency){
        if(r.leftChild == null){
          r.leftChild = n;
          n.parent = r;
          balance(r);
        }
        else {
          insert(r.leftChild, n);
        }
      }
      else{
        if(r.rightChild == null){
          r.rightChild = n;
          n.parent = r;
          balance(r);
        }
        else{
          insert(r.rightChild, n);
        }
      }
    }
  }

//Checks the current balance of node and makes adjustments based on the recieved number difference

  private void balance(Node current){
    findBalance(current);
    int balance = current.balance;

    if(balance == -2){
      if(height(current.leftChild.leftChild) >= height(current.leftChild.rightChild)){
        current = rotateRight(current);
      }
      else{
        current = leftRightDoubleRotate(current);
      }
    }
    else if(balance == 2) {
      if (height(current.rightChild.rightChild) >= height(current.rightChild.leftChild)){
        current = rotateLeft(current);
      }
      else{
        current = rightLeftDoubleRotate(current);
      }
    }
    if(current.parent != null){
      balance(current.parent);
    }
    else{
      this.root = current;
    }
  }

//Updates the balance value of the current node

  private void findBalance(Node current){
    current.balance = height(current.rightChild) - height(current.leftChild);
  }

//Adjusts pointers and makes a left rotation and updates the balance

  private Node rotateLeft (Node current){
    Node temp = new Node();
    temp = current.rightChild;
    temp.parent = current.parent;

    current.rightChild = temp.leftChild;
    if (current.rightChild != null){
      current.rightChild.parent = current;
    }
    temp.leftChild = current;
    current.parent = temp;

    if(temp.parent != null) {
      if (temp.parent.rightChild == current){
        temp.parent.rightChild = temp;
      }
      else if(temp.parent.leftChild == current) {
        temp.parent.leftChild = temp;
      }
    }

    findBalance(current);
    findBalance(temp);
    return temp;
  }

//Adjusts pointers and makes a right rotation and updates the balance

  private Node rotateRight (Node current){
    Node temp = new Node();
    temp = current.leftChild;
    temp.parent = current.parent;

    current.leftChild = temp.rightChild;

    if(current.leftChild != null){
      current.leftChild.parent = current;
    }
    temp.rightChild = current;
    current.parent = temp;

    if(temp.parent != null) {
      if(temp.parent.rightChild == current){
        temp.parent.rightChild = temp;
      }
      else if(temp.parent.leftChild == current) {
        temp.parent.leftChild = temp;
      }
    }
    findBalance(current);
    findBalance(temp);
    return temp;
  }

// Adjusts pointers and makes a left-right-double rotation and updates the balance

  private Node leftRightDoubleRotate (Node current){
    current.leftChild = rotateLeft(current.leftChild);
    return rotateRight(current);
  }

// adjusts pointers and makes a right-left double rotation and updates the balance

  private Node rightLeftDoubleRotate (Node current){
    current.rightChild = rotateRight(current.rightChild);
    return rotateLeft(current);
  }

//Recursively finds the height of a current node

  private int height(Node current){
    if (current == null) {
      return -1;
    }
    if (current.leftChild == null & current.rightChild == null){
      return 0;
    }
    else if (current.leftChild == null){
      return 1+height(current.rightChild);
    }
    else if (current.rightChild == null){
      return 1 + height(current.leftChild);
    }
    else{
      return 1 + max(height(current.leftChild), height(current.rightChild));
    }
  }

//Helper function just to find the max integer

  private int max (int x, int y){
    if (x >= y){
      return x;
    }
    else{
      return y;
    }
  }

  public void inOrderSearch(String name){
    search(this.root, name);
  }

//recursively searches for a name and prints whether its found.

  private void search (Node node, String name){
    if (node != null){
      if(node.name.equals(name) && node.gender.equals("M")){
        System.out.println("The name " + node.name + " had frequency " + node.frequency + " and rank "+ node.rank + " among boys of the current year.");
        found = true;
      }
      if(node.name.equals(name) && node.gender.equals("F")){
        System.out.println("The name " + node.name + " had frequency " + node.frequency + " and rank "+ node.rank + " among girls of the current year.");
        found = true;
      }
      search(node.leftChild, name);
      search(node.rightChild, name);
    }
  }
  public void inOrder(ArrayList<Node> sortedNames){
    inOrder(this.root, sortedNames);
  }

//Adds each node to an arraylist

  private void inOrder(Node node, ArrayList<Node> sortedNames){
    if (node != null){
      sortedNames.add(node);
      this.total = this.total + node.frequency;

      inOrder(node.leftChild, sortedNames);
      inOrder(node.rightChild, sortedNames);
    }
  }
  public void popular(){
    counter(this.root);
    privatePopular(this.root);
  }

//Print 10 most popular names per gender by an in order search comparing rank.

  private void privatePopular(Node node){
    double sum = (double) this.total;
    if (node != null){
      if(node.rank <= 10){
        double sumPercent = 100.0*(node.frequency/sum);
        sumPercent = Math.round(sumPercent*100);
        sumPercent = sumPercent/100;
        System.out.println(node.name + ", " + node.frequency + ", " + "(" + sumPercent +"%)");
      }
      privatePopular(node.leftChild);
      privatePopular(node.rightChild);
    }
  }
  public void unique(){
    counter(this.root);
    privateUnique(this.root);
  }

//Prints 5 unique names per gender by an in-order search.

  private void privateUnique(Node node){
    double sum = (double) this.total;
    if(node != null){
      if (node.frequency == 5){
        if (mCount < 5 && node.gender.equals("M")){
          double sumPercent = 100.0*(node.frequency/sum);
          sumPercent = Math.round(sumPercent*100);
          sumPercent = sumPercent/100;
          mCount ++;
          System.out.println(node.name + " " + node.gender + ": " + node.frequency+ " (" + sumPercent +"%)");
        }
        if(fCount < 5 && node.gender.equals("F")){
          double sumPercent = 100.0*(node.frequency/sum);
          sumPercent = Math.round(sumPercent*100);
          sumPercent = sumPercent/100;
          fCount ++;
          System.out.println(node.name + " " + node.gender + ": " + node.frequency+ " (" + sumPercent +"%)");
        }
      }
      privateUnique(node.leftChild);
      privateUnique(node.rightChild);
    }
  }

//A sum of all the frequencies.

  private void counter(Node node){
    if (node != null){
      this.total = this.total + node.frequency;

      counter(node.leftChild);
      counter(node.rightChild);
    }
  }
}
