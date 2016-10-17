package networking3;

public class IpTest {
	private static byte[] hexdump = hexStringToByteArray(
					/*"a42b8c9afb8ed017c29c13680800" +*/ "4500" + 
					"002e642240008006c179c0a80115b8ad" +
					"5ac3c3720050b5d3682b4133bf685018" +
					"0104ff7b00008a8024f0484a");
	
	public static void main(String[] args){
		IpPacket ip = new IpPacket(hexdump);
		System.out.println(ip.dest());
	}

	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
