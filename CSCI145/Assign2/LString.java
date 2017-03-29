/*************************
*CSCI 145								 *
*Assignment 2						 *
*Scott Waldron					 *
*Feb 24, 16							 *
*File Name: LString.java *
**************************/
public class LString {

	private static class Node{ //Private class for Nodes in LString class

		char data;
		Node next;

		public Node(){ //Constructor
		}
		public Node(char newData){ //Constructor
			this.data = newData;
		}
		public Node(char newData, Node newNext){ //Constructor
			this.data = newData;
			this.next = newNext;
		}
	}

	Node front;
	int size;

	public LString(){ //LString Constructor
		this.size = 0;
		this.front = null;
	}
	public LString(String original){ //LString Constructor return a new LString with same value
		this.size = original.length();
		if (original.length() > 0){
			this.front = new Node (original.charAt(0));
			Node current = this.front;

			for (int i = 1; i < original.length(); i++){
				current.next = new Node (original.charAt(i));
				current = current.next;
			}
		}
	}
	public String toString(){ //Returns a string of LString
		if(front == null){
			return "";
		}else{
			StringBuilder tempBuilder= new StringBuilder (this.length());
		//	String result = "";
			Node curr = front;
			while (curr != null){
			tempBuilder.append(curr.data);
				curr = curr.next;
			}
			return tempBuilder.toString();
		}
	}
	public int length(){ // Getter for length
		return this.size;
	}
	public char charAt (int index){ // Finds a char at a given location within bounds
		if (index >= length()  || index < 0){
			throw new IndexOutOfBoundsException("ERROR: Can't retrieve index at " + index);
		}
		Node curr = front;
		for (int i = 0; i < length(); i++){
			if (i == index){
				return curr.data;
			}
			curr = curr.next;
		}
		throw new IllegalStateException();
	}
	public void setCharAt(int index, char ch){ // finds a char at a given location and replaces it
		if (index >= length()  || index < 0){
			throw new IndexOutOfBoundsException("ERROR: Can't retrieve index at " + index);
		}
		Node curr = front;
		for (int i = 0; i < length(); i++){
			if (i == index){
				 curr.data = ch;
			}
			curr = curr.next;
		}
	}
	public int compareTo(LString anotherLString){ // Lexicographically compares two Lstrings
		int limit = Math.min(length(), anotherLString.length());

		Node currentNode1 = front;
		Node currentNode2 = anotherLString.front;

		int i = 0;
		while ( i < limit){
			char ch1 = currentNode1.data;
			char ch2 = currentNode2.data;
			if (ch1 != ch2){
				return ch1 - ch2;
			}
			i++;
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		return length() - anotherLString.length();
	}
	@Override
	public boolean equals(Object other){ // Checks to see if another LString is identical to the original
		if (other == null || !(other instanceof LString)){
			return false;
		}else{
			LString otherLString = (LString)other;
			int length = length();
			if (length == otherLString.length()){
				Node node1 = front;
				Node node2 = otherLString.front;
				while (node1 != null){
					if (node1.data != node2.data){
						return false;
					}
					node1 = node1.next;
					node2 = node2.next;
				}
				return true;
			}
			return false;
		}
	}
	public LString substring(int start, int end){ // Returns a partial LString of your original
		if (end > length() || start < 0 || start > end){
			throw new IndexOutOfBoundsException("ERROR: Your start and end are not compatible");
		}
		if(start == end && start == this.length()){
			LString nullLString = new LString();
			return nullLString;
		}
		String result = "";
		for(int i=start; i < end; i++){
			char temp = this.charAt(i);
			result += temp;
		}
		LString newSubString = new LString(result);
		return newSubString;
	}
	public LString replace(int start, int end, LString lStr) { // Returns a LString with another LString
    if (start < 0 || end > this.length() || start > end) {    // interjected into it at a given location   
        throw new IndexOutOfBoundsException();
    }
		if (front == null){ //Runs when front is null
			front = new Node (lStr.front.data);
			Node curr=front;
			Node currlStr = lStr.front;
			for(int i = 0; i<lStr.length() - 1; i++){
				currlStr = currlStr.next;
				curr.next = new Node(currlStr.data);
				curr = curr.next;
			}
		}
		else if(start == 0 && end == start){ // Runs when start and end are at the beggining of Lstring
			Node target = front;
			front = new Node(lStr.front.data);
			Node curr = front;

			Node currlStr = lStr.front;
			for(int i = 0; i <lStr.length() - 1; i++){
				currlStr = currlStr.next;
				curr.next = new Node(currlStr.data);
				curr = curr.next;
			}
			curr.next = target;
		}
      else if(start==end && end == this.length()){ // Runs when start and end are at the end of LString
			Node curr = front;
			for(int i = 0;i <this.length() - 1; i++){
				curr = curr.next;
			}
			Node currlStr = lStr.front;
			for(int i = 0; i < lStr.length(); i++){
				curr.next = new Node(currlStr.data);
				currlStr = currlStr.next;
				curr = curr.next;
			}
		}
		else if(start == end){ // Runs when start and end are equal in the middle of LString
                             // Does not replace, only interjects 
			Node curr = front;
			for (int i = 0; i < start - 1; i++){
					curr = curr.next;
			}
			Node finish = curr.next;
			Node currlStr = lStr.front;
			for(int i = 0; i < lStr.length(); i++){
			curr.next= new Node(currlStr.data);
			currlStr= currlStr.next;
			curr = curr.next;
			}
			curr.next = finish;
		}
		else if(start == 0){ // Runs when start is 0 and end is not
			Node finish = front;
			for(int i = 0; i <end; i++){
				finish = finish.next;
			}
			front = new Node(lStr.front.data);
			Node curr= front;

			Node currlStr = lStr.front;
			for(int i=0; i < lStr.length() - 1; i++){
				currlStr = currlStr.next;
				curr.next = new Node(currlStr.data);
				curr = curr.next;
			}
			curr.next = finish;
		}
		else{                 //Runs when start and end are two distinct numbers not at 0 or length()
			Node curr = front;
			for(int i = 0; i<start - 1;i++){
				curr = curr.next;
			}
			Node finish = curr.next;
			for(int i=start;i<end; i++){
				finish = finish.next;
			}
			Node currlStr = lStr.front;
			for(int i = 0; i <lStr.length(); i++){
				curr.next = new Node(currlStr.data);
				currlStr = currlStr.next;
				curr = curr.next;
			}
			curr.next = finish;
		}
		this.size = this.size-(end-start) + lStr.length();
		return this;
	}
}
