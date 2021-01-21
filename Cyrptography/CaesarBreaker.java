
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    int[] countLetters(String encrypted) {
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
    
    int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int dkey = getKey(encrypted);
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void testDecrypt() {
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println(decryptTwoKeys(encrypted));
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int k = start; k < message.length(); k += 2) {
            halfString.append(message.charAt(k));
        }
        return halfString.toString();
    }
    
    public void testHalfOfString() {
        String abc = "abcdefgh";
        System.out.println(halfOfString(abc, 0));
        System.out.println(halfOfString(abc, 1));
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // why minus 4, get the distance between maxDex and E; 
        if (maxDex < 4) { // less then 4, need wrap round to compute the distance
            dkey = 26 - (4-dkey);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        int key1 = getKey(s1);
        System.out.println("key1 is " + key1);
        String s2 = halfOfString(encrypted, 1);
        int key2 = getKey(s2);
        System.out.println("key2 is " + key2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - 14, 26 - 24);
    }
    
    public void testDecryptedTowKeys() {
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(decryptTwoKeys(s));
    }
}

