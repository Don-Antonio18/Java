 

import java.util.Scanner;

import src.Person;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class FullSolution {

    public static void main(String[] args)
    { 
        TCProgram tcp = new TCProgram();
        Scanner scan = new Scanner(System.in);
        PrintWriter outWriter = null;
        try{
            outWriter = new PrintWriter(new FileOutputStream("case0.vcp"));
        }
        catch (IOException ioe)
        {
            System.out.println("Cannot open output file for case 0");

        }

        //! Ask for interactive mode selection
        System.out.println("Interactive Mode?:[y/n],([n] to read from file and assess test cases for submission)");
        String choice = scan.next().toUpperCase();
        if (choice.equals("Y"))
        {
            char mchoice = 'F';
            EntryScreen ec = new EntryScreen();
            System.out.println("Enter number of testcase[0..6] to prime data, or [F/f] for free entry, or [X/x] to  exit ");
            String menu = scan.next().toUpperCase();
            mchoice = menu.charAt(0);
            try{
                int tcase = Integer.parseInt(menu);
                tcp.loadData(tcase); 
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Initiating free entry");
            }

            //! Loop until X is entered
            while(mchoice!='X')
            {
                System.out.println("[P]erson entry\n[A]pproval info\nContract [B]atch\n[S]how data\npro[C]ess contracts\n[R]eport\ne[X]it");
                menu = scan.next().toUpperCase();
                mchoice = menu.charAt(0);
                switch(mchoice)
                {   
                    //! Person Entry
                    case 'P':{
                        Person p = ec.getPerson(scan);
                        if (p!=null)
                        {
                            tcp.getPlist().add(p);
                            System.out.println("Successfully added person to dataset");
                        }
                        else
                            System.out.println("Something went wrong- Person not added");
                        break;
                    }
                    //! Approval Info
                    case 'A':
                    {
                        ApprovedPerson ap= ec.approvePerson( scan, tcp);
                        if (ap!=null)
                        {
                            tcp.getAPlist().add(ap);
                            System.out.println("Successfully approved person");
                        }
                        else
                            System.out.println("Something went wrong- Person not approved");
                        break;
                    }
                    //! Contract batch
                    case 'B':
                    {
                        TransportCon tc = ec.getTransportCon(scan);
                        if (tc!=null)
                        {
                            tcp.getTClist().add(tc);
                            System.out.println("Successfully added contract batch to dataset");
                        }
                        else
                            System.out.println("Something went wrong- contract batch not added");

                        break;
                    }
                    //! Show data
                    case 'S':
                    {
                        tcp.showData(System.out);
                        break;
                    }
                    //! Apply temporary contract
                    case 'C':
                    {
                        tcp.applyTCons();//vx.setFVlist(
                        if (tcp.countTCons()>0)
                            tcp.applyRemaining();
                        break;
                    }
                    //! Report data
                    case 'R':
                    {
                        tcp.publishData();
                        break;
                    }

                    //! 1(c) Test Cases
                    case 'T': 
                    {
                        TCProgram tcProgram =  new TCProgram();
                        TestCase.runCases(tcProgram);
                        break;
                    }
    
                }
            }
        } // interactive mode
        
        //! Read from file, access test cases
        if (choice.equals("N"))

        {
            // System.out.println("Test functionality currently not implemented. Your first task is to  invoke the tests.");
            
            // 1(b)
            TCProgram tcp1 =new TCProgram();
            TestCase.runCases(tcp1);
        }


        




    } // main
} // class

