/**
 * An abstract base class for a segment tree data structure implemented using a tree structure.
 * Subclasses must implement the {@code queryRange} method to provide specific range query functionality.
 */
public abstract class SegmentTreeByTree implements SegmentTree {

    protected SegmentTreeNode root;
    protected int size;

    /**
     * Constructor for creating a Segment Tree from an input array
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public SegmentTreeByTree(int[] arr) {
        build(arr);
    }

    /**
     * Builds the segment tree from the given array of integers.
     *
     * @param arr the array of integers to build the segment tree from
     */
    @Override
    public void build(int[] arr) {
        int start = 0;
        int end = arr.length-1;

        this.size = arr.length;
        this.root = rec_tree_build(arr, start, end); 
    }

    /**
     * Helper function
     * Recursively builds a tree from the given array of integers and start & end array indices.
     * 
     * @param arr the array of integers to build the segment tree from
     * @param start The start index of the segment
     * @param end The start index of the segment
     * @return tree node (within recursion). At the end of recursion returns the full tree.
     */
    protected SegmentTreeNode rec_tree_build(int[] arr, int start, int end) {
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if (start == end) {
            // SegmentTreeNode node = new SegmentTreeNode(start, end);
            node.setMin(arr[start]);
            node.setMax(arr[start]);
            node.setSum(arr[start]);
            return node;
        } 
        int mid = getMidpoint(start, end);
        node.leftChild = rec_tree_build(arr, start, mid);
        node.rightChild = rec_tree_build(arr, mid+1, end);
        updateNode(node);
        return node; 
    }

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
     * Updates the element at the specified index in the original array and updates the segment tree accordingly.
     *
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    @Override
    public void update(int index, int value) {
        traverse_n_update(this.root, index, value);
    }

    /**
     * Helper function
     * Recursively traverses a tree from a given node, updates leaf in arr index: index with value: value 
     * and updates the nodes' min, max and sum values in the taken path.
     * 
     * @param currNode starting node (current node recursively)
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    protected void traverse_n_update(SegmentTreeNode currNode, int index, int value) {
        int currStart = currNode.getStart();
        int currEnd = currNode.getEnd();
        int currMid = getMidpoint(currStart, currEnd);
        if ((currNode.isLeaf()) && (currNode.getStart() == index)) {
            currNode.setMin(value);
            currNode.setMax(value);
            currNode.setSum(value);
            return;
        }
        if (index <= currMid) { 
            currNode = (SegmentTreeNode)currNode.leftChild; // go left
            traverse_n_update(currNode, index, value);
            updateNode(currNode);
        } else if (index > currMid) {
            currNode = (SegmentTreeNode)currNode.rightChild; // go right
            traverse_n_update(currNode, index, value);
            updateNode(currNode);
        } else {
            System.out.println("index " + index +" not in tree");
            return;
        }
        updateNode(this.root);
    }

    /**
     * Helper function that updates a given node's min, max and sum values
     * 
     * @param node
     */
    protected void updateNode(SegmentTreeNode node) {
        if ((node.leftChild == null) && (node.rightChild == null)) {
            return;
        }
        node.setMin(Math.min(((SegmentTreeNode) node.leftChild).getMin(),((SegmentTreeNode) node.rightChild).getMin()));
        node.setMax(Math.max(((SegmentTreeNode) node.leftChild).getMax(),((SegmentTreeNode) node.rightChild).getMax()));
        node.setSum(((SegmentTreeNode) node.leftChild).getSum() + ((SegmentTreeNode) node.rightChild).getSum());
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
        return this.size;
      //IMPLEMENT THE FUNCTION
    }

    /**
     * Queries the Segment Tree for the minimum value in the given range. to be implemented by subclasses.
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return Minimum value in the given range
     */
    @Override
    public abstract int queryRange(int left, int right);

    /**
     * Helper method for querying the Segment Tree 
     * @param node Current node of the Segment Tree
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return A SegmentTreeNode that contains the minimum, maximum and sum values for the given range
     */
    protected SegmentTreeNode queryRangeHelper(SegmentTreeNode node, int left, int right) {
        int currStart = node.getStart();
        int currEnd = node.getEnd();
        SegmentTreeNode leftNode;
        SegmentTreeNode rightNode; 
        SegmentTreeNode newNode;

        if ((currStart >= left) && (currEnd <= right)) {
            return node;
        }

        int currMid = getMidpoint(currStart, currEnd);

        if (right <= currMid) {
            newNode = queryRangeHelper((SegmentTreeNode)node.leftChild, left, right);
        } else if (left > currMid) {
            newNode = queryRangeHelper((SegmentTreeNode)node.rightChild, left, right);
        } else {
            leftNode = queryRangeHelper((SegmentTreeNode)node.leftChild, left, currMid);
            rightNode = queryRangeHelper((SegmentTreeNode)node.rightChild, currMid+1, right);
            newNode = new SegmentTreeNode(currStart, currEnd);
            newNode.setMin(Math.min(leftNode.getMin(),rightNode.getMin()));
            newNode.setMax(Math.max(leftNode.getMax(),rightNode.getMax()));
            newNode.setSum(leftNode.getSum() + rightNode.getSum());
        }

        return newNode;
    }
}
