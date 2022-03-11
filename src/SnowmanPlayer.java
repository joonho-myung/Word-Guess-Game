import java.lang.reflect.Array;
import java.util.ArrayList;


public class SnowmanPlayer {

	public static void main(String []args) {
		
		String [] life = {"cat", "dog"};
		startGame(life,1,5,"abcdefghijklmnopqrstuvwxyz");
	}
	
	private static ArrayList <String> firstList = new ArrayList <String>();
	private static ArrayList <String> secondList = new ArrayList <String>();
	private static ArrayList <String> thirdList = new ArrayList <String>();
	
	public static String getAuthor() {return "Myung, Joonho";}
	
	/**
	 * 
	 * @param words			Array of Strings that contains all possible words of English 
	 * 						that the secret words are randomly chosen from.
	 * @param minLength		minimum and maximum lengths of the secret words.
	 * @param maxLength		
	 * @param allowedChars	String which contains all 26 possible alphabet letters in lowercase.
	 */
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars) {

		char temp1 = ' ';
		String temp = "";
		int counter;
		
		//count length of the word.
		for (int i = 0; i < words.length; i++) {
			counter = 0;
			temp = words[i];
			if (temp.length() >= minLength && temp.length() <= maxLength) {
				
				for(int a = 0; a < temp.length(); a++) {
					temp1 = temp.charAt(a);
					
					//check if letter contains allowed alphabets
					for(int c = 0; c < allowedChars.length(); c++) {
						if (temp1 == allowedChars.charAt(c)) {
						counter++;
						}
					}
						
				}	
				
				//convert uppercase letter from word to lowercase
				if (counter == temp.length()) {
					temp.toLowerCase();
					firstList.add(temp);
				}
			}
		}	
	}
	
	
	/**
	 * 
	 * @param length 	length of the secret word.
	 * 
	 * 	Compare words with length of the secret word and filter words which has same length as parameter.
	 */
	public static void startNewWord(int length) {

		secondList.clear();

		String temp = "";
					
		for (int a = 0; a < firstList.size(); a++) {
			temp = firstList.get(a);				
				
			if (temp.length() == length) {
					secondList.add(temp);
			}
		}

	}
	
	/**
	 * 
	 * @param pattern				String that contains the current secret word with unknown letter
	 * @param preveiousGuesses		String which contains the characters that I already guessed before.
	 * 
	 * @return	return a character for guessing
	 */
	public static char guessLetter(String pattern, String preveiousGuesses) {

		thirdList.clear();
		
		int index1 = 0;


		char temp1 = ' ';
		char temp2 = ' ';
	
		
		for (int i = 0; i < secondList.size(); i++) {
			String wordSecondList = secondList.get(i);
			
			for (int a = 0; a < wordSecondList.length(); a++) {
				char letter = wordSecondList.charAt(a);
				char patternletter = pattern.charAt(a);
				
				//check if the character is unknown
				if (patternletter != '*') {
					if(patternletter != letter) {
						break;
					}
					else {
						//check if we are at the final index of the word
						if (a == wordSecondList.length()-1) {
							thirdList.add(wordSecondList);
						}
					}
				}
				else {
					//check if we are at the final index of the word
					if (a == wordSecondList.length()-1) {
						thirdList.add(wordSecondList);
					}
				}
				
			}
			
		}
		
		// find the char and index of the discovered letter in pattern
		// Put all the element that has same letter with pattern in the thirdList.
		
		//gets the frequency of the letter and save in the array
		
		int [] frequency = new int [26];
		
		for (int c = 0; c < thirdList.size(); c++) {
			String thirdListWord = thirdList.get(c);
			String guessCharacter = "";

			for (int a = 0; a < thirdListWord.length(); a++) {
				temp1 = thirdListWord.charAt(a);
				int counter = 0;

				for(int e = 0; e < preveiousGuesses.length(); e++) {
					
					char guesses = preveiousGuesses.charAt(e);
					
					if (temp1 == guesses) {
						counter++;
					}	
					
				}
				
				if(counter != 0) {
					continue;
				}
				
				frequency[temp1 - 'a']++;
				
			}
		}
		
		int indexOfMaximum = 0;
		int maximum = frequency[0];
		for (int i = 0; i < frequency.length; i++) {
			if (maximum <= frequency[i]) {
				maximum = frequency[i];
				indexOfMaximum = i;
			}
		}
		
		char guess = (char)(indexOfMaximum + 'a');
		
		return guess;
	}

}
