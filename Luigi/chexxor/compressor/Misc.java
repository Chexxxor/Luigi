package compressor;

public class Misc {
	static String subString(String[] in, int pos, int len){
		String ret = "";
		for(int i = pos; i < pos + len; i++){
			ret = ret.concat(in[i] + " ");
		}
		return ret.trim();
	}
	static String[] stringToArray(String in){
		return in.split("\\s+");
	}
	static String arrayToString(String[] in){
		String ret = "";
		for(String s : in){
			ret = ret.concat(s + " ");
		}
		return ret;
	}
	static <T> T[] arrayRev(T[] arr){
		T[] ret = arr.clone();
		for(int i = 0; i < arr.length; i++){
			ret[arr.length-i-1] = arr[i];
		}
		return ret;
	}
	static byte[] concatBytes(byte[] in, byte[] app){
		byte[] ret = new byte[in.length + app.length];
		System.arraycopy(in, 0, ret, 0, in.length);
		System.arraycopy(app, 0, ret, in.length, app.length);
		return ret;
	}
}
