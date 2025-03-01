public abstract class Vehicle {
    protected int vehicleIdentificationNumber;
    protected String colour;
    protected int yearOfManufacture;
    protected double initialPrice;

    public Vehicle(int vehicleIdentificationNumber, String colour, int yearOfManufacture, double initialPrice) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.colour = colour;
        this.yearOfManufacture = yearOfManufacture;
        this.initialPrice = initialPrice;
    }

    public int getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public String getColour() {
        return colour;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }
    
    public double getInitialPrice() {
        return initialPrice;
    }

    // This is the way that the abstract class is able to tell another
    // class that it needs to override it. 
    public abstract double forceSaleValue(int currentYear);

}
