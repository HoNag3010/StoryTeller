import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class WordFileReader {
    public static LinkedHashMap<String,ArrayList<String>> readTokensFromFile(String filename) {
        try {
            LinkedHashMap<String,ArrayList<String>> result = new LinkedHashMap<>();
            Scanner fsc = new Scanner(new File(filename));
            String line, word, category;
            String[] parts;
            ArrayList<String> theList;
            while (fsc.hasNextLine()) {
                line = fsc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                parts = line.split("\t");
                word = parts[0];
                category = parts[1];
                if (!result.containsKey(category)) {
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
