package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	
	public Client() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 1991);
	}

	
}
