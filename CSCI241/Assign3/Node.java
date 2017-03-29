
/*
Node class for implementation
Scott Waldron
Chachi Lor
*/
import java.util.Comparator;

public class Node{
  public Node parent;
  public Node leftChild;
  public Node rightChild;
  public String name;
  public String gender;
  public int frequency;
  public int rank;
  public int balance = 0;

  public Node(){

  }

  public Node(Node otherNode){
    this.parent = otherNode.parent;
    this.leftChild = otherNode.leftChild;
    this.rightChild = otherNode.rightChild;
    this.name = otherNode.name;
    this.gender = otherNode.gender;
    this.frequency = otherNode.frequency;
    this.rank = otherNode.rank;
    this.balance = otherNode.balance;
  }
  public Node(String name, String gender, int frequency, int rank){
    this.name = name;
    this.gender = gender;
    this.frequency = frequency;
    this.rank = rank;
  }
  public Node(String name){
    this.name = name;
  }
}
