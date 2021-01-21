
/**
 * Write a description of CaesarCipher1 here.'
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher1 {
    private String alphabet;
    private String shiftedAlphabet;
    //private String key;
    //private String mainKey;
    public CaesarCipher1(int key){
        String lcAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet = lcAlphabet.substring(key) + lcAlphabet.substring(0, key);
        String ucAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet = ucAlphabet.substring(key) + ucAlphabet.substring(0, key);
        alphabet = lcAlphabet + ucAlphabet;
        shiftedAlphabet = shiftedLCAlphabet + shiftedUCAlphabet;
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i =0; i< sb.length(); i++){
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            if(idx != -1){                
                c = shiftedAlphabet.charAt(idx);                                                     
                sb.setCharAt(i,c);
            }
        }       
        return sb.toString();
    }    
    private String mainKey;
    public String decrypt(String input , int key){
        String lowerInput = input.toLowerCase(); 
        StringBuilder Decrypted =new StringBuilder(input);
        StringBuilder decrypted = new StringBuilder(lowerInput);
        CaesarCipher1 cc = new CaesarCipher1(26 - key);
        return cc.encrypt(input);
    }    
    public int[] countLetters(String encrypted) {
        String alphet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < encrypted.length(); k++) {
            char currChar = Character.toLowerCase(encrypted.charAt(k));
            int index = alphet.indexOf(currChar);
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    /*private int getKey(String s){ 
        int[] letterFreqs = countLetters(s); 
        int maxindex = maxIndex(letterFreqs); 
        int dkey = maxindex - 4;
        if (maxindex < 4) { 
            dkey = 26 - (4-maxindex); 
        } 
        return dkey; 
    } */
    private String halfOfString(String message, int start){ 
        String answer = "";    
        for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);    	 
        } 
        return answer; 
    }
    public String breakCaesarCipher (String str1){
        int[] freqs = countLetters(str1);
        int maxDex = maxIndex(freqs);
        int dkey = 0;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        else{
            dkey = maxDex - 4;
        }
        CaesarCipher1 cc = new CaesarCipher1(26-dkey);        
        return cc.encrypt(str1);     
    } 
    public void TestCaesarCipher() {
    int key = 15;
    //FileResource fr = new FileResource();
    String message = "Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?";
    String encrypted = encrypt(message);
    System.out.println(encrypted);
    String decrypted = decrypt(encrypted,key);
    System.out.println(decrypted);
   }
    public void simpleTest() { 
         /*FileResource fr = new FileResource(); 
         String message = fr.asString(); 
         CaesarCipher1 cc = new CaesarCipher1(17,3); 
         String encrypted = c.encrypt(message); 
         System.out.println(encrypted); 
         System.out.println(c.decrypt(encrypted)); */
         String message = breakCaesarCipher("WIVV TRBV ZE KYV TFEVIVETV IFfd!"); 
         System.out.println(message); 
    }
}
