public class Truck extends Vehicle implements Comparable<Truck> {
    private int tipperNumber;
    private int registrationNumber;
    private double maxLadenWeight;
    
    
    public Truck(int vehicleIdentificationNumber, int colour, String yearOfManufacture, 
                double initialPrice, int tipperNumber, int registrationNumber, double maxLadenWeight) {
        // Call the parent constructor to initialize vehicle properties
        super(vehicleIdentificationNumber, yearOfManufacture, colour, initialPrice);
        this.tipperNumber = tipperNumber;
        this.registrationNumber = registrationNumber;
        this.maxLadenWeight = maxLadenWeight;
    }
    
    
    public int getTipperNumber() {
        return tipperNumber;
    }
    
    
    public int getRegistrationNumber() {
        return registrationNumber;
    }
    
    
    public double getMaxLadenWeight() {
        return maxLadenWeight;
    }
    
    
    @Override
    public double forceSaleValue(int currentYear) {
        double value = getInitialPrice();
        int yearsOfDepreciation = currentYear - getYearOfManufacture();
        
        // Apply 10% reduction for each year
        for (int i = 0; i < yearsOfDepreciation; i++) {
            value *= 0.9; // Apply 10% reduction
        }
        
        return value;
    }
    
    
    @Override //! compareTo method of Comparable interface
    public int compareTo(Truck other) {
        return this.vehicleIdentificationNumber - other.getVehicleIdentificationNumber();
    }
    
    
    @Override
    public String toString() {
        return "Truck{" +
                "VIN='" + getVehicleIdentificationNumber() + '\'' +
                ", colour='" + getColour() + '\'' +
                ", yearOfManufacture=" + getYearOfManufacture() +
                ", initialPrice=" + getInitialPrice() +
                ", tipperNumber='" + tipperNumber + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", maxLadenWeight=" + maxLadenWeight +
                '}';
    }
}

