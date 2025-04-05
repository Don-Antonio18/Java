import java.util.Comparator;

public class TruckWeightColourComparator implements Comparator<Truck> {
    
    
    @Override 
    //! compare is abstract method of comparator
    public int compare(Truck truck1, Truck truck2) {

        if (truck1.getMaxLadenWeight() -  truck2.getMaxLadenWeight())  )


        // First compare by max laden weight
        int weightComparison = Double.compare(truck1.getMaxLadenWeight(), truck2.getMaxLadenWeight());
        
        // If weights are equal, compare by colour
        if (weightComparison == 0) {
            return truck1.getColour().compareTo(truck2.getColour());
        }
        
        return weightComparison;
    }
}

