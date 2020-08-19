package sa1;

import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
  private int nodeCount;
  protected List<SingleLL<E>> collection;

  /**
   * Base constructor, initializes an empty ListCollection.
   */
  public ListCollection() {
	  this.nodeCount = 0;
	  this.collection = new ArrayList<SingleLL<E>>();
  }

  /**
   * Initializes a ListCollection with `numLists` lists.
   * 
   * @param numLists
   */
  public ListCollection(int numLists) {
	  this.nodeCount = numLists;
	  
	  for(int i = 0; i<numLists; i++) {
		  SingleLL<E> n = new SingleLL<E>();
		  collection.add(i, n);
	  }
  }

  /**
   * @return The size of the `ListCollection`
   */
  public int size() {
	  return collection.size();
  }

  /**
   * Sets the nodeCount
   * 
   * @param nodeCount
   */
  public void setNodeCount(int nodeCount) {
	  this.nodeCount = nodeCount;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount() {
	  return nodeCount;
  }

  /**
   * Adds the specified `SingleLL` to the end of the `ListCollection`
   * 
   * @param list
   */
  public void addList(SingleLL<E> list) {
	  collection.add(list);
	  int temp = list.size();
	  nodeCount += temp;
  }

  /**
   * Adds the specified `List` to the `ListCollection`
   * 
   * @param list
   * @complexity Your big-o and supporting explanation here
   * O(n^2) : The function loops through the elements of "list" and performs the append function each time(n times).
   * The append function iterates through the current list and adds the new element at the end; as each element is
   * added, it does one more loop, leading to 1+2+3+...+n or the summation from 1 to n, which can be simplified to 
   * n(n+1)/2 or O(n^2).
   */
  public void addList(List<E> list) {
	  SingleLL<E> temp = new SingleLL<E>();
	  for (E n : list) {
		  temp.append(n);
	  }
	  int temp1 = list.size();
	  nodeCount += temp1;
	  collection.add(temp);
  }

  /**
   * Returns the list at the specified index
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @return the list
   */
  public SingleLL<E> getList(int listIndex) {
	  if (listIndex < 0 || listIndex >= collection.size()) {
		  throw new IllegalArgumentException("getList: index is out of bounds");
	  }
	  else {
		  return collection.get(listIndex);
	  }
  }

  /**
   * Adds an element to the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   * @complexity Your big-o and supporting explanation here
   * O(n) : The function only needs to insert one element if it passes the first condition. It calls the inner list, 
   * then loops through the elements up to the place the element is being inserted once within the insert() function.
   * This results in a time complexity of only O(n). 
   */
  public void addElem(int listIndex, int elemIndex, E elem) {
	  if (listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex > this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("addElem: index is out of bounds");
	  }
	  SingleLL<E> temp = getList(listIndex);
	  temp.insert(elemIndex, elem);
	  nodeCount += 1;
  }

  /**
   * Sets the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   */
  public void setElem(int listIndex, int elemIndex, E elem) {
	  if (listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("setElem: index is out of bounds");
	  }
	  SingleLL<E> temp = getList(listIndex);
	  temp.set(elemIndex, elem);
  }

  /**
   * Returns the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @return
   */
  public E getElem(int listIndex, int elemIndex) {
	  if (listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("getElem: index is out of bounds");
	  }
	  SingleLL<E> temp = getList(listIndex);
	  return temp.get(elemIndex);
  }

  /**
   * Returns the `ListCollection` in string form, following STRICTLY the rule of
   * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5] [6, 7, 8]]
   */
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	  for(SingleLL<E> item: collection) {
		  sb.append(item.toString());
		  sb.append(", ");
	  }
	  sb.delete(sb.length()-2, sb.length());
	  sb.append("]");
	  return sb.toString();
  }
  
  public static void main(String[] args) {
	  ListCollection<Integer> C1 = new ListCollection<Integer>();
	  SingleLL<Integer> L1 = new SingleLL<Integer>();
	  L1.append(1);
	  L1.append(2);
	  L1.append(3);
	  SingleLL<Integer> L2 = new SingleLL<Integer>();
	  L2.append(1);
	  L2.append(2);
	  L2.append(3);
	  SingleLL<Integer> L3 = new SingleLL<Integer>();
	  L3.append(1);
	  L3.append(2);
	  L3.append(3);
	  C1.addList(L1);
	  C1.addList(L2);
	  C1.addList(L3);
	  C1.addElem(0, 2, 4);
	  C1.setElem(1,2,4);
	  System.out.println(C1.getElem(2,1));
	  System.out.println(C1.getList(1));
	  System.out.println(C1);
  }
  

}
