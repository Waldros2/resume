/****************************
*CSCI 145					*
*Lab 6					    *
*Scott Waldron				*
*Mar 2, 16					*
*File Name: LinkedList.java *
****************************/
public class LinkedList<E> {
	
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
	
	public LinkedList(){
		front = null;
	}
	
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
	public void add(int index, E value){
		if(index >= 0 && index <= size()){
			if (front == null){
				this.add(value);
			}else{
				ListNode current = front;
				for(int i = 0; i < index - 1; i++){
					current = current.next;
				}
				current.next = new ListNode(value, current.next.next);
				size++;
			}
		}else{
			throw new IndexOutOfBoundsException("ERROR: Index not in range.");
		}
	}	
			
			
			
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
	public E remove(){
		if(size() > 0){
			E first = front.data;
			front = front.next;
			size--;
			return first;
		}else{
			throw new IndexOutOfBoundsException("ERROR: Can't remove from empty list.");
		}
	}
	public E remove(int index){
		if(index >= 0 && index < size()){
			ListNode current = front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			E temp = current.next.data;
			current.next = current.next.next;
			size--;
			return temp;
		}else{
			throw new IndexOutOfBoundsException("ERROR: Index not in range."); 
		}
	}
}