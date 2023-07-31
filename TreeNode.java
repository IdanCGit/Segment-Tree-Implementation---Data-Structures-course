public abstract class TreeNode {
    //fields
    TreeNode leftChild;
    TreeNode rightChild;

    /** default constructor
     */
    public TreeNode(){
        leftChild = null;
        rightChild = null;
    }

    /** constructor that receives also left and right children
     * @param left The left child of the node
     * @param right The right child of the node
     */
    public TreeNode(TreeNode left, TreeNode right){
        this.leftChild = left;
        this.rightChild = right;
    }

    /** returns the left child of the node
     * @return The TreeNode left child of the node.
     */
    public TreeNode getLeft(){
        return this.leftChild;
    }

    /** returns the right child of the node
     * @return The TreeNode right child of the node.
     */
    public TreeNode getRight(){
        return this.rightChild;
    }

    /** returns if this node is a leaf or not
     * @return true or false.
     */
    public boolean isLeaf(){
        return (this.leftChild == null) && (this.rightChild == null);
    }

}
