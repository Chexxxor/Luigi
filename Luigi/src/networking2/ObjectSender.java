package networking2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ObjectSender<Obj extends Serializable> extends Sender{
	ObjectOutputStream out;

	public ObjectSender(Socket s) throws IOException {
		super(s);
		out = new ObjectOutputStream(s.getOutputStream());
	}
	
	public void send(Obj packet) throws IOException{
		out.writeObject(packet);
	}
	
	public void post(Obj packet){
		try {
			out.writeObject(packet);
		} catch (IOException e) {}
	}

}
