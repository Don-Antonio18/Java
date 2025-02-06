public class OtherClass {

    public static void main(String args[]) {

        printMessage();
    }

    public static void printMessage() {
        String message = "Hello from OtherClass\n";
        System.out.println(message + " This message is " + message.length() + " chars long ");

    }
}
