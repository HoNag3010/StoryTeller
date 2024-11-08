import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * This class generates sentences
 */
public class Author {
    private LinkedHashMap<String,ArrayList<String>> tokens;
    private Random rand;
    private int adjChance;
    private int advChance;
    private int preChance;          // Initialize
    public int getAdjChance() {
        return adjChance;
    }

    public int getAdvChance() {
        return advChance;
    }

    public int getPreChance() {
        return preChance;
    }

    /**
     * These three functions doing the same job - adjust the chances to be compatible with the program
     * @param c is the chance user gave to be adjusted to fit the program
     */
    public void setAdjChance(int c) {
        if (c > 10) {
            adjChance = 10;
        } else if (c < 0) {
            adjChance = 0;
        } else {
            adjChance = c;
        }
    }

    public void setAdvChance(int c) {
        if (c > 10) {
            advChance = 10;
        } else if (c < 0) {
            advChance = 0;
        } else {
            advChance = c;
        }
    }

    public void setPreChance(int c) {
        if (c > 10) {
            preChance = 10;
        } else if (c < 0) {
            preChance = 0;
        } else {
            preChance = c;
        }
    }

    public Author() {
        this(null,0,0, 0);
    }

    public Author(LinkedHashMap<String,ArrayList<String>> tokens) {
        this(tokens,3,3,3);
    }

    public Author(LinkedHashMap<String,ArrayList<String>> tokens, int adjChance, int advChance, int preChance) {
        this.tokens = tokens;
        setAdjChance(adjChance);
        setAdvChance(advChance);
        setPreChance(preChance);
        rand = new Random();
    }

    /**
     * This function returns a random word based on category
     * @param category is adj, adv, n, v, prep in the LinkedHashMap
     * @return it should return the random word
     */
    public String chooseToken(String category) {
        ArrayList<String> theList;
        if (tokens.containsKey(category)) {
            theList = tokens.get(category);
            return theList.get(rand.nextInt(theList.size()));
        } else {
            return "";
        }
    }

    /**
     * This function generates sentences
     * @param count is how many sentences user want
     * @return this should return ArrayList of sentence, if the tokens are not null
     */
    public ArrayList<String> storyTeller(int count) {
        if (tokens == null) {
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        String sentence;
        int decider;
        for (int i = 0; i < count; i++) {           // for loop to loop for times input
            sentence = "The";
            decider = rand.nextInt(10);         // Randomize the chance to generate certain word
            if (decider < adjChance) {
                sentence = sentence + " " + chooseToken("adj");         // Add word if succeeded
            }
            sentence = sentence + " " + chooseToken("n") + " " + chooseToken("v");
            decider = rand.nextInt(10);
            if (decider < advChance) {
                sentence = sentence + " " + chooseToken("adv");
            }
            decider = rand.nextInt(10);
            if (decider < preChance) {
                sentence = sentence + " " + chooseToken("prep") + " the " + chooseToken("n");
            }
            sentence = sentence + ".";
            result.add(sentence);
        }
        return result;
    }
}
