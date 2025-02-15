public class Truck extends Vehicle {
    private int numberOfWheels;
    private String classification;

    public Truck(String owner, String make, String model, String colour, float estimatedValue, 
                int yearOfManufacture, int numberOfWheels, String classification) {
        super(owner, make, model, colour, estimatedValue, yearOfManufacture);
        this.numberOfWheels = numberOfWheels;
        this.classification = classification;
    }


    
}
