import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CompareCollection {

    private static int times = 10000;;

    public static void main(String[] args) {
        System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s\n", "Type", "Add 1", "Remove 1", "Contains 1", "Add 2", "Remove 2", "Contains 2", "Add 3", "Remove 3", "Contains 3", "Add Avg.", "Remove Avg.", "Contains Avg.");
        //System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for(int i = 0; i < 16*13; i++) {
            System.out.print("-");
        }
        System.out.println("");

        HashSet<Integer> hashSet = new HashSet<Integer>();
        CompareCollection.printTestTimes("HashSet", CompareCollection.testAll(hashSet));

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        CompareCollection.printTestTimes("ArrayList", CompareCollection.testAll(arrayList));

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        CompareCollection.printTestTimes("LinkedList", CompareCollection.testAll(linkedList));

    }


    public static long testAdd(AbstractCollection<Integer> o) {
        Random rdm = new Random();
        long startTime = System.nanoTime();
        for(int i = 0; i < times; i++) {
            o.add(rdm.nextInt(Integer.MAX_VALUE));
        }

        return (System.nanoTime() - startTime) / times;
    }

    public static long testRemove(AbstractCollection<Integer> o) {
        Random rdm = new Random();
        long startTime = System.nanoTime();
        for(int i = 0; i < times; i++) {
            o.remove(rdm.nextInt(Integer.MAX_VALUE));
        }
        return (System.nanoTime() - startTime) / times;
    }

    public static long testContains(AbstractCollection<Integer> o) {
        Random rdm = new Random();
        long startTime = System.nanoTime();
        for(int i = 0; i < times; i++) {
            o.contains(rdm.nextInt(Integer.MAX_VALUE));
        }

        return (System.nanoTime() - startTime) / times;
    }

    public static long[][] testAll(AbstractCollection<Integer> o) {
        long[] testTimes = new long[9];
        for(int i = 0; i < 3; i++) {
            o.clear();
            testTimes[i] = CompareCollection.testAdd(o);
            testTimes[i + 3] = CompareCollection.testRemove(o);
            o.clear();
            CompareCollection.testAdd(o);
            testTimes[i + 6] = CompareCollection.testContains(o);
        }
        long[] averages = new long[3];
        averages[0] = (testTimes[0] + testTimes[1] + testTimes[2]) / 3;
        averages[1] = (testTimes[3] + testTimes[4] + testTimes[5]) / 3;
        averages[2] = (testTimes[6] + testTimes[7] + testTimes[8]) / 3;

        return new long[][]{testTimes, averages};
    }

    public static void printTestTimes(String type, long[][] testTime) {
        System.out.format("%-16s", type);
        for(long t : testTime[0]) {
            System.out.format("%-16d", t);
        }
        for(long t : testTime[1]) {
            System.out.format("%-16d", t);
        }
        System.out.println("");
    }
}
