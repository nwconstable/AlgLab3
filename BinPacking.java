import java.util.Arrays;
import java.util.Scanner;
public class BinPacking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int binCapacity = scanner.nextInt();
        int numBins = 3;
        // ArrayList<Integer> inputValues = new ArrayList<Integer>();

        int[] inputValues = new int[scanner.nextInt()];
        for(int i = 0; i < inputValues.length; i++){
            inputValues[i] = scanner.nextInt();
        }

        scanner.close();
        Arrays.sort(inputValues);

        SortingBin[] bins = new SortingBin[numBins + 1];
        for(int i = 0; i < numBins; i++){
            bins[i] = new SortingBin(binCapacity);
        }
        bins[numBins] = new OverflowBin(binCapacity);

        for(int inputValue:inputValues){
            int toAdd = inputValue;
            int addPos = 0;
            do {
                // Takes whichever value is to be added and tries to add it.
                // If the addition returns a value, it will try to add that value
                // to the next bucket.
                toAdd = bins[addPos].insertElement(toAdd);
                addPos++;
            } while (toAdd != 0); 
            // Always terminates safely; final bucket is an OverflowBucket, which
            // always returns 0 in response to an element being added.

            // In the worst case, this will call insertElement N + 1 times.
            // As shown in sortingBin, each call is O(n), making the while
            // loop be a total of O(n(N + 1));
        }
        // This loop runs n times, for an overall efficiency of O(n^2 * (N + 1)).
        // Because sort was called earlier, that adds an additional O(n log n) time,
        // or O(n^2) in bad cases.
        // These should also hold for big-Theta, which is harder to type.

        printOutput(bins);
    }

    public static void printOutput(SortingBin[] input){
        int totalRemainingCapacity = 0;
        for (SortingBin b:input){
            System.out.println(b);
            totalRemainingCapacity += b.getRemainingCapacity();
        }
        System.out.println("Total remaining capacity: " + totalRemainingCapacity);
    }
}