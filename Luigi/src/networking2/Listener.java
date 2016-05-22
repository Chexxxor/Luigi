package networking2;

//import java.io.InputStream;
import java.net.Socket;
//import java.util.ArrayList;

public abstract class Listener extends Thread {
	protected Socket socket;
	boolean running = true;

	public Listener(Socket s) {
		socket = s;
		//in = s.getInputStream();
	}

}
