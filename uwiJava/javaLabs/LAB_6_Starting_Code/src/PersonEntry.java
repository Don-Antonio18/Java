package src;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class PersonEntry extends JFrame
{
    private JTextField  txtName;       //name
    private JTextField  txtAge;        //age
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     cmdClearAll;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private PersonListing personListing; // reference personlisting
    private PersonEntry instance; // reference personentry
    
    //Modify the PersonEntry object so that the interface looks like that presented in
    //Figure 3.Expected Appearance of Person Entry Screen
    //Hints:
    //i. You may need to add a JLabel and a JCheckBox
    //ii. The layout from the previous developer for the panel displays data on two rows.
    
    private JCheckBox chkWillPublish;
    
    // Modify PersonEntry so that it is able to store an instance of
    // PersonListing.
    
    
//    Modify the Constructor of PersonEntry so that it accepts an
//    instance of PersonListing, and then sets the local instance variable to the value
//    accepted by the constructor
    public PersonEntry(PersonListing personListing) {
        this.personListing = personListing;
        this.instance = this; // set instance to THIS
        
        setTitle("Entering new person");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        
        pnlDisplay.setLayout(new GridLayout(3, 2));
        
        // custom colours
        Color backgroundColour = Color.LIGHT_GRAY;
        Color foregroundColour = Color.BLACK;
        Color buttonBackgroundColour = new Color(0, 255, 255);
        Color buttonForegroundColour = Color.BLACK;
        pnlDisplay.setBackground(backgroundColour);
        pnlCommand.setBackground(backgroundColour);
        
        
        
        pnlDisplay.add(new JLabel("Name:"));
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        
        pnlDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        pnlDisplay.add(txtAge);
        
        pnlDisplay.add(new JLabel("Will Publish?:"));
        chkWillPublish = new JCheckBox();
        pnlDisplay.add(chkWillPublish);
        
        
        
        
        cmdSave = new JButton("Save");
        cmdClose = new JButton("Close");
        
        cmdClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instance.setVisible(false);
            }
        });
        
        cmdSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String ageText = txtAge.getText();
                boolean willPublish = chkWillPublish.isSelected();
                
                if (validateName(name) && validateAge(ageText)) {
                    int age = Integer.parseInt(ageText);
                    Person person = new Person(name, age, willPublish);
                    personListing.addPerson(person);
                    instance.setVisible(false); // close form
                } else {
                    JOptionPane.showMessageDialog(instance, "Invalid input--Please enter both first and last name, as well as a valid age.");
                }
            }
        });
        
        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
    }
    private boolean validateName(String name) {
        String[] parts = name.split(" ");
        return parts.length >= 2; // check if there are at least two parts
    }
    
    private boolean validateAge(String ageText) {
        try {
            Integer.parseInt(ageText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}