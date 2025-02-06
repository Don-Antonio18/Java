public class catConstructors {
    
    public static void main(String[] args) {
    
    // create new class Objects 
    // Use class methods instead of hardcoding them
    
    Cat cat1 = new Cat("Garfield", 6, "Anthony");
    System.out.println(
        "My name is " + cat1.name 
        + " and I am " + cat1.age + " years old! " );

    Cat cat2 = new Cat("Jarvis", 12, "Tony");
    System.out.println(
        "cat owner = " + cat2.owner
        + "\ncat age = " + cat2.age
        + "\ncat name = " + cat2.name );
    
        
    

    }

 }
    
