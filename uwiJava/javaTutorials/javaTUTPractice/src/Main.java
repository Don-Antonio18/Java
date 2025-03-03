public class Main {
    public static void main(String[] args) throws Exception {
        Person Mary = new Person("Mary", "Parker", 10, 0.0);
        Person Jane = new Person("Jane", "Smith", 10, 1000);

        //Update Mary's network to show that she has been paid for 5 hours work.
        Mary.updateWorthFromWork (50);

        System.out.println(Mary);
        System.out.println(Jane);
    }
}
