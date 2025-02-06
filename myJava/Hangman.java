
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


 //! Things to add:
 //! 1. separate lists for food, sports, animals
 //! 2. list showing incorrectly guessed errors so we dont pick it twice

public class Hangman {
    public static void main(String[] args) {
        //? USE EXACT FILE PATH 
       String filePath = "/Users/antoniokerr/Vscode/Java/myJava/words.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null ) {
               words.add(line.trim());
               
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Could not find file");
            return; //? Exit the program if the file is not found
        }
        catch(IOException e ){
            System.out.println("Something went wrong");
            return; //? Exit the program if an IO error occurs
        }

        if (words.isEmpty()) {
            System.out.println("The words list is empty.");
            return; //? Exit the program if the words list is empty
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));
        System.out.println(word);

    Scanner scanner = new Scanner(System.in);
    ArrayList<Character> wordState = new ArrayList<>();
    int wrongGuesses = 0;
    
    //? REPLACES EACH LETTER IN WORD WITH '_'
    for (int i = 0; i < word.length(); i++) {
        wordState.add('_');
    }

    //? WELCOME MESSAGE 
    System.out.println("*******************");
    System.out.println("Welcome to Hangman!");
    System.out.println("*******************");

        while (wrongGuesses < 6) {

            System.out.println(getHangmanArt(wrongGuesses));

            System.out.print("Word: ");

            for (char c: wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            //? GET GUESS AS INPUT FROM USER
            System.out.print("Guess a letter: ");
                                    //? CONVERTS INPUT TO LOWERCASE; RETURNS 1ST LETTER
            char guess = scanner.next().toLowerCase().toLowerCase().charAt(0);

                //? VERIFY IF GUESS IS VALID 
            if(word.indexOf(guess) >= 0) {
                System.out.println("CORRECT guess!\n");

                for ( int i = 0; i < word.length(); i++){
                    
                    //? Checks for letter matches within word
                    if (word.charAt(i) == guess) {
                        //? Updates wordstate with guess at index
                        wordState.set(i, guess);
                    }
                }
                //? Checks if wordstate contains no '_' meaning all letters guessed
                if(!wordState.contains('_')){
                    System.out.println(getHangmanArt(wrongGuesses));
                    System.out.println(" 🥳 YOU WON THE GAME!!! 🎉 ");
                    System.out.println("The word was: " + word);
                    
                    //? EXITS WHILE LOOP
                    break;
                }
            }
            else{
                wrongGuesses++;
                System.out.println("Wrong Guess!\n");
            }

        }

        if(wrongGuesses >= 6) {
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);


    

        }

        scanner.close(); 
         
    } 
 

    

    static String getHangmanArt(int wrongGuesses) {
    //? RETURNS HANGMAN ART BASED ON # OF WRONG GUESSES

        return switch(wrongGuesses) {
            /*  -> is a lambda operator meaning that 
            the code after the arrow is executed */
            case 0 -> """
            +---+
                |
                |
                |
                |
                |
            =========""";
            case 1 -> """
            +---+
            0   |
                |
                |
                |
                |
            =========""";
            case 2 -> """
            +---+
            0   |
            |   |
                |
                |
                |
            =========""";
            case 3 -> """
            +---+
            0   |
           /|   |
                |
                |
                |
            =========""";
            case 4 -> """
            +---+
            0   |
           /|\\  |
                |
                |
                |
            =========""";
            case 5 -> """
            +---+
            O   |
           /|\\  |
           /    |
                | 
                |     
            =========""";
            case 6 -> """
            +---+
            O   |
           /|\\  |
           / \\  |
            |   |
            |   |
            =========""";
            default -> "";
        };

    }
}



