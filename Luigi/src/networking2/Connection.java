package networking2;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class Connection<O extends Serializable>{
	Socket socket;
	boolean running = true;
	ObjectListener<O> listener;
	ObjectSender<O> sender;
	
	@SuppressWarnings("unchecked")
	public Connection(Socket s, int id, Class<O> c) throws IOException{
		socket = s;
		sender = new ObjectSender<>(s);
		if(Packet.class == c)
			listener = new ObjectListener<>(s, (ObjectSender<Packet>)sender);
		else
			listener = new ObjectListener<>(s);
	}
	
	public void send(O packet) throws IOException{
		sender.send(packet);
	}
	
	public void post(O packet){
		sender.post(packet);
	}
	
	public void listen(){
		listener.start();
	}
	
	public void close(){
		listener.close();
	}
}
