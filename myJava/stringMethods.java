// This is a simple Java program.
// FileName : "HelloWorld.java".

public class stringMethods {
    // Your program begins with a call to main().
    // Prints "Hello, World" to the terminal window.
    public static void main(String args[])
    {
        
        OtherClass.printMessage();
        stringMethods.printStringLength();
    }

    public static void printStringLength() {
        String txt = "Antonio";
        System.out.println("Length of sentence: " + txt.length());
    }
}   
