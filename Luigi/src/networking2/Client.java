package networking2;

import java.io.IOException;
import java.net.Socket;

public class Client {
	Socket socket;
	ObjectListener<Packet> listener;
	ObjectSender<Packet> sender;

	public Client() {}
	
	public boolean start(int port){
		try {
			socket = new Socket("localhost", 1337);
			listener = new ObjectListener<>(socket);
			listener.start();
			sender = new ObjectSender<>(socket);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
