package networking3;

import static networking3.ByteManager.*;

public class IpPacket {
	byte[] datagram;
	
	public IpPacket(byte[] data){
		datagram = data;
	}

	public byte[] packet(){
		return datagram;
	}
	
	public int version(){ //and header length
		return uByteToInt(field(0, 1)[0]);
	}
	public int TOS(){
		return uByteToInt(field(1, 1));
	}
	public int length(){
		return uByteToInt(field(2, 2));
	}
	public int identifier(){
		return uByteToInt(field(4, 2));
	}
	public int flags(){ //part of offset
		return uByteToInt(field(6, 1));
	}
	public int offset(){
		return uByteToInt(field(7, 1));
	}
	public int TTL(){
		return uByteToInt(field(8, 1));
	}
	public int upperLayer(){
		return uByteToInt(field(9, 1));
	}
	public int checksum(){
		return uByteToInt(field(10, 2));
	}
	public IpAddress source(){
		return new IpAddress(field(12, 4));
	}
	public IpAddress dest(){
		return new IpAddress(field(16, 4));
	}
	public byte[] data(){
		return field(20, length()-20);
	}
	
	public byte[] field(int start, int length){
		byte[] temp = new byte[length];
		System.arraycopy(datagram, start, temp, 0, length);
		return temp;
	}
}
