
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input , int key){
        StringBuilder encrypted = new StringBuilder(input);
        String lcAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedLCAlphabet = lcAlphabet.substring(key) + lcAlphabet.substring(0, key);
        String ucAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUCAlphabet = ucAlphabet.substring(key) + ucAlphabet.substring(0, key);
        String alphabet = lcAlphabet + ucAlphabet;
        String shiftedAlphabet = shiftedLCAlphabet + shiftedUCAlphabet;
        for (int i=0; i< encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){                
                char newChar =  shiftedAlphabet.charAt(idx);                                                     
                    encrypted.setCharAt(i,newChar);
            }    
        }    
      return encrypted.toString();  
    }  
    
    public void testEncrypt(){
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }    
    public void testCaesar(){
      int key = 23;
      //FileResource fr = new FileResource();
      //String message = fr.asString();
      String message = "First Legion";
      String encrypted = encrypt(message, key);
      System.out.println("key is " + key + "\n" + encrypted);
    }    
    public String encryptTwoKeys(String input,int key1,int key2)
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n=0;
        String str="";
        String out="";
        for(int k=0;k<input.length();k++)
        {
            char ch=input.charAt(k);            
            String s=Character.toString(ch); 
            if(alpha.contains(s))
            {
                if(n%2==0)
                {
                    str=encrypt(s,key1) ;
                    out=out+str;
                    n+=1;
                }
                else
                {
                    str=encrypt(s,key2);
                    out=out+str;
                    n+=1;
                }
            }
            else if(s.equals(" "))
            {
                n=0;
                out=out+" ";
                
            }
            else
            {
                out=out+s;
            }
        }
        return out;
    }
    public void testEncryptedTwoKeys(){
      //int key = 23;
      //FileResource fr = new FileResource();
      //String message = fr.asString();
      String message = "First Legion";
      String encryptedTwoKeys = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
      System.out.println(encryptedTwoKeys);
    }
}
