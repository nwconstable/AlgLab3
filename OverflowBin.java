public class OverflowBin extends SortingBin {
    public OverflowBin(int i) {
        super(i);
    }

    /**
     * Vacuously adds whatever it is given, without caring about capacity.
     * Elements will be added to this only after they have not successfully been added to 
     * the main sorting bins.
     */
    @Override
    public int insertElement(int toAdd){
        contents.add(toAdd);
        return 0;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("Overflow contents: {");
        for (int val:contents){
            output.append(val);
            output.append(", ");
        }
        output.append("}");

        return output.toString();
    }

    @Override
    public int getRemainingCapacity(){
        return 0;
    }
}