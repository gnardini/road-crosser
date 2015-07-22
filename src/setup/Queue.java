package setup;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Queue<T> implements Iterable<T>{

	private Node first, last;
	private int size;
	
	public void push(T elem){
		if(isEmpty()){
			first = new Node(elem);
			last=first;
		}else{
			Node aux=new Node(elem);
			last.next=aux;
			last=aux;
		}
		size++;
	}
	
	public T pop(){
		if(isEmpty())throw new EmptyStackException();
		T aux=first.elem;
		first=first.next;
		size--;
		if(isEmpty()){
			first=null;
			last=null;
		}
		return aux;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	private class Node{
		protected T elem;
		protected Node next;
		
		public Node(T elem){
			this.elem=elem;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			
			private Node current=first;
			@Override
			public boolean hasNext() {
				return current!=null;
			}
			public T next() {
				T aux=current.elem;
				current=current.next;
				return aux;
			}
			
			public void remove() {
				
			}
			
		};
	}
}
