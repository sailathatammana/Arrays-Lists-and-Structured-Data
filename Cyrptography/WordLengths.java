
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;


public class WordLengths {
       public int[] countWordLengths(FileResource fr, int[] counts) {
        int lastIdx = counts.length - 1; 
        
        for (String word : fr.words()) {            
            int len = word.length();
            
            if (!Character.isLetter(word.charAt(0))) {
                len -= 1;
            }
            
            if (len > 0 && !Character.isLetter(word.charAt(word.length()-1))) {
                len -= 1;
            }
            
            if (len >= lastIdx) {
                counts[lastIdx] += 1;
            }
            
            else {
                counts[len] += 1;
            }
        }        
        return counts;
       }
       public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int [] counts = new int[31];
        int [] result = countWordLengths(resource, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println(counts[i] + " word(s) of length " + (i + 1));
        }
        int maxlength = indexOfMax(result);
        System.out.println("The most common word length in the f        ile is " + maxlength);        
       } 
       public int indexOfMax(int[] values) {
        int index = 0;
        for(int i = 0; i < values.length; i++){
            if (values[i] > values[index]) {
                index = i;
            }
        }
        return index;
    }
}
