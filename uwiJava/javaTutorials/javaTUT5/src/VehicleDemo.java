import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VehicleDemo {
    public static void main(String[] args) {
        // Create ArrayList of Trucks
        ArrayList<Truck> truckList = new ArrayList<Truck>();
        
        // Add sample trucks

        
        // Display original list
        System.out.println("Original truck list:");
        for (Truck truck : truckList) {
            System.out.println(truck);
        }
        
        // Sort by vehicle identification number (natural ordering)
        Collections.sort(truckList);
        System.out.println("\nTrucks sorted by vehicle identification number:");
        for (Truck truck : truckList) {
            System.out.println(truck);
        }
        
        // Sort by max laden weight, then by colour
        Collections.sort(truckList, new TruckWeightColourComparator());
        System.out.println("\nTrucks sorted by max laden weight, then by colour:");
        for (Truck truck : truckList) {
            System.out.println(truck);
        }
        
        
        }
        
        // Calculate and display forced sale value for a truck
        Truck sampleTruck = truckList.get(0);
        int currentYear = 2023;
        System.out.println("\nForced sale value of a truck from " + 
                          sampleTruck.getYearOfManufacture() + 
                          " in " + currentYear + ": $" + 
                          sampleTruck.forceSaleValue(currentYear));
    }
}
