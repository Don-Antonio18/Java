public class GarageManagement {
    public static void main(String[] args) {
        Vehicle[] garage = new Vehicle[5];

        // Create and add vehicles to garage
        garage[0] = new Car("John", "Toyota", "Corolla", "white", 1200000f, 2018, "sedan", 1450);
        garage[1] = new Truck("Debbie", "Ford", "Iveco", "black", 5200000f, 2019, 10, "light");
        garage[2] = new Car("Zack", "Ferrari", "Roma", "red", 12800000f, 2020, "sedan", 3855);
        garage[3] = new Car("Arianna", "Subaru", "Impreza", "blue", 3500000f, 2022, "hatchback", 1995);
        garage[4] = new Truck("Lance", "Mack", "MD6", "red", 10000000f, 2015, 18, "trailer");

        // Calculate and print service charges
        System.out.println("Service Charges for all vehicles:");
        for (Vehicle vehicle : garage) {
            System.out.printf("%s's %s %s: $%.2f%n", 
                vehicle.owner, vehicle.make, vehicle.model, vehicle.serviceCharge());
        }

        // Calculate service charges with delivery for cars only
        System.out.println("\nService Charges including delivery for cars:");
        for (Vehicle vehicle : garage) {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                System.out.printf("%s's %s %s: $%.2f%n", 
                    car.owner, car.make, car.model, car.serviceCharge(5000));
            }
        }

        for (int i=0; i < garage.length; i++) {
            if (garage[i] instanceof Car) {
                System.out.println(garage[i].serviceCharge(5000));
            }
            else{
                System.out.println(garage[i].serviceCharge());
            }

        }

        // Print total number of vehicles
        System.out.println("\nTotal number of vehicles in garage: " + Vehicle.getVehicleCount());
    }
}
