/**
 * Main entry point for the StudyWise application.
 * Initializes and launches the main menu interface on the Event Dispatch Thread
 * to ensure proper GUI functionality.
 */
import javax.swing.*;


public class MainDriver {
    /**
     * Main method that launches the application.
     * Uses SwingUtilities.invokeLater to ensure thread safety.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenu.createAndShowGUI();
            }
        });
    }
}

//terminal command to run:
/*
 java -jar /Users/antoniokerr/antJava/Java/uwiJava/JavaProjectTwo/out/artifacts/JavaProjectTwo_jar/JavaProjectTwo.jar
 
 */