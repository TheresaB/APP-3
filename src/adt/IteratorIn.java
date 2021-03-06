package adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorIn<T extends java.lang.Comparable<T>> implements Iterator<RedBlackTree<T>> {
	private RedBlackTree<T> rbt;
	private int i, laenge;
	private RBTNode<T> actual, last, parent, child;
	
	//Konstruktor
	public IteratorIn(RedBlackTree<T> rbt){
		this.rbt = rbt;
		i = 0;
		laenge = rbt.size();
		//kleinster Knoten zuerst
		actual = rbt.list;
		while(actual.left.data != null){
			actual = actual.left;
		}
		parent = actual.p;
	}
	
	public boolean hasNext(){
		return i < laenge;
	}
	
	public void remove(){
		try{
			rbt.remove(last.data);
		}
		catch(IllegalArgumentException e){
			throw new NoSuchElementException("Error");
		}
	}
	
	public RedBlackTree<T> next(){
		last = actual;
		//wenn es noch rechte Kinder gibt, die zuerst ausgeben
		if(last.right.data != null){
			actual = last.right;
			while(actual.left.data != null){
				actual = actual.left;
			}
		}
		else{
			//zur�ck zum n�chst gr��eren
			child = last;
			parent = last.p;
			while(parent != null && child == parent.right){
				child = parent;
				parent = parent.p;
			}
			actual = parent;
		}
		//i f�r hasNext() erh�hen
		i++;
		RedBlackTree<T> rbt2 = new RedBlackTree<T>();
		rbt2.list = last;
		return rbt2;
	}
}
