package sa1;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  public ListMatrix(int rows, int columns) {
	  this.rows = rows;
	  this.columns = columns;
	  this.setNodeCount(columns*rows);
	  
	  for (int i = 0; i<rows; i++) {
		  SingleLL<Integer> tempList = new SingleLL<Integer>();
		  for(int j=0; j<columns; j++) {
				  tempList.append(0);
		  }
		  collection.add(tempList);
	  }
  }

  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }

  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not properly coincide
   * @param other
   * @complexity Your big-o and supporting explanation here
   * O(n^3) : The loops through the number of rows and columns results in a time complexity of n^2 since it has to loop
   * through every element in the new matrix. Within the for loops, getElem and setElem both have a time complexity of
   * n, since they iterate through i+j times, so the inner loop has time complexity, 3n. So, combined, n^2*3n = 3n^3. 
   */
  public void add(ListMatrix other) {
	  if (rows != other.rows || columns != other.columns) {
		  throw new IllegalArgumentException("add: Matrices dimensions are not the same");
	  }
	  for (int i=0; i<rows; i++) {
		  for(int j=0; j<columns; j++) {
			  int temp1 = this.getElem(i, j);
			  int temp2 = other.getElem(i, j);
			  this.setElem(i, j, temp1 + temp2);
		  }
	  }
  }

  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
  public static ListMatrix transpose(ListMatrix matrix) {
	  ListMatrix transpose = new ListMatrix(matrix.columns, matrix.rows);
	  for(int i=0; i<matrix.rows; i++) {
		  for (int j=0; j<matrix.columns; j++) {
			  int temp = matrix.getElem(i, j);
			  transpose.setElem(j, i, temp);
		  }
	  }
	  return transpose;
  }

  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not properly coincide
   * @param other
   * @return
   */
  public int helperMult(int rowA, int columnB, ListMatrix matrixA, ListMatrix matrixB) {
	  int temp = 0;
	  for (int i=0; i<matrixB.rows; i++) {
		  temp += (matrixA.getElem(rowA, i)*matrixB.getElem(i, columnB));
	  }
	  return temp;
  }
  
  public ListMatrix multiply(ListMatrix other) {
	  if (this.columns != other.rows) {
		  throw new IllegalArgumentException("multiply: this.columns and other.rows are not the same length");
	  }
	  ListMatrix LM = new ListMatrix(this.rows, other.columns);
	  for (int i=0; i<this.rows; i++) {
		  for (int j=0; j<other.columns; j++) {
			  LM.setElem(i,j,helperMult(i,j,this,other));
		  }
	  }
	  return LM;
  }
  
  public static void main(String[] args) {
	  ListMatrix LM = new ListMatrix(2,3);
	  SingleLL<Integer> L1 = new SingleLL<Integer>();
	  L1.append(1);
	  L1.append(2);
	  L1.append(3);
	  SingleLL<Integer> L2 = new SingleLL<Integer>();
	  L2.append(1);
	  L2.append(2);
	  L2.append(3);
	  LM.collection.set(0, L1);
	  LM.collection.set(1, L2);
	  
	  ListMatrix LM2 = new ListMatrix(3,3);
	  LM2.collection.set(0, L1);
	  LM2.collection.set(1, L1);
	  LM2.collection.set(2, L1);
	  //LM.collection.set(2, L1);
	  //System.out.println(LM);
	  //System.out.println(LM2);
	  //System.out.println(LM.multiply(LM2));
	  //System.out.println(transpose(LM));
	  //LM.add(LM2);
	  //LM.addElem(1,3,2);
	  //LM.addElem(0, 0, 0);
	  System.out.println(LM);
  }
}
