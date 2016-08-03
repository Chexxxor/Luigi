package networking2;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class StringListener extends Listener {
	LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
	
	public StringListener(Socket s){
		super(s);
	}
}
