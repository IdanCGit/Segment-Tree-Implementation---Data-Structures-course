/**
 * This is a testing framework. 
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        //SegmentTrees
        testMaximumSegmentTreeByTree();
        testMinimumSegmentTreeByTree();
        testSummationSegmentTreeByTree();

        testMaximumSegmentTreeByArray();
        testMinimumSegmentTreeByArray();
        testSummationSegmentTreeByArray();

        //NumberAnalyzers
        testNumberAnalyzerByTrees();
        testNumberAnalyzerByArrays();

        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }


    /**
     * Checks the MaximumSegmentTreeByTree class.
     */
    private static void testMaximumSegmentTreeByTree() {

        MaximumSegmentTreeByTree mstbt = new MaximumSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(mstbt.queryRange(0,4) == 60, "The max of {60,10,5,15,6} between indexes [0:3] should be 60");

        test(mstbt.toString().equals(" [ 60 60 60 60 10 5 15 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 60 60 60 60 10 5 15 15 6 ] ' got: '" + mstbt.toString()+ " '");

        mstbt.update(1,80);

        test(mstbt.queryRange(0,4) == 80, "After update index 1 from {60,10,5,15,6} to 80, the max between indexes [0:3] should be 80");

        test(mstbt.toString().equals(" [ 80 80 80 60 80 5 15 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 80 80 80 60 80 5 15 15 6 ] ' got: '" + mstbt.toString()+ " '");
    
        test(mstbt.size() == 5, "Size of {10,15,55,15,9,12} should be 5");
    }

    /**
     * Checks the MinimumSegmentTreeByTree class.
     */
    private static void testMinimumSegmentTreeByTree() {

        MinimumSegmentTreeByTree minstbt = new MinimumSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(minstbt.queryRange(0,4) == 5, "The min of {60,10,5,15,6} between indexes [0:3] should be 5");

        test(minstbt.toString().equals(" [ 5 5 10 60 10 5 6 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 5 5 10 60 10 5 6 15 6 ] ' got: '" + minstbt.toString()+ " '");

        minstbt.update(1,2);

        test(minstbt.queryRange(0,4) == 2, "After update index 1 from {60,10,5,15,6} to 2, the min between indexes [0:3] should be 2");

        test(minstbt.toString().equals(" [ 2 2 2 60 2 5 6 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 2 2 2 60 2 5 6 15 6 ] ' got: '" + minstbt.toString()+ " '");
    
        test(minstbt.size() == 5, "Size of {10,15,55,15,9,12} should be 5");
    }

    /**
     * Checks the SummationSegmentTreeByTree class.
     */
    private static void testSummationSegmentTreeByTree() {

        SummationSegmentTreeByTree sstbt = new SummationSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(sstbt.queryRange(0,4) == 96, "The sum of {60,10,5,15,6} between indexes [0:3] should be 96");

        test(sstbt.toString().equals(" [ 96 75 70 60 10 5 21 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 96 75 70 60 10 5 21 15 6 ] ' got: '" + sstbt.toString()+ " '");

        sstbt.update(4,10);

        test(sstbt.queryRange(0,4) == 100, "After update index 4 from {60,10,5,15,6} to 10, the sum between indexes [0:3] should be 100");

        test(sstbt.toString().equals(" [ 100 75 70 60 10 5 25 15 10 ] "),"The toString of {60,80,5,15} should be ' [ 100 75 70 60 10 5 25 15 10 ] ' got: '" + sstbt.toString()+ " '");
    
        test(sstbt.size() == 5, "Size of {10,15,55,15,9,12} should be 5");
    }

    /**
     * Checks the MaximumSegmentTreeByArray class.
     */
    private static void testMaximumSegmentTreeByArray() {

        MaximumSegmentTreeByArray mstba = new MaximumSegmentTreeByArray(new int[]{10,15,55,15,9,12});

        test(mstba.toString().equals(" [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] ' got: '" + mstba.toString()+ " '");

        test(mstba.queryRange(0,5) == 55, "The max of {10,15,55,15,9,12} between indexes [0:5] should be 55");

        mstba.update(0,80);

        test(mstba.toString().equals(" [ 80 80 15 80 55 15 12 80 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 80 80 15 80 55 15 12 80 15 - - 15 9 - - ] ' got: '" + mstba.toString()+ " '");

        test(mstba.queryRange(0,5) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the max between indexes [0:5] should be 80");

        test(mstba.size() == 6, "Size of {10,15,55,15,9,12} should be 6");
    }

    /**
     * Checks the MinimumSegmentTreeByArray class.
     */
    private static void testMinimumSegmentTreeByArray() {

        MinimumSegmentTreeByArray minstba = new MinimumSegmentTreeByArray(new int[]{10,15,55,15,9,12});

        test(minstba.toString().equals(" [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] ' got: '" + minstba.toString()+ " '");

        test(minstba.queryRange(0,2) == 10, "The min of {10,15,55,15,9,12} between indexes [0:2] should be 10");

        minstba.update(5,80);

        test(minstba.toString().equals(" [ 9 10 9 10 55 9 80 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 9 10 9 10 55 9 80 10 15 - - 15 9 - - ] ' got: '" + minstba.toString()+ " '");

        test(minstba.queryRange(0,5) == 9, "After update index 0 from {10,15,55,15,9,12} to 80, the min between indexes [0:5] should be 9");

        test(minstba.size() == 6, "Size of {10,15,55,15,9,12} should be 6");
    }

    /**
     * Checks the MaximumSegmentTreeByArray class.
     */
    private static void testSummationSegmentTreeByArray() {

        SummationSegmentTreeByArray sstba = new SummationSegmentTreeByArray(new int[]{10,15,55,15,9,12});

        test(sstba.toString().equals(" [ 116 80 36 25 55 24 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 116 80 36 25 55 24 12 10 15 - - 15 9 - - ] ' got: '" + sstba.toString()+ " '");

        test(sstba.queryRange(4,5) == 21, "The sum of {10,15,55,15,9,12} between indexes [4:5] should be 21");

        sstba.update(5,80); 

        test(sstba.toString().equals(" [ 184 80 104 25 55 24 80 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 184 80 104 25 55 24 80 10 15 - - 15 9 - - ] ' got: '" + sstba.toString()+ " '");

        test(sstba.queryRange(4,5) == 89, "After update index 5 from {10,15,55,15,9,12} to 80, the sum between indexes [4:5] should be 89");

        test(sstba.size() == 6, "Size of {10,15,55,15,9,12} should be 6");
    }

    /**
     * Checks the NumberAnalyzerByTrees class.
     */
    private static void testNumberAnalyzerByTrees() {

        Integer[] arr = {10,30,50};
        NumberAnalyzerByTrees nabt = new NumberAnalyzerByTrees(arr);

        test(nabt.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
        test(nabt.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(nabt.getSum(0,2) == 90, "The sum of {10,30,50} between indexes [0:1] should be 40");

        nabt.update(2,5);

        test(nabt.getMax(0,2) == 30, "The max of {10,30,5} between indexes [0:2] should be 30");
        test(nabt.getMin(0,2) == 5, "The min of {10,30,5} between indexes [0:2] should be 5");
        test(nabt.getSum(0,2) == 45, "The sum of {10,30,5} between indexes [0:2] should be 45");

        // Comparator
        test(nabt.compare(4,2) == 1, "The comparison result between num1 = 4 and num2 = 2 should be 1 (both even, num1 is bigger than num2)");
        test(nabt.compare(1,1) == 0, "The comparison result between num1 = 1 and num2 = 1 should be 0 (both odd and equal)");
        test(nabt.compare(3,2) == -1, "The comparison result between num1 = 3 and num2 = 2 should be -1 (num1 is odd, num2 is even)");
        test(nabt.compare(2,3) == 1, "The comparison result between num1 = 2 and num2 = 3 should be 1 (num1 is even, num2 is odd)");
        test(nabt.compare(3,7) == -1, "The comparison result between num1 = 3 and num2 = 7 should be -1 (both odd, num2 is bigger than num1)");

        // Iterator
        String tst_nabt = "";
        
        for (int i : nabt){
            tst_nabt = tst_nabt +" " + i;
        }
        test(tst_nabt.equals(" 10 30 5"),"The iterator result should be ' 10 30 5' got: '" + tst_nabt+ " '");
    }

    /**
     * Checks the NumberAnalyzerByArrays class.
     */
    private static void testNumberAnalyzerByArrays() {

        Integer[] arr = {10,30,50};
        NumberAnalyzerByArrays naba = new NumberAnalyzerByArrays(arr);

        test(naba.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
        test(naba.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(naba.getSum(0,1) == 40, "The sum of {10,30,50} between indexes [0:1] should be 40");

        naba.update(2,5);

        test(naba.getMax(0,2) == 30, "The max of {10,30,5} between indexes [0:2] should be 30");
        test(naba.getMin(0,2) == 5, "The min of {10,30,5} between indexes [0:2] should be 5");
        test(naba.getSum(0,2) == 45, "The sum of {10,30,5} between indexes [0:2] should be 45");

        // Comparator
        test(naba.compare(4,2) == 1, "The comparison result between num1 = 4 and num2 = 2 should be 1 (both even, num1 is bigger than num2)");
        test(naba.compare(1,1) == 0, "The comparison result between num1 = 1 and num2 = 1 should be 0 (both odd and equal)");
        test(naba.compare(3,2) == -1, "The comparison result between num1 = 3 and num2 = 2 should be -1 (num1 is odd, num2 is even)");
        test(naba.compare(2,3) == 1, "The comparison result between num1 = 2 and num2 = 3 should be 1 (num1 is even, num2 is odd)");
        test(naba.compare(3,7) == -1, "The comparison result between num1 = 3 and num2 = 7 should be -1 (both odd, num2 is bigger than num1)");

        // Iterator
        String tst_naba = "";
        
        for (int i : naba){
            tst_naba = tst_naba +" " + i;
        }
        test(tst_naba.equals(" 10 30 5"),"The iterator result should be ' 10 30 5' got: '" + tst_naba+ " '");
    }

}
