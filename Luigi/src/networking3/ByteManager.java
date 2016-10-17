package networking3;

import java.nio.ByteBuffer;

public class ByteManager {
	public static int uByteToInt(byte b){
		return (int)b & 0xFF;
	}
	
	public static int uByteToInt(byte[] b){
		int res = 0;
		for(int i = 0; i < b.length; i++){
			res += ((int)b[i] & 0xFF) * Math.pow(8, b.length-i-1); 
		}
		return res;
	}
	
	public static byte[] intToByteArr(int i){
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(i);
		return b.array();

		/*byte[] result = b.array();
		byte[] temp = new byte[4];
		for(int c = 0; c < 4; c++){
			temp[3-c] = i&0xFF; 
		}*/
	}
}
