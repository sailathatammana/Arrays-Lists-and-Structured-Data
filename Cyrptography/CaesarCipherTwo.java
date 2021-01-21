
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet1;
    private String alphabet2;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    //private String key;
    //private String mainKey;
    public CaesarCipherTwo(int key1,int key2){
        String lcAlphabet1 = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet1 = lcAlphabet1.substring(key1) + lcAlphabet1.substring(0, key1);
        String ucAlphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet1 = ucAlphabet1.substring(key1) + ucAlphabet1.substring(0, key1);
        alphabet1 = lcAlphabet1 + ucAlphabet1;
        shiftedAlphabet1 = shiftedLCAlphabet1 + shiftedUCAlphabet1;
        String lcAlphabet2 = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet2 = lcAlphabet2.substring(key2) + lcAlphabet2.substring(0, key2);
        String ucAlphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet2 = ucAlphabet2.substring(key2) + ucAlphabet2.substring(0, key2);
        alphabet2 = lcAlphabet2 + ucAlphabet2;
        shiftedAlphabet2 = shiftedLCAlphabet2 + shiftedUCAlphabet2;
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i =0; i< sb.length(); i++){
            char c = sb.charAt(i);
            if( i%2 == 0){
            int idx = alphabet1.indexOf(c);
            if(idx != -1){                
                c = shiftedAlphabet1.charAt(idx);
                sb.setCharAt(i,c);
            }
            }
            else {
            int idx = alphabet2.indexOf(c);
            if(idx != -1){                
                c = shiftedAlphabet2.charAt(idx);
                sb.setCharAt(i,c);
            }
        }
        }       
        return sb.toString();
    }   
    private String mainKey;
    public String decrypt(String input , int key1, int key2){
        String lowerInput = input.toLowerCase(); 
        StringBuilder Decrypted =new StringBuilder(input);
        StringBuilder decrypted = new StringBuilder(lowerInput);
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }  
    public void TestCaesarCipherTwo() {
    int key1 = 21;
    int key2 = 8;
    //FileResource fr = new FileResource();
    String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    String encrypted = encrypt(message);
    System.out.println(encrypted);
    String decrypted = decrypt(encrypted,key1, key2);
    System.out.println(decrypted);
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
    public void simpleTest() { 
         /*FileResource fr = new FileResource(); 
         String message = fr.asString(); 
         CaesarCipherTwo cc = new CaesarCipherTwo(17,3); 
         String encrypted = cc.encrypt(message); 
         System.out.println(encrypted); 
         System.out.println(cc.decrypt(encrypted,17, 3)); */
         String message = breakCaesarCipher("WIVV TRBV ZE KYV TFEVIVETV IFfd!"); 
         System.out.println(message); 
    }
}
