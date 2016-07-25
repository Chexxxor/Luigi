package compressor;

import static compressor.Misc.*;

import java.io.UnsupportedEncodingException;

public class Linking {
	private static int LEN_LIMIT = 255;
	//private static int LEN_LIMIT = Byte.MAX_VALUE; //Max => 6714
	private static int MIN_LEN = 1;
	private static int MIN_WORD_LEN = 3;
	private static byte SYMBOL_B2 = 0;
	private static char SYMBOL_C = 0;
	
	public static String link(String in){
		return link(stringToArray(in));
	}
	public static String link(String[] in){
		String ret = "";
		int i;
		for(i = 0; i < in.length - MIN_LEN; i++){
			int pos = findBest(in, i);
			int len = repLen(in, i, pos);
			if(pos != 0 && len >= MIN_LEN && !(len == 1 && in[i].length() < MIN_WORD_LEN)){
				//System.out.println(Integer.toBinaryString(Token.toChar((byte)len, (byte)(pos-i))));
				//ret = ret.concat("" + SYMBOL_C + Token.toChar((byte)len, (byte)(pos-i)) + " ");
				ret = ret.concat("" + new String(Token.toBytes(len, pos-i)) + " ");
				i+=len-1;
			}
			else
				ret = ret.concat(in[i] + " ");
		}
		ret = ret.concat(subString(in, i, in.length-i));
		return ret.trim();
	}
	public static byte[] linkToBytes(String in) throws UnsupportedEncodingException{
		return linkToBytes(stringToArray(in));
	}
	public static byte[] linkToBytes(String[] in) throws UnsupportedEncodingException{
		byte[] ret = new byte[0];
		int i;
		for(i = 0; i < in.length - MIN_LEN; i++){
			int pos = findBest(in, i);
			int len = repLen(in, i, pos);
			if(pos != 0 && len >= MIN_LEN && !(len == 1 && in[i].length() < MIN_WORD_LEN)){
				ret = concatBytes(ret, Token.toBytes(len, pos-i-len+1));
				i+=len-1;
			}
			else
				ret = concatBytes(ret, in[i].getBytes("UTF-8"));
			ret = concatBytes(ret, " ".getBytes("UTF-8"));
		}
		ret = concatBytes(ret, subString(in, i, in.length - i).getBytes("UTF-8"));

		return ret;
	}
	
	public static String unlink(String in){
		String[] inArr = in.split("\\s+");
		return unlink(inArr);
	}
	public static String unlink(String[] in){
		String ret = "";
		for(int i = in.length-1; i >= 0; i--){
			if(in[i].charAt(0) == SYMBOL_C){
				//Reinstating substitution references
				Token token = new Token(in[i].charAt(1), in[i].charAt(2));
				String sub = subString(stringToArray(ret.trim()), token.getOffset()-1, token.getLength());
				System.out.println("Link to \"" + sub + "\"");
				//ret = ret.concat(arrayToString(arrayRev(stringToArray(sub)))); // reverse
				ret = sub + " " + ret;
			}
			else
				ret = in[i] + " " + ret;
		}
		return arrayToString(arrayRev(stringToArray(ret)));
	}
	public static String unlink(byte[] in) throws UnsupportedEncodingException{
		String ret = "";
		int i = in.length;
		int charCount = 0;
		while(i > -1){
			i--;
			if(i < 0 || (in[i] == (byte)32 && in[i-1] != SYMBOL_B2 && (i < 2 || in[i-2] != SYMBOL_B2))){
				if(in[i+1] == SYMBOL_B2){
					Token token = new Token(in[i+2], in[i+3]);
					ret = subString(stringToArray(ret.trim()), token.getOffset()-1, token.getLength()) + " " + ret;
				}
				else{
					byte[] temp = new byte[charCount];
					for(int a = 0; a < charCount; a++){
						temp[a] = in[i + a + 1];
					}
					ret = new String(temp, "UTF-8") + " " + ret;
				}
				charCount = 0;
			}
			else
				charCount++;
		}
		return ret.trim();
	}
	
	private static String findMatchS(String[] in, int current){
		int pos = findNext(in, current);
		String ret = "";
		//String ret = "No match found";
		if(pos != 0){
			int len = repLen(in, current, pos);
			ret = "Match for String \"" + subString(in, pos, len) + "\"";
		}
		return ret;
	}
	private static int findNext(String[] in, int current){
		for(int i = current + MIN_LEN; i < Math.min(current + LEN_LIMIT, in.length); i++){
			if(in[current].equals(in[i])){
				return i;
			}
		}
		return 0;
	}
	private static int findBest(String[] in, int current){
		int maxLen = 0;
		int bestPos = 0;
		for(int i = current + MIN_LEN; i < Math.min(current + LEN_LIMIT, in.length); i++){
			byte currentLen = repLen(in, current, i);
			if(in[current].equals(in[i]) && currentLen > maxLen){
				maxLen = currentLen;
				bestPos = i;
			}
		}
		return bestPos;
	}
	private static byte repLen(String[] in, int a, int b){
		int len = 0;
		while(b+len < in.length && in[a+len].equals(in[b+len])){
			len++;
		}
		return (byte)len;
	}
	
	private static class Token{
		byte length, offset;
		
		Token(int length, int offset){
			this((byte)length, (byte)offset);
		}
		Token(byte length, byte offset){
			this.length = length;
			this.offset = offset;
		}
		Token(char c){
			length = (byte)(c & 0xFF00);
			offset = (byte)(c & 0x00FF);
		}
		Token(byte[] b){
			length = b[0];
			offset = b[1];
		}
		
		int getLength(){
			return length & 0xFF;
		}
		int getOffset(){
			return offset & 0xFF;
		}
		
		byte[] toBytes(){
			byte[] ret = {SYMBOL_B2, length, offset};
			return ret;
		}
		char toChar(){
			return (char)(length << 4 + offset);
		}

		static byte[] toBytes(int len, int off){
			return toBytes((byte)len, (byte)off);
		}
		static byte[] toBytes(byte len, byte off){
			byte[] ret = {SYMBOL_B2, len, off};
			return ret;
		}
		static char toChar(byte len, byte off){
			return (char)(len << 4 + off);
		}
	}
}
