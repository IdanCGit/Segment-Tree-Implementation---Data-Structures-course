public class MaximumSegmentTreeByTree extends SegmentTreeByTree {
    
    /**
     * Constructor for creating a Segment Tree from an input array
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public MaximumSegmentTreeByTree(int[] arr){
        super(arr);
    }

    /**
     * Queries the Segment Tree for the maximum value in the given range.
     * @param left Start index of the query range
     * @param right End index of the query range
     * @return Maximum value in the given range
     */
    @Override
    public int queryRange(int left, int right) {
        return queryRangeHelper(this.root, left, right).getMax();
    }

    @Override
    /**
     * Returns a string representation of the segment tree.
     * @return a string representation of the segment tree
     */
    public String toString() {
        String maxTree = " [";
        return traverse_preorder(this.root, maxTree) +" ] ";
    }

    /**
     * Helper function
     * Recursively traverses a tree from a given node and writes/logs the nodes' max in the taken path pre-order.
     * 
     * @param node starting node (current node recursively)
     */
    protected String traverse_preorder(SegmentTreeNode node, String st) {
        if (node == null) {
            return st;
        }
        st = st+" " + String.valueOf(node.getMax());
        // add to string/char -> node.getMax();
        st = traverse_preorder((SegmentTreeNode)node.leftChild, st);
        st = traverse_preorder((SegmentTreeNode)node.rightChild, st);
        return st;
    }
}
