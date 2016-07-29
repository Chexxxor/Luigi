package networking2;

import java.io.IOException;
import java.net.Socket;

public abstract class Listener extends Thread {
	protected Socket socket;
	boolean running = true;

	public Listener(Socket s) {
		socket = s;
	}

	public void close(){
		running = false;
		try {
			socket.close();
		} catch (IOException e) {}
	}
}
