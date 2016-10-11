/*
 * Wordifier.java
 *
 * Implements methods for iteratively learning words from a
 * character-segmented text file, and then evaluates how good they are
 *
 * Students may only use functionality provided in the packages
 *     java.lang
 *     java.util
 *     java.io
 *
 * Use of any additional Java Class Library components is not permitted
 *
 * PUT BOTH OF YOUR NAMES HERE
 * Scott Waldron
 * Cachi Lor
 */

import java.util.*;
import java.lang.*;
import java.io.*;


public class Wordifier {

    // Preconditions:
    //    - textFilename is the name of a plaintext input file
    // Postconditions:
    //  - A LinkedList<String> object is returned that contains
    //    all of the tokens in the input file, in order
    // Notes:
    //  - If opening any file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + textFilename
    //        (where textFilename contains the name of the problem file)
    //      and then exit with value 1 (i.e. System.exit(1))
	public static LinkedList<String> loadSentences(String textFilename) {
		LinkedList <String> words = new LinkedList <String>();
		File file = new File(textFilename);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()){
				String curr = sc.next();
				words.add(curr);
			}
		}
		catch (FileNotFoundException ex){
			System.out.println("Error: Unable to open file " + textFilename);
			System.exit(1);
		}
		//System.out.println(words);
	  //System.out.println("This list conatains " + words.size() + " tokens");
		return words;
	}


    // findNewWords
    // Preconditions:
    //    - bigramCounts maps bigrams to the number of times the bigram appears in the data
    //    - scores maps bigrams to its bigram product score
    //    - countThreshold is a threshold on the counts
    //    - probabilityThreshold is a threshold on the bigram product score
    // Postconditions:
    //    - A HashSet is created and returned, containing all bigrams that meet the following criteria
    //        1) the bigram is a key in bigramCounts
    //        2) the count of the bigram is >= countThreshold
    //        3) the score of the bigram is >= probabilityThreshold
    //      Formatting note: keys in the returned HashSet should include a space between the two tokens in the bigram
	public static HashSet<String> findNewWords( HashMap<String,Integer> bigramCounts, HashMap<String,Double> scores, int countThreshold, double probabilityThreshold ) {
		return null;
	}

    // resegment
    // Preconditions:
    //    - previousData is the LinkedList representation of the data
    //    - newWords is the HashSet containing the new words (after merging)
    // Postconditions:
    //    - A new LinkedList is returned, which contains the same information as
    //      previousData, but any pairs of words in the newWords set have been merged
    //      to a single entry (merge from left to right)
    //
    //      For example, if the previous linked list contained the following items:
    //         A B C D E F G H I
    //      and the newWords contained the entries "B C" and "G H", then the returned list would have
    //         A BC D E F GH I
	public static LinkedList<String> resegment( LinkedList<String> previousData, HashSet<String> newWords ) {
		return null;
	}

    // computeCounts
    // Preconditions:
    //    - data is the LinkedList representation of the data
    //    - bigramCounts is an empty HashMap that has already been created
    // Postconditions:
    //    - bigramCounts maps each bigram appearing in the data to the number of times it appears
	public static void computeCounts(LinkedList<String> data, HashMap<String,Integer> bigramCounts ) {
		for (int i = 0; i < data.size() - 1; i++){
			incrementHashMap(bigramCounts, data.get(i)+" "+data.get(i+1), 1);

		}
		//System.out.println(bigramCounts);
		return;
	}

    // convertCountsToProbabilities
    // Preconditions:
    //    - bigramCounts maps each bigram appearing in the data to the number of times it appears
    //    - bigramProbs is an empty HashMap that has already been created
    //    - leftUnigramProbs is an empty HashMap that has already been created
    //    - rightUnigramProbs is an empty HashMap that has already been created
    // Postconditions:
    //    - bigramProbs maps bigrams to their joint probability
    //        (where the joint probability of a bigram is the # times it appears over the total # bigrams)
    //    - leftUnigramProbs maps words in the first position to their "marginal probability"
    //    - rightUnigramProbs maps words in the second position to their "marginal probability"
	public static void convertCountsToProbabilities(HashMap<String,Integer> bigramCounts, HashMap<String,Double> bigramProbs, HashMap<String,Double> leftUnigramProbs, HashMap<String,Double> rightUnigramProbs ) {
		int sum = 0;
		Collection<Integer> values = bigramCounts.values();
		for(Integer number : values){
			sum+= number;
		}
		for(String key : bigramCounts.keySet()){
			Double value = (double) bigramCounts.get(key);
			bigramProbs.put(key, value);
			String left = Character.toString(key.charAt(0));
			if(leftUnigramProbs.containsKey(left)){
				leftUnigramProbs.put(left, leftUnigramProbs.get(left)+value);
			}
			else{
				leftUnigramProbs.put(left, value);
			}

		}
		helperHash(bigramProbs, sum);
		helperHash(leftUnigramProbs, sum);
		//System.out.println(bigramProbs);
		System.out.println(bigramCounts);
		System.out.println(leftUnigramProbs);
		return;
	}
	private static HashMap<String, Double> helperHash(HashMap<String, Double> map, int sum){
		for(String key : map.keySet()){
			map.put(key, map.get(key)/sum);
		}
		return map;
	}
    // getScores
    // Preconditions:
    //    - bigramProbs maps bigrams to to their joint probability
    //    - leftUnigramProbs maps words in the first position to their probability
    //    - rightUnigramProbs maps words in the first position to their probability
    // Postconditions:
    //    - A new HashMap is created and returned that maps bigrams to
    //      their "bigram product scores", defined to be P(w1|w2)P(w2|w1)
    //      The above product is equal to P(w1,w2)/sqrt(P_L(w1)*P_R(w2)), which
    //      is the form you will want to use
	public static HashMap<String,Double> getScores( HashMap<String,Double> bigramProbs, HashMap<String,Double> leftUnigramProbs, HashMap<String,Double> rightUnigramProbs ) {
		return null;
	}

    // getVocabulary
    // Preconditions:
    //    - data is a LinkedList representation of the data
    // Postconditions:
    //    - A new HashMap is created and returned that maps words
    //      to the number of times they appear in the data
	public static HashMap<String,Integer> getVocabulary(LinkedList<String> data) {

		HashMap<String, Integer> vocab = new HashMap<String, Integer>();
		for (int i = 0; i<data.size(); i++){
			incrementHashMap(vocab, data.get(i), 1);
		}
		return vocab;
	}

    // loadDictionary
    // Preconditions:
    //    - dictionaryFilename is the name of a dictionary file
    // Postconditions:
    //    - A new HashSet is created and returned that contains
    //      all unique words appearing in the dictionary
	public static HashSet<String> loadDictionary( String dictionaryFilename ) {
		HashSet<String> dictionary = new HashSet<String>();
		try{
			File file = new File(dictionaryFilename);
			Scanner sc = new Scanner (file);
			while (sc.hasNext()){
				String curr = sc.next();
				dictionary.add(curr);
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("Error: Unable to open file " + dictionaryFilename);
			System.exit(1);
		}
		//System.out.println(dictionary);
		//System.out.println("The dictionary has " + dictionary.size() + " elements");
		return dictionary;
	}

    // incrementHashMap
    // Preconditions:
    //  - map is a non-null HashMap
    //  - key is a key that may or may not be in map
    //  - amount is the amount that you would like to increment key's value by
    // Postconditions:
    //  - If key was already in map, map.get(key) returns amount more than it did before
    //  - If key was not in map, map.get(key) returns amount
    // Notes:
    //  - This method has been provided for you
	private static void incrementHashMap(HashMap<String,Integer> map,String key,int amount) {
		if(map.containsKey(key) ) {
			map.put(key,map.get(key)+amount);
		} else {
			map.put(key,amount);
		}
		return;
	}

    // printNumWordsDiscovered
    // Preconditions:
    //    - vocab maps words to the number of times they appear in the data
    //    - dictionary contains the words in the dictionary
    // Postconditions:
    //    - Prints each word in vocab that is also in dictionary, in sorted order (alphabetical, ascending)
    //        Also prints the counts for how many times each such word occurs
    //    - Prints the number of unique words in vocab that are also in dictionary
    //    - Prints the total of words in vocab (weighted by their count) that are also in dictionary
	// Notes:
    //    - See example output for formatting
	public static void printNumWordsDiscovered( HashMap<String,Integer> vocab, HashSet<String> dictionary ) {
		return;
	}

}
