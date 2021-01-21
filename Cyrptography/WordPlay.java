            
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
     String vowel = "AEIOUaeiou";
   for (int i=0; i<vowel.length(); i++){
    char charVowel = vowel.charAt(i);
     if (ch == charVowel){
      return true;
     }
   }
   return false;
 }
public void testIsVowel(){
    boolean value = isVowel('g');
    System.out.println(value);
}
public String replaceVowels(String phrase, char ch){
    StringBuilder retPhrase = new StringBuilder(phrase);
    for (int i=0; i<retPhrase.length(); i++){
         // create a char variable and set it to each individual index of phrase to allow comparison of ch
        char charPhrase = retPhrase.charAt(i); //this give me each letter at i and goes up by 1
        //System.out.println(charPhrase);
        //Find the index of charPhrase in the phrase (call it idx)
        int idx = phrase.indexOf(charPhrase,i); //gives me the index of each 
        System.out.println(idx);
        //System.out.println(isVowel(charPhrase));
        if (isVowel(charPhrase)){
                      
            //Replace the ith character of encrypted with ch
           retPhrase.setCharAt(idx, ch);
           System.out.println(retPhrase);
        }
           }
   return retPhrase.toString();

}
public void testReplaceVowels(){
    System.out.println(replaceVowels("Hello World",'*'));
} 
public String emphasize(String phrase, char ch){
        String newphrase = "";
        for(int k=0; k<phrase.length(); k++){
            char c = phrase.charAt(k);
            if (k%2==0 && ( c == Character.toUpperCase(ch) || c == Character.toLowerCase(ch))){
                newphrase = newphrase + '*';
            }
            else if(k%2==1 && ( c == Character.toUpperCase(ch) || c == Character.toLowerCase(ch))){
                newphrase = newphrase + '+';
            }
            else{
                newphrase = newphrase + c;
            }
        }
        return newphrase;        
    }
   public void testemphasize(){
    System.out.println(emphasize("dna ctgaaactga",'a'));
} 
}
