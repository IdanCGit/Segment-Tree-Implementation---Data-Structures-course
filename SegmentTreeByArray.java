/**
 * This abstract class represents a segment tree implementation using an array
 * and provides methods to build, update, and query the tree.
 */
public abstract class SegmentTreeByArray implements SegmentTree {
     
	 protected int[] tree;
	 protected int size;

    /**
     * Constructor for initializing the segment tree with the given input array.
     * @param arr the input array
     */
    public SegmentTreeByArray(int[] arr) {  
      build(arr);
    }

    /**
     * Builds the segment tree from the input array.
     * @param arr the input array
     */
    @Override
    public void build(int[] arr) {
      int start = 0;
      int end = arr.length-1;
      
      int treeSize = 2*(int)Math.pow(2,(Math.ceil(Math.log(arr.length)/Math.log(2)))) - 1;
      this.tree = new int[treeSize];
      for (int i=0; i < this.tree.length; i++) {
        this.tree[i] = Integer.MIN_VALUE;
      }
      
      this.size = arr.length;

      rec_tree_build(arr, start, end, 0);
      
      // root in arr[0] = in index 1 
      // left child in i*2 - (i = parent index)
      // right child in i*2+1 - (i = parent index)
      // parent of a child in Math.floor(i/2) - (i = child index)
      // transformation to java:
      // root in arr[0] = in index 0
      // left child in i*2+1 - (i = parent index)
      // right child in i*2+2 - (i = parent index)
      // parent of a child in Math.floor((i+1)/2) - (i = child index)
    }

    /**
     * Helper function.
     * Abstract class that is implemented accordingly in the subclasses.
     * Recursivly builds the tree in this.tree .
     * 
     * @param arr the input array
     * @param start The start index of the segment
     * @param end The end index of the segment
     * @param treeIdx The tree index in the this.tree array.
     * @return Return used in the recursive action of this function. Returns the value of the node in treeIdx
     */
    protected abstract int rec_tree_build(int[] arr, int start, int end, int treeIdx);

    /**
     * Helper function that returns the mid index (rounded down) of given start & end array indices.
     * @param start The start index of the segment
     * @param end The end index of the segment
     * @return The mid index of the segment
     */
    protected int getMidpoint(int start, int end) {
      int mid = (int)Math.floor((start + end)/2);
      return mid;
    }

    /**
     * Updates the value at the specified index and updates the segment tree accordingly.
     * @param index the index of the element to update in the array
     * @param value the new value to replace the existing value
     */
    @Override
    public void update(int index, int value) {
      traverse_n_update(0, 0, size()-1, index, value);
    }

    /**
     * Helper function
     * Recursively traverses a tree from a given node index, updates leaf in tree array index with value
     * and updates the nodes' attribute value, according to the segment tree type (uses the abstract class updateNode), in the taken path.
     * 
     * @param nodeIdx Index of a node in the tree
     * @param start The start index of the segment
     * @param end The end index of the segment
     * @param index the index of the element to update in the array
     * @param value the new value to replace the existing value
     */
    protected void traverse_n_update(int nodeIdx, int start, int end, int index, int value) {
      int mid = getMidpoint(start, end);
      // index found
      if ((end == index) && (start == index)) {
          this.tree[nodeIdx] = value;
          return;
      }
      if (index <= mid) {
          nodeIdx = nodeIdx*2 + 1;
          traverse_n_update(nodeIdx, start, mid, index, value); // go left
          updateNode(nodeIdx);
      } else if (index > mid) {
        nodeIdx = nodeIdx*2 + 2;
        traverse_n_update(nodeIdx, mid+1, end, index, value); // go right
          updateNode(nodeIdx);
      } else {
          System.out.println("index " + index +" not in tree");
          return;
      }
      updateNode(0);
    }

    /**
     * Helper function used to update the node based on the main criteria of each tree
     * implemented in its subclass
     * 
     * @param nodeIdx Index of a node in the tree
     */
    protected abstract void updateNode(int nodeIdx);

    /**
     * Queries the segment tree for a range of elements.
     * @param left the left index of the range
     * @param right the right index of the range
     * @return the result of the query operation
     */
    @Override
    public int queryRange(int left, int right) {
      int start = 0;
      int end = size() - 1; 
      return query(0, start, end, left, right);
	   // uses the query function for the implementation. 
    }

    /**
     * Abstract method for query operation, to be implemented by subclasses.
     * @param node the current node
     * @param start the start index
     * @param end the end index
     * @param left the left index
     * @param right the right index
     * @return the result of the query operation
     */
    protected abstract int query(int node, int start, int end, int left, int right);

    /**
     * Returns a string representation of the segment tree.
     * The members inside the array representing the segment tree are printed according to their indexes in the array.
	   * When the members are surrounded by "[ ]" and exactly one space between each number and between the brackets. 
	   * For example, for the tree [10,4,6,1,3,2,4] " [ 10 4 6 1 3 2 4 ] " will be returned
     * @return a string representation of the segment tree
     */
    @Override
    public String toString() {
      String daTree = " [";
      for (int i=0; i<this.tree.length; i++) {
        if (this.tree[i] == Integer.MIN_VALUE) {
          daTree = daTree + " -" ;
        } else {
          daTree = daTree + " " + Integer.valueOf(this.tree[i]);
        }
      }
      daTree = daTree + " ] ";
      return daTree;
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
      return this.size;
    }
}