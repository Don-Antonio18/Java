public class Cat {
    // Write variables for object here
    String owner;
    String name;
    int age;

    // Can also write any methods() for class:
     public String meow() {
        return "MEOW!";
    }

    public String scratch() {
        return "SCRATCH!";
    }

    // ADD PARAMETERS TO OBJECTS 
    public Cat(String name, int age, String owner) {
        // Use 'this.' to refer to an object field
        this.name = name;
        this.age = age;
        this.owner = owner;
    } 
}
