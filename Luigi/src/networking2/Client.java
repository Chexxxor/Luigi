package networking2;

import java.io.IOException;
import java.net.Socket;

public class Client {
	Socket socket;

	public Client() {}
	
	public boolean Connect(int port){
		try {
			socket = new Socket("localhost", 1337);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
