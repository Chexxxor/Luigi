package networking3;

import static networking3.ByteManager.*;

public class IpAddress {
	public byte[] address = new byte[4];
	
	public IpAddress(String adr){
		if(!adr.matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"))
			throw new IllegalArgumentException("Illegal IP representation");
		String[] parts = adr.split("\\.");
		for(int i = 0; i < 4; i++){
			address[i] = (byte) Integer.parseInt(parts[i]);
		}
	}
	public IpAddress(byte[] adr){
		if(adr.length != 4)
			throw new IllegalArgumentException("Illegal array length");
		address = adr;
	}

	public String toString(){
		String temp = "";
		for(int i = 0; i < 3; i++){
			temp = temp.concat(uByteToInt(address[0]) + ".");
		}
		return temp + uByteToInt(address[3]);
	}
}
