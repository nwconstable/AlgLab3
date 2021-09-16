import java.util.ArrayList;

public class SortingBin {
    private final int maxCapacity;
    private int remainingCapacity;
    protected ArrayList<Integer> contents = new ArrayList<Integer>();

    public SortingBin (int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.remainingCapacity = maxCapacity;
    }

    /**
     * Tries to add an element to the bin, dependent on capacity.
     * 
     * @param toAdd
     * @return 0 if the element fit into the bin without removing anything, 
     * otherwise returns the smallest element that does not fit 
     * between the new element to be added or the elements the bin previously contained. 
     */
    public int insertElement (int toAdd){
        if (remainingCapacity == 0) {
            //The bin is exactly full and so can't be made any bigger,
            //so there's no reason to try to add anything.
            return toAdd;
        }

        if (toAdd <= remainingCapacity) {
            //The element fits as-is, so it can be added trivially.
            contents.add(toAdd);
            remainingCapacity -= toAdd;
            return 0;
        } else { 
            int overflow = toAdd - remainingCapacity;
            for (int i = 0; i < contents.size(); i++){
                int valueAt = contents.get(i);
                if (toAdd - valueAt <= overflow) { //If this element was removed, the new element would fit
                    contents.remove(i);
                    remainingCapacity -= valueAt;
                    contents.add(toAdd);
                    remainingCapacity += toAdd;
                    // The order in which we recieve the elements to add is sorted; hence, every element recieved
                    // is not less than each element already within the arraylist.  Because we are always adding these
                    // to the end of the arraylist, it maintains a sorted order.
                    return valueAt;
                }
            }
            // At this point, no matter what element was removed, the new element could not fit.  Therefore, 
            // it is the smallest object that can be removed to make the bin not be over-full, and thus must
            // return the input.
            return toAdd;
        }
    }
}