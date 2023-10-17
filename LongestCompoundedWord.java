import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class LongestCompoundedWord{
    //recursive function that checks if a given word can be formed by combining other words in the set
      public static boolean isCompound(String word, Set<String> set) {
        if (set.isEmpty()) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            //split the word into a prefix and a suffix at every possible position
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
           //If the prefix and suffix is in the set OR compounded word itself --> word is compounded
            if ( set.contains(prefix) && (set.contains(suffix)  || isCompound(suffix, set) )) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
       // reading the list of words from the input file and storing them in an ArrayList called words
        String inputFileName = "Input_02.txt";
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;}
       // Sorting in descending order
        Collections.sort(words, (s1, s2) -> s2.length() - s1.length());

        String longestCompound = null;
        String secondLongestCompound = null;

        Set<String> set = new HashSet<>(words);
       //iterates through the sorted list of words
        for (String word : words) {
            set.remove(word);
       // checks if word is formed by combining other words excluding itself
            if (isCompound(word, set)) {
                if (longestCompound == null) {
                    longestCompound = word;
                } else if (secondLongestCompound == null) {
                    secondLongestCompound = word;
                } else {
                    break; 
                }
            }
        }
    //Print the longest and second longest compounded word
        System.out.println("Longest Compound Word: " + longestCompound);
        System.out.println("Second Longest Compound Word: " + secondLongestCompound);
    }
}
 