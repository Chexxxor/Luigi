package networking2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class Sender<Obj> {
	protected Socket socket;
	ObjectOutputStream out;

	public Sender(Socket s) throws IOException {
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
	}
	
	public abstract void send(Obj packet);

}
