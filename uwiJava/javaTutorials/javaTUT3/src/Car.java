public class Car extends Vehicle {
    private String bodyType;
    private int ccRating;

    public Car(String owner, String make, String model, String colour, float estimatedValue, 
               int yearOfManufacture, String bodyType, int ccRating) {
        super(owner, make, model, colour, estimatedValue, yearOfManufacture);
        this.bodyType = bodyType;
        this.ccRating = ccRating;
    }

    @Override
    public double serviceCharge() {
        return 0.75 * (getEstValue() / ccRating);
    }

    public double serviceCharge(int otherCharge) {
        return serviceCharge() + otherCharge;
    }
}
