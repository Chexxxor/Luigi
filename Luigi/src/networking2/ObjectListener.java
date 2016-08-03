package networking2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectListener<Obj extends Serializable> extends Listener {
	ObjectInputStream in;
	LinkedBlockingQueue<Obj> objects = new LinkedBlockingQueue<>();
	ObjectSender<Packet> sender = null;

	public ObjectListener(Socket s) throws IOException {
		super(s);
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public ObjectListener(Socket s, ObjectSender<Packet> sender) throws IOException{
		this(s);
		this.sender = sender;
	}
	
	public Obj readNow() throws NoSuchElementException {
		return objects.remove();
	}
	
	public Obj readWait() throws InterruptedException{
		return objects.take();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run(){
		while(running){
			try {
				synchronized (socket) {
					objects.add((Obj)in.readObject());
				}
			} catch (ClassNotFoundException e) {
				if(sender != null)
					sender.post(new Packet(Packet.Command.BAD_CLASS));
			} catch (IOException e) {
				if(sender != null && running && !socket.isClosed())
					sender.post(new Packet(Packet.Command.REPEAT));
			}
		}
	}
}
