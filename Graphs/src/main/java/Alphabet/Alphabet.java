/*
 * This Java file contains the declaration
 * of the Alphabet class which contains the
 * methods in order to find the order of any
 * given alphabet.
 * file Alphabet.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     4  April 2023
 * Modified 16 April 2023
 */

package Alphabet;
import java.util.ArrayList;

public class Alphabet {

    public static ArrayList<Character> lexicalOrder(String[] dictionary) {
        Graph<Character> graph = setUpGraphConnections(dictionary);
        return graph.topSort();
    }

    private static Graph<Character> setUpGraphConnections(String[] dictionary) {
        Graph<Character> graph = new Graph<Character>();
        for (int wordIdx = 0; wordIdx < dictionary.length - 1; wordIdx++) {
            String firstWord = dictionary[wordIdx], secondWord = dictionary[wordIdx + 1];
            for (int charIdx = 0; charIdx < Math.min(firstWord.length(), secondWord.length()); charIdx++) {
                char firstLetter = firstWord.charAt(charIdx), secondLetter = secondWord.charAt(charIdx);
                if (firstLetter != secondLetter) {
                    graph.connect(firstLetter, secondLetter);
                    break;
                }
            }
        }
        return graph;
    }
}


