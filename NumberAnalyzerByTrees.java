public class NumberAnalyzerByTrees extends NumberAnalyzer {
    protected Integer[] numbers;
    protected int[] numbersArr;
    protected MaximumSegmentTreeByTree maxTree;
    protected MinimumSegmentTreeByTree minTree;
    protected SummationSegmentTreeByTree sumTree;
    protected int i;

    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzerByTrees(Integer[] numbers) {
        super(numbers);
        this.numbers = numbers;
        // Save number Integer[] as int[] to be compatible with SegmentTree class
        this.numbersArr = new int[numbers.length];
        for (i = 0; i <= numbers.length - 1 ; i++) {
            this.numbersArr[i] = numbers[i].intValue();
        }
        
        this.maxTree = new MaximumSegmentTreeByTree(this.numbersArr);
        this.minTree = new MinimumSegmentTreeByTree(this.numbersArr);
        this.sumTree = new SummationSegmentTreeByTree(this.numbersArr);
    }

    /**
     * Returns the maximum value in the given range.
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The maximum value in the range.
     */
    public Integer getMax(int left, int right) { 
        return this.maxTree.queryRange(left, right);
    }

    /**
     * Returns the minimum value in the given range.
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The minimum value in the range.
     */
    public  Integer getMin(int left, int right) { 
        return this.minTree.queryRange(left, right);
    }

    /**
     * Returns the sum of the values in the given range. 
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The sum of the values in the range.
     */
    public Integer getSum(int left, int right) { 
        return this.sumTree.queryRange(left, right);
    }

    /**
     * Updates the value at the given index.
     * @param index The index of the value to be updated.
     * @param value The new value to be set at the given index.
     */
    public void update(int index, int value) {
        this.numbers[index] = value;
        this.numbersArr[index] = value;
        this.maxTree.update(index, value);
        this.minTree.update(index, value);
        this.sumTree.update(index, value);
    }
}
