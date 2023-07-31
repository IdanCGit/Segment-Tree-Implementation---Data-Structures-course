public class MinimumSegmentTreeByArray extends SegmentTreeByArray {
    
    /**
     * Constructor for creating a Segment Tree from an input array
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public MinimumSegmentTreeByArray(int[] arr){
        super(arr);
    }


    /**
     * Recursivly builds the tree in this.tree . 
     * Saves min value in each node (this.tree[treeIdx]).
     * 
     * @param arr the input array
     * @param start The start index of the segment
     * @param end The end index of the segment
     * @param treeIdx The tree index in the this.tree array.
     * @return Return used in the recursive action of this function. Returns the value of the node in treeIdx
     */
    @Override
    protected int rec_tree_build(int[] arr, int start, int end, int treeIdx) {
        int left;
        int right;

        if (start == end) {
          this.tree[treeIdx] = arr[start];
          return arr[start];
        } 
        int mid = getMidpoint(start, end);
        left = rec_tree_build(arr, start, mid, treeIdx*2 + 1);
        right = rec_tree_build(arr, mid+1, end, treeIdx*2 + 2);
        this.tree[treeIdx] = Math.min(left,right);
        return this.tree[treeIdx]; 
    }

    /**
     * Helper function used to update the node based on the min value of its children
     * 
     * @param nodeIdx Index of a node in the tree
     */
    @Override
    protected void updateNode(int nodeIdx) {
        if ((nodeIdx*2 + 1 > this.tree.length - 1)) {
            return; // index out of tree
        }
        if (this.tree[nodeIdx*2 + 1] == Integer.MIN_VALUE && this.tree[nodeIdx*2 + 2] == Integer.MIN_VALUE) {
            return; // tries to update a node that has node child nodes but the array has allocated space in their spots
        }
        this.tree[nodeIdx] = Math.min(this.tree[nodeIdx*2 + 1], this.tree[nodeIdx*2 + 2]);
    }

    /**
     * Queries the Segment Tree for the minimum value.
     * @param node the current node 
     * @param start the start index
     * @param end the end index
     * @param left the left index
     * @param right the right index
     * @return the result of the query operation
     */
    @Override
    protected int query(int node, int start, int end, int left, int right) {
        int leftNode;
        int rightNode; 
        int newNode;

        if ((start >= left) && (end <= right)) {
            return this.tree[node];
        }

        int mid = getMidpoint(start, end);

        if (right <= mid) {
            newNode = query(node*2 + 1, start, mid, left, right);  // go left
        } else if (left > mid) {
            newNode = query(node*2 + 2, mid+1, end, left, right);  // go right
        } else { // part left part right
            leftNode = query(node*2 + 1, start, mid, left, right);
            rightNode = query(node*2 + 2, mid+1, end, left, right);

            newNode = Math.min(leftNode,rightNode);
        }

        return newNode;
    }

}
