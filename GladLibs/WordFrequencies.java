
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
       myWords = new ArrayList<String>();
       myFreqs = new ArrayList<Integer>();
    }    
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource(); 
        for(String s : resource.words()){
            s= s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
               myWords.add(s);
                myFreqs.add(1); 
            }    
            else {
                int frq = myFreqs.get(index);
                myFreqs.set(index,frq+1); 
            }    
        }    
    }    
    
    public int findIndexOfMax (){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester(){        
        findUnique();
        //System.out.println("No of unique words: "+myFreqs.size());
        //System.out.println("# unique words: "+myWords.size());
        for(int k=0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
        }
        System.out.println("No of unique words: "+myFreqs.size());
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+myWords.get(index)+" "+myFreqs.get(index));
    }    
}
