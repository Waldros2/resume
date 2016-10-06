/****************************
*CSCI 145					*
*Lab 6					    *
*Scott Waldron				*
*Mar 2, 16					*
*File Name: LinkedList.java *
****************************/
public class LinkedList<E> {
//A ListNode class for LinkedList to access
	private class ListNode { 				
		E data;
		ListNode next;
		ListNode(E data) {
			this.data = data;
			next = null;
		}
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	ListNode front;
	int size;
//Constructor for an empty LinkedList
	public LinkedList(){
		front = null;
	}
//Method to check if a LinkedList is empty	
	public boolean isEmpty(){
		if (front == null){
			return true;
		}else{
			return false;
		}
	}
	public int size(){
		return size;
	}
//Add to the end of a LinkedList
	public void add(E value){
		if (front == null) {
			front = new ListNode(value);
		} else {
			ListNode current = front;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(value);
		}
		size++;
	}
//Add at an index of a LinkedList
	public void add(int index, E value){
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException("ERROR: Index not in range.");
		}else if(index == 0){
			this.front = new ListNode(value, this.front);
			size++;
		}else{
			ListNode current = front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
			size++;
		}
	}	
//Return the data at an index		
	public E get(int index){
		if(index >= 0 && index < size()){
			ListNode current = front;
			for(int i = 0; i < index; i++){
				current = current.next;
			}
			return current.data;
		}else{
			throw new IndexOutOfBoundsException("ERROR: Index not in range."); 
		}
	}
//Remove the first index of a LinkedList
	public E remove(){
		if(size() == 0){
			throw new IndexOutOfBoundsException("ERROR: Can't remove from empty list.");
		}else{
			ListNode current = front;
			front = front.next;
			size--;
			return current.data;
		}
	}
//Remove at specific index of LinkedList
	public E remove(int index){
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException("ERROR: Index not in range."); 
		}else if(index == 0){
			return this.remove();
		}else{	
			ListNode current = front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			E temp = current.next.data;
			current.next = current.next.next;
			size--;
			return temp;
		}
	}
}