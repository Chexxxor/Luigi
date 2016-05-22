package networking2;

import java.net.Socket;

public abstract class Sender {
	protected Socket socket;

	public Sender(Socket s) {
		socket = s;
	}
	
	//public abstract void send();

}
