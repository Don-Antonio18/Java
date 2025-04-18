public class Vehicle {
    protected String owner;
    protected String make;
    protected String model;
    protected String colour;
    protected float estimatedValue;
    protected int yearOfManufacture;
    private static int vehicleCount = 0;  // Track number of vehicles

    public Vehicle(String owner, String make, String model, String colour, float estimatedValue, int yearOfManufacture) {
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.estimatedValue = estimatedValue;
        this.yearOfManufacture = yearOfManufacture;
        vehicleCount++;  // Increment counter when vehicle is created
    }

    public double serviceCharge() {
        return (float) (estimatedValue * 0.01) ; // 1% of estimated value
    }

    public double serviceCharge(int otherCharge) {
        return serviceCharge() + otherCharge;
    }

    public float getEstValue(){
        return estimatedValue;
    }

    public static int getVehicleCount() {
        return vehicleCount;
    }
}
