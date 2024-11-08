import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the name of the word file: ");
        String fname = scan.nextLine();
        LinkedHashMap<String,ArrayList<String>> tokens = WordFileReader.readTokensFromFile(fname);
        // TokenWriter.writeTokensToScreen(tokens);
        Author au = new Author(tokens);
        int howMany, adjLikely, advLikely, preLikely;
        ArrayList<String> sentences;
        do {
            System.out.print("How many sentences would you like in your story? ");
            howMany = scan.nextInt();
            if (howMany > 0) {
                System.out.print("How frequently should adjectives be used? ");
                adjLikely = scan.nextInt();
                System.out.print("How frequently should adverbs be used? ");
                advLikely = scan.nextInt();
                System.out.print("How frequently should prepositions be used? ");
                preLikely = scan.nextInt();
                au.setAdjChance(adjLikely);
                au.setAdvChance(advLikely);
                au.setPreChance(preLikely);
                sentences = au.storyTeller(howMany);
                System.out.println("Here it is: ");
                printStory(sentences);
                System.out.println();
            } 
        } while (howMany > 0);
    }

    public static void printStory(ArrayList<String> sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    } 
}
