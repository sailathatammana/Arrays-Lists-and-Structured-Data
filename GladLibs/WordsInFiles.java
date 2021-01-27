
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsInFiles;
    public WordsInFiles() {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource txtFile = new FileResource(f);
        //ArrayList<String> wordLoc = new ArrayList<String>();
        String filename = f.getName();
        
        for (String word : txtFile.words()) {
            ArrayList<String> wordLoc = new ArrayList<String>();
            if (!wordsInFiles.containsKey(word)) {
                wordsInFiles.put(word, wordLoc);
                int index = wordLoc.indexOf(filename);
                if (index == -1) {
                    wordLoc.add(filename);
                }
            }
            else {
                wordLoc = wordsInFiles.get(word);
                int index = wordLoc.indexOf(filename);
                if (index == -1) {
                    wordLoc.add(filename);
                }
            }
        }
    }
    private void buildWordFileMap() {
        wordsInFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    private int maxNumber() {
        int maxSize = 0;
        for (String word : wordsInFiles.keySet()) {
            ArrayList<String> files = new ArrayList<String>(wordsInFiles.get(word));
            int numberOfFiles = files.size();
            if (numberOfFiles > maxSize) {
                maxSize = numberOfFiles;
            }
        }
        return maxSize;
    }
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : wordsInFiles.keySet()) {
            ArrayList<String> files = new ArrayList<String>(wordsInFiles.get(word));
            int numOfFiles = files.size();
            if (numOfFiles == number) {
                words.add(word);
            }
        }
        return words;
    }
    private void printFilesIn(String word){
        ArrayList<String> files = new ArrayList<String>(wordsInFiles.get(word));
        for (String file : files) {
            System.out.println(file);
        }
    }
    public void tester() {
        buildWordFileMap();
        int maxNumOfFiles = maxNumber();
        System.out.println("max num of files any word is in: " + maxNumOfFiles);
        ArrayList<String> wordsWithMaxFiles = wordsInNumFiles(maxNumOfFiles);
        System.out.println("words with this num of files: ");
        for (String word : wordsWithMaxFiles) {
            System.out.println(word);
        }
        
        System.out.println(wordsInFiles);
    }
}
