import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * This class runs the main story telling
 */
public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);          // Declare scanner

        printWelcomeBanner();

        System.out.print("Enter the name of the word file: ");          // Ask for user's file
        String fname = scan.nextLine();
        LinkedHashMap<String,ArrayList<String>> tokens = WordFileReader.readTokensFromFile(fname);          // Generate tokens based on the file
        Author au = new Author(tokens);                 // Input token to constructor class
        int howMany, adjLikely, advLikely, preLikely;
        String restart = "";                // Initialize to use later
        ArrayList<String> sentences;
        do {            // do.. while loop to loop until user tells to stop
            System.out.print("\nHow many sentences would you like in your story? ");
            howMany = scan.nextInt();
            if (howMany > 0) {
                System.out.println("On a scale of 0 to 10 ...");
                System.out.print("  How frequently should adjectives be used? ");
                adjLikely = scan.nextInt();
                System.out.print("  How frequently should adverbs be used? ");
                advLikely = scan.nextInt();
                System.out.print("  How frequently should prepositions be used? ");
                preLikely = scan.nextInt();
                au.setAdjChance(adjLikely);         // Set chance based on user input
                au.setAdvChance(advLikely);
                au.setPreChance(preLikely);
                sentences = au.storyTeller(howMany);            // Generate sentences based on times user tells
                System.out.println("Here it is: ");
                printStory(sentences);              // Print sentences generated
                System.out.println();
                System.out.print("Would you like another story (y or n)? ");
                scan.nextLine();
                restart = scan.nextLine();          // If you tell it "y", the loop will restart
            } 
        } while (restart.equalsIgnoreCase("y"));

        System.out.println("\nThank you for using StoryTeller!");       // Thank you message
    }

    /**
     * This function prints the whole story
     * @param sentences is the list of sentences generated from Author class
     */
    public static void printStory(ArrayList<String> sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    } 

    /**
     * This function prints the welcome banner
     */
    public static void printWelcomeBanner() {
        System.out.println("****************************************************");             // I don't want to make a sentence too long, so I cut it into multiple line
        System.out.println("                   STORYTELLER V1.0                 ");             // I think I can use printf to format this but I don't want to adjust it too thoroughly
        System.out.println("****************************************************");
        System.out.println("\nWelcome to StoryTeller, a sophisticated electronic"); 
        System.out.println("author. This program reads from a list of words of ");
        System.out.println("various parts of speech and creates a story from ");
        System.out.println("them. You can tune the richness of the writing by ");
        System.out.println("changing how frequently adjectives, adverbs, and ");
        System.out.println("prepositions should be included.");
        System.out.println("");
    }
}