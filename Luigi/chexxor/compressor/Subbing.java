package compressor;

import java.util.ArrayList;

public class Subbing {
	public static String sub(String in){
		String[] words = createWordList(in);
		String temp = in;
		StringBuilder builder = new StringBuilder(words.length + " ");
		for(int i = 0; i < words.length; i++){
			//temp = temp.replace(words[i], "" + SYMBOLS[i]);
			temp = temp.replace(words[i], "" + (char)(i+128));
			builder.append(words[i] + " ");
		}
		builder.append(temp);
		return builder.toString();
	}
	public static String deSub(String in){
		String[] temp = in.split(" ", 2);
		int count = Integer.parseInt(temp[0]);
		for(int i = 0; i < count; i++){
			temp = temp[1].split(" ", 2);
			temp[1] = temp[1].replaceAll("" + (char)(i+128), temp[0]);
		}
		return temp[1];
	}
	
	private static String[] createWordList(String in){
		WordCounter counter = new WordCounter(in);
		ArrayList<String> words = new ArrayList<>();
		//for(int i = 0; i < counter.size() && words.size() < SYMBOLS.length; i++){
		for(int i = 0; i < counter.size() && words.size() < 128; i++){
			if(goodWord(counter.getWord(i), counter.getCount(i)))
				words.add(counter.getWord(i));
		}
		String[] ret = new String[words.size()];
		words.toArray(ret);
		return ret;
	}
	private static boolean goodWord(String word, int count){
		return (word.length() - 1) * count > word.length() + 1;
	}
}
