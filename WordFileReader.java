import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * This class reads the given file
 */
public class WordFileReader {
    /**
     * This class takes the file given from the App class to read and split to Arraylist inside the LinkedHashmap
     * @param filename is the file user chose
     * @return if the file is not found, it will return null. Otherwise, it will split the text file given by space and input inside the LinkedHashMap, and return it
     */
    public static LinkedHashMap<String,ArrayList<String>> readTokensFromFile(String filename) {
        try {
            LinkedHashMap<String,ArrayList<String>> result = new LinkedHashMap<>();
            Scanner fsc = new Scanner(new File(filename));
            String line, word, category;
            String[] parts;
            ArrayList<String> theList;          // Initialize 
            while (fsc.hasNextLine()) {         // If there is another line remained in the file, the loop continues
                line = fsc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                parts = line.split(" ");        // Split based on " ", I thought the space was bigger so I used \t
                word = parts[0];
                category = parts[1];
                if (!result.containsKey(category)) {                // Make a new category if there is none in the LinkedHasMap
                    result.put(category, new ArrayList<>());
                }
                theList = result.get(category);
                theList.add(word);
            }
            fsc.close();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
