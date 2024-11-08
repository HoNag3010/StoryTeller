import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class Author {
    private LinkedHashMap<String,ArrayList<String>> tokens;
    private Random rand;
    private int adjChance;
    private int advChance;
    private int preChance;
    public int getAdjChance() {
        return adjChance;
    }

    public int getAdvChance() {
        return advChance;
    }

    public int getPreChance() {
        return preChance;
    }

    public void setAdjChance(int sc) {
        if (sc > 10) {
            adjChance = 10;
        } else if (sc < 10) {
            adjChance = 0;
        } else {
            adjChance = sc;
        }
    }

    public void setAdvChance(int nc) {
        if (nc > 10) {
            advChance = 10;
        } else if (nc < 10) {
            advChance = 0;
        } else {
            advChance = nc;
        }
    }

    public void setPreChance(int sc) {
        if (sc > 10) {
            preChance = 10;
        } else if (sc < 10) {
            preChance = 0;
        } else {
            preChance = sc;
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

    public String chooseToken(String category) {
        ArrayList<String> theList;
        if (tokens.containsKey(category)) {
            theList = tokens.get(category);
            return theList.get(rand.nextInt(theList.size()));
        } else {
            return "";
        }
    }

    public ArrayList<String> storyTeller(int count) {
        if (tokens == null) {
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        String sentence;
        int decider;
        for (int i = 0; i < count; i++) {
            sentence = "The ";
            decider = rand.nextInt(10);
            if (decider < adjChance) {
                sentence =  sentence + chooseToken("adj");
            }
            sentence = sentence + chooseToken("n") + chooseToken("v");
            decider = rand.nextInt(10);
            if (decider < advChance) {
                sentence = sentence + chooseToken("adv");
            }
            decider = rand.nextInt(10);
            if (decider < preChance) {
                sentence = sentence + chooseToken("prep") + "the" + chooseToken("n");
            }
            result.add(sentence);
        }
        return result;
    }
}
