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
     * Tries to add an element to the bin, dependent on capacity.  If the object
     * does not fit, the smallest object possible is removed to make it fit, and 
     * returned to pass to another bucket.
     *
     * The removal function is linear, and at worst, the current largest object must be removed,
     * comparing to each element already within the bucket once and then calling removal.
     * Thus, each individual call of this function is O(n), or equivalently O(2n).
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
                if (valueAt >= overflow) { //If this element was removed, the new element would fit
                    contents.remove(i);
                    remainingCapacity += valueAt;
                    contents.add(toAdd);
                    remainingCapacity -= toAdd;
                    // The order in which we recieve the elements to add is sorted; hence, every element recieved
                    // is not less than each element already within the arraylist.  Because we are always adding these
                    // to the end of the arraylist, it maintains a sorted order.

                    // If we are on the second bin, this sortedness is preserved.  In each case, the smallest possible element
                    // is removed, meaning that in any case, any element removed is larger than any other object previously removed
                    // from that, ensuring that future buckets also recieve elements in sorted order.
                    return valueAt;
                }
            }
            // At this point, no matter what element was removed, the new element could not fit.  Therefore, 
            // it is the smallest object that can be removed to make the bin not be over-full, and thus must
            // return the input.
            return toAdd;
        }
    }

    public ArrayList<Integer> getContents (){
        return contents;
    }

    public int getRemainingCapacity (){
        return remainingCapacity;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Contents: {");
        for (int val:contents){
            output.append(val);
            output.append(", ");
        }
        output.append("}\nRemaing Capacity: ");
        output.append(remainingCapacity);

        return output.toString();
    }
}