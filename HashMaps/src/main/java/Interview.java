/*
 * This Java file contains the declaration
 * of the Interview class which
 * contains all the methods of the class
 * file Interview.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     26 february 2023
 * Modified 15 february 2023
 */
public final class Interview {

    /** Justify Time Complexity  : Average Case O(n+m)
     ** Justify Space Complexity : Worst Case O(n+m)
     * In order to give an explanation on the complexity of the findMostCommonValidWord method,
     * we have to understand what each line of code does.
     * ============================================================================================
     * First line:
     * Transforms all characters of the given sentence to lower case and splits all characters
     * that seperated by an empty space and returns a string array of all words contained in the
     * sentence. The variable words is affected by the result of the split. In other words, we
     * iterate over all the characters of the given sentence.
     * ============================================================================================
     * Second line:
     * Uses the method fillHashMap to first fill with all the words contained in the sentence and
     * remove all the stop words that are contained in the HashMap. Finally, the variable map is
     * affected to the fillHashMap's return hashmap which contain all the words of the sentence
     * excluding the stop words.
     * ============================================================================================
     * Third line:
     * Iterates through the elements of the filtered hashmap and returns a Pair of the word and
     * the value.
     * ********************************************************************************************
     * Time Complexity: Average Case * Go see the analysis of the time complexity of each private methods
     * O (n + (n + m) + n)
     * => O (3n + m)
     * => O (n + m)
     * ********************************************************************************************
     * Space Complexity : Worst Case
     * To evaluate the space complexity we need to measure the total amount of memory that the
     * algorithm needs to run according to its inputs size. In our case the two inputs of the
     * algorithm are phrase which is a string and stopwords which is a string array. Considering
     * the worst case scenario, a big phrase and a lot of stopwords, our algorithm would need memory
     * for both of those parameters. So the space complexity would be O (n + m). But let's dig deeper.
     * O (n + m + n + n + m + n + m)
     * => O (4n + 3m)
     * => O (n + m)
     * ============================================================================================
     * findMostCommonValidWord parameters => n + m
     * ============================================================================================
     * First line:
     * n since we need space to iterate over all the characters of the sentence in order to change
     * to lower case, split. Moreover, we affect the variable words with the returned string array.
     * So the space complexity is O (n).
     * ============================================================================================
     * Second line:
     * n + m since we take in parameters of the method fillHashMap the variable words and the
     * parameter stopwords.
     * So the space complexity is O (n + m)
     * ============================================================================================
     * Third line:
     * n + m since we take in parameters of the method findMaxValidWord the variable map and the
     * parameter stopwords.
     * So the space complexity is O (n + m)
     * ============================================================================================
     */
    public static Pair findMostCommonValidWord(String phrase, String[] stopwords) {
        String[] words = phrase.toLowerCase().split(" ");
        HashMap<String, Integer> map = fillHashMap(words, stopwords);
        return findMaxValidWord(map, words);
    }

    /*
     * Analysis of the complexity of fillHashMap(String[] words, String[] stopwords)
     * ********************************************************************************************
     * Time Complexity: Average Case
     * O (1 + n * (1 + 1 + 1 + 1) + m * 1 + 1)
     * => O (2 + 4n + m)
     * => O (n + m)
     */
    private static HashMap<String, Integer> fillHashMap(String[] words, String[] stopwords) {
        HashMap<String, Integer> map = new HashMap<>(); // Time Complexity: Average Case O(1)
        for (String word : words) { // Time Complexity: Average Case O(n)
            if (word.length() > 1) { // Time Complexity: Average Case O(1)
                if (!map.containsKey(word)) // Time Complexity: Average Case O(1)
                    map.putIfAbsent(word, 1); // Time Complexity: Average Case O(1)
                else
                    map.put(word, map.get(word) + 1); // Time Complexity: Average Case O(1)
            }
        }
        for (String word: stopwords) // Time Complexity: Average Case O(m)
            map.remove(word.toLowerCase()); // Time Complexity: Average Case O(1)
        return map; // Time Complexity: Average Case O(1)
    }
    /*
    * Analysis of the complexity of findMaxValidWord(HashMap<String, Integer> map, String[] words)
    * ********************************************************************************************
    * Time Complexity: Average Case
    * O (1 + 1 + 1 + 1 + n * (1 + 1 + 1 + 1 + 1 + 1 + 1) + 1)
    * => O (5 + n * 7)
    * => O (5 + 7n)
    * => O (n)
    * ********************************************************************************************
    */
    private static Pair findMaxValidWord(HashMap<String, Integer> map, String[] words) {
        int maxValue = 0, value = 0; // Time Complexity: Average Case O(1)
        String maxWord = null; // Time Complexity: Average Case O(1)
        if (map.isEmpty()) // Time Complexity: Average Case O(1)
            return new Pair(null, null); // Time Complexity: Average Case O(1)
        for (String word : words) { // Time Complexity: Average Case O(n)
            if (map.containsKey(word)) { // Time Complexity: Average Case O(1)
                value = map.get(word); // Time Complexity: Average Case O(1)
                if (value > maxValue) { // Time Complexity: Average Case O(1)
                    maxValue = value; // Time Complexity: Average Case O(1)
                    maxWord = word; // Time Complexity: Average Case O(1)
                }
                else if (value == maxValue && maxWord != null && word.compareTo(maxWord) < 0) // Time Complexity: Average Case O(1)
                    maxWord = word; // Time Complexity: Average Case O(1)
            }
        }
        return new Pair(maxWord, maxValue); // Time Complexity: Average Case O(1)
    }
}



