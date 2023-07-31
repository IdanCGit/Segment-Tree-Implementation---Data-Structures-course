public class MinimumSegmentTreeByTree extends SegmentTreeByTree {
    
    /**
     * Constructor for creating a Segment Tree from an input array
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public MinimumSegmentTreeByTree(int[] arr){
        super(arr);
    }

    /**
     * Queries the Segment Tree for the Minimum value in the given range.
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return Minimum value in the given range
     */
    @Override
    public int queryRange(int left, int right) {
        return queryRangeHelper(this.root, left, right).getMin();
    }

    @Override
    /**
     * Returns a string representation of the segment tree.
     * @return a string representation of the segment tree
     */
    public String toString() {
        String minTree = " [";
        return traverse_preorder(this.root, minTree) +" ] ";
    }

    /**
     * Helper function
     * Recursively traverses a tree from a given node and writes/logs the nodes' min in the taken path pre-order.
     * 
     * @param node starting node (current node recursively)
     */
    protected String traverse_preorder(SegmentTreeNode node, String st) {
        if (node == null) {
            return st;
        }
        st = st+" " + String.valueOf(node.getMin());
        // add to string/char -> node.getMin();
        st = traverse_preorder((SegmentTreeNode)node.leftChild, st);
        st = traverse_preorder((SegmentTreeNode)node.rightChild, st);
        return st;
    }
}
