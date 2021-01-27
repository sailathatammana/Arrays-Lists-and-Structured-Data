import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        String slicedString = "";
        for(int i=whichSlice; i < sb.length();i+=totalSlices){
            char c = sb.charAt(i);
            slicedString = slicedString + c;
        }       
        return slicedString;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc=new CaesarCracker(mostCommon);
        for (int i=0; i<key.length; i++){
            String shifted=sliceString(encrypted,i,klength);
            int k=cc.getKey(shifted);
            key[i]=(k);
        }
        
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String line : fr.lines()){
            hs.add(line.toLowerCase());
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for (String word: message.split("\\W+")){
            //System.out.println("The word is " + word);
            if (dictionary.contains(word.toLowerCase())){
                count++;         
                //System.out.println("The count is " + count);
            }                        
        }
        return count;
    }   
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        int klength = 0;
        for (int i = 1; i <= 100; i++){
            int[] key = tryKeyLength(encrypted,i,'e');
            //System.out.println(key.length);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            //System.out.println(decrypted);
            int count = countWords(decrypted, dictionary);
            //System.out.println(count);
            if (count > max){
                max = count;        
                klength = i;
                //System.out.println("count > max >> max = " + max);
            }
        }
        //System.out.println(encrypted);
        System.out.println(klength);
        //System.out.println(mostCommonChar);
        int[] key = tryKeyLength(encrypted, klength, 'e');
        System.out.println(key.length);
        
        System.out.println("maximum valid words = " + max);
        VigenereCipher vc = new VigenereCipher(key);
        //System.out.println(vc.ciphers.length);
        /*ArrayList<Integer> keys = new ArrayList<Integer>();
        System.out.println("The Key length is " + key.length);
        for(int i = 0; i < key.length; i++){
           keys.add(i,key[i]);              
        }
        System.out.println(keys); 
        
        int[] latha = new int[keys.size()];
        for(int j=0; j < keys.size(); j++){
            latha[j] = keys.get(j);
            //System.out.println(latha[j]);
        }
        System.out.println(Arrays.toString(latha));*/
        return vc.decrypt(encrypted);
        
    }
    
    
    
   public void breakVigenere () {
        FileResource fr = new FileResource();
        String str = fr.asString();
        //System.out.println(str);
        FileResource file = new FileResource();
        HashSet<String> dictionary = readDictionary(file);
        System.out.println(dictionary.size());
        String decrypted = breakForLanguage(str, dictionary);
        //breakForLanguage(str, dictionary);
        System.out.println(decrypted.substring(0,150));  
        int[] key = tryKeyLength(str, 38, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted2 = vc.decrypt(str);
        int num = countWords(decrypted2, dictionary);
        System.out.println("number of words counted: " + num);
        /*ArrayList<Integer> keys = new ArrayList<Integer>();
        System.out.println(key.length);
        for(int i = 0; i < key.length; i++){
           keys.set(i,key[i]);  
            
        }
        System.out.println(keys); */
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostchar=0;
        HashMap<Character,Integer> MyHash= new HashMap<Character,Integer>();
        String Alph="abcdefghijklmnopqrstuvwxyz";
        for (String word:dictionary) {
            for(char ch:word.toCharArray()) {
                char low= Character.toLowerCase(ch);
                int ind= Alph.indexOf(low);
                if(!MyHash.containsKey(low)){
                    MyHash.put(low,1);
                }
                else {
                    int amount= MyHash.get(low);
                    MyHash.put(low,amount+1);
                }
        }
    }
        int max=0;
        for (Character ch:MyHash.keySet()) {
            int curramount= MyHash.get(ch);
            if(curramount>max) {
                max= curramount;
                mostchar= ch;
            }
        }
     return mostchar;   
    } 
    
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages) {
    String rightDec= "";
    int topcount=0; 
    String finaldict="";
    for (String s:languages.keySet()) {
        HashSet<String> currdict= languages.get(s);
        String currdec=breakForLanguage(encrypted,currdict);
        int currcount=countWords(currdec,currdict);
            if (currcount>topcount) {
                topcount=currcount;
                rightDec=currdec;
                finaldict=s;
            }
        }
        System.out.println(topcount);
        System.out.println(finaldict);
        return rightDec;
    }
    
    public String breakForLanguages(String encrypted,HashSet<String>dictionary) {
        String RightDec= "";
        int topcount=0;
        //int[] finalkey= null;
        //int finallength= 0;
        for(int k=1;k<101;k++) {
            int [] ans= tryKeyLength(encrypted,k,mostCommonCharIn(dictionary));
            VigenereCipher vc= new VigenereCipher(ans);
            String currdec= vc.decrypt(encrypted);
            int currcount=countWords(currdec,dictionary);
            if (currcount>topcount) {
                topcount=currcount;
                RightDec=currdec;
                //finalkey= ans;
                //finallength= k;
            }
        }
        //System.out.println(Arrays.toString(finalkey));
        //System.out.println(finallength);
        //System.out.println(topcount);
        return RightDec;
    }
    
    public void breakVigeneres () {
        HashMap <String,HashSet<String>> MyHashM= new HashMap <String,HashSet<String>>();
        DirectoryResource dr= new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr= new FileResource(f);
            String Lang= f.getName();
            HashSet<String>currdict= readDictionary(fr);
            MyHashM.put(Lang,currdict);
            System.out.println(Lang);
        }
        FileResource fr= new FileResource();
        String file= fr.asString();
        //FileResource dict= new FileResource();
        //HashSet<String> MyHashS=readDictionary (dict);
        String dec=breakForAllLangs(file,MyHashM);
        System.out.println(dec);
    }
    
    public void tester () {
        FileResource fr= new FileResource();
        String message=fr.asString();
        int[] val=tryKeyLength(message,4,'e');
        for(int i = 0; i < val.length; i++){
        System.out.println(val[i]);   
        }
    }
}
