
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> words;
    private ArrayList<Integer>counts;

    public CharactersInPlay()
    {
        words = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person)
    {
        person = person.toLowerCase();
        int index = words.indexOf(person);
        if (index ==-1)
        {
            words.add(person);
            counts.add(words.indexOf(person), 1);
        }
        else
        {
            int indexCounts = counts.get(words.indexOf(person)) + 1;
            counts.set(words.indexOf(person), indexCounts);
        }

    }

    private void findAllCharacters()
    {
        words.clear();
        counts.clear();
        FileResource resource = new FileResource();
        char period ='.';
        for (String word: resource.lines())
        {
            int periodIndex = word.indexOf(period);
            if (periodIndex!=-1)
            {
                String character = word.substring(0, periodIndex);
                    update(character);
            }

        }
    }

    public void tester()
    {
        findAllCharacters();
        System.out.println("# unique words: "+words.size());
        for(int k=0; k < words.size(); k++) {
            System.out.println(counts.get(k)+"\t"+words.get(k));
        }
        //characterNumParts(0,10000);

    }
    private void characterNumParts(int num1, int num2)
    {
        for (int i=0; i<counts.size();i++)
        {
            if (counts.get(i)>= num1 && counts.get(i) <=num2)
                 System.out.println(words.get(i)+"\t" + counts.get(i));
        }
    }
}
