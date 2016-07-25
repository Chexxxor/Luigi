package compressor;

import java.util.ArrayList;
import java.util.Collections;

public class WordCounter {
	String in;
	ArrayList<WordCount> counts = new ArrayList<>();

	public WordCounter(String in){
		this.in = in;
		for(String s : in.split("\\s+")){
			int i = indexOf(s);
			if(i > -1)
				counts.get(i).add();
			else
				counts.add(new WordCount(s));
		}
		Collections.sort(counts);
	}
	
	private boolean contains(String s){
		for(WordCount c : counts){
			if(c.word.equals(s))
				return true;
		}
		return false;
	}
	
	private int indexOf(String s){
		for(int i = 0; i < counts.size(); i++){
			if(counts.get(i).word.equals(s))
				return i;
		}
		return -1;
	}

	public int size(){
		return counts.size();
	}
	public int wordsAboveThreshold(int length){
		int count = 0;
		while(counts.get(count).getWord().length() >= length){
			count++;
		}
		return count;
	}
	public String getWord(int i){
		return counts.get(i).getWord();
	}
	public int getCount(int i){
		return counts.get(i).count;
	}

 	private class WordCount implements Comparable<WordCount>{
		String word;
		int count;

		WordCount(String word){
			this.word = word;
			count = 1;
		}

		void add(){
			count++;
		}

		public int compareTo(WordCount c){
			//return Integer.compare(count, c.count);
			return c.count - count;
		}
		
		public String getWord(){
			return word;
		}

		public int getCount(){
			return count;
		}

		public String toString(){
			String tabs = "\t";
			int tabCount = 2 - word.length()/8;
			for(int i = 0; i < tabCount; i++){
				tabs = tabs.concat("\t");
			}
			return word + tabs + count;
		}
	}
	
	public String toString(){
		String ret = "";
		for(WordCount c : counts){
			ret = ret.concat(c.toString() + "\n");
		}
		return ret;
	}
}
