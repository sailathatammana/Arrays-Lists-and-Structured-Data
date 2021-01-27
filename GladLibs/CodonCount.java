
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount (){
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        for (int k = start; k<dna.length()-2; k+=3){
            String codon = dna.substring(k, k+3);
            //System.out.println(codon);
            if (!map.containsKey(codon)){
                map.put(codon,1);
            }
            else {
                map.put(codon, map.get(codon)+1);
            }
        }
        for (String c : map.keySet()){
            System.out.println(c + "\t "+ map.get(c));
        }
    }
    public String getMostCommonCodon(){
        int most = 0;
        String mccodon = null;
        for(String s: map.keySet()){
            int count = map.get(s);
            if(count > most) {
                most = count;
                mccodon = s;
            }
        }
        return mccodon;
    }    
    public void printCodonCounts(int start,int end){
        for(String s: map.keySet()){
           if(map.get(s)<=end&& map.get(s)>=start){
               System.out.println(s+"\t "+map.get(s));
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(0,dna);
        String mostcommoncodon = getMostCommonCodon();
        //System.out.println("The total number of unique codons in the reading frame is: " + map.size());
        //System.out.println("The most common codon is: "+ mostcommoncodon + "count: " + map.get(mostcommoncodon));
        //printCodonCounts(1,5);
    }
}
