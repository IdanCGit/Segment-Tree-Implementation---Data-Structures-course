import java.util.Comparator;
import java.util.Iterator;

/**
 The NumberAnalyzer class provides an abstract implementation for analyzing a collection of integers.
 It implements the Iterable interface to allow for iteration over the collection, and the Comparator interface
 to provide a default comparison method for integers.
 */
public abstract class NumberAnalyzer implements Iterable<Integer>, Comparator<Integer> {

    protected Integer[] numbers;

    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzer(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Implementation of the Comparator interface. Compares two numbers using a custom definition defined in the 'returns' section.
     * 
     * @param num1 First number
     * @param num2 Second number
     * @return Returns 1 if the first number is bigger than the second number, 0 if they're equal and -1 if the second number is bigger than the first.
     * In addition, in this method, even numbers are always considered to be bigger than odd numbers.
     */
    public int compare(Integer num1, Integer num2) {  
        if ((isEven(num1) && isEven(num2)) || (!isEven(num1) && !isEven(num2))) {
            if (num2 < num1) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            } else { // num1 == num2
                return 0;
            }
        } else if (isEven(num1)) {
            return 1;
        } else { // (isEven(num2))
            return -1;
        }
     }


    /**
     * Helper function. Used to determine wether a number is and even number or not (odd)
     * @param num the tested number
     * @return return boolean. true if num is even, false if it is odd.
     */
    protected boolean isEven(int num) {
        if ((num % 2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Iterable interface implementation
     */
    public Iterator iterator() {
        return new AnalyzerIterator(this.numbers);
    }

    /**
     * New custom iterator under the Iterator interface implementation
     */
    class AnalyzerIterator implements Iterator {
        private Integer[] numbers;
        private int nextIdx, size;
        // constructor
        public AnalyzerIterator(Integer[] numbers) {
            this.numbers = numbers; 
            this.size = numbers.length; 
            nextIdx = 0;
        }
          
        // Checks if the next element exists
        public boolean hasNext() {
            return nextIdx <= this.size - 1;
        }
          
        // moves the cursor/iterator to next element
        public Integer next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements");
            }
            Integer ans = this.numbers[nextIdx];
            nextIdx = nextIdx + 1;
            return ans;
        }
    }

    /**
     * Returns the maximum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The maximum value in the range.
     */
    public abstract Integer getMax(int left, int right);

    /**
     * Returns the minimum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The minimum value in the range.
     */
    public abstract Integer getMin(int left, int right);

    /**
     * Returns the sum of the values in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The sum of the values in the range.
     */
    public abstract Integer getSum(int left, int right);

    /**
     * Updates the value at the given index. This is an abstract function to be implemented by the subclasses
     * @param index The index of the value to be updated.
     * @param value The new value to be set at the given index.
     */
    public abstract void update(int index, int value);



}