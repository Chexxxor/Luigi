package networking2;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ConnectionListener<Obj extends Serializable> extends Thread {
	int count = 0;
	boolean running = true;
	ServerSocket server;
	ArrayList<Connection<Obj>> clients = new ArrayList<>();
	Class<Obj> type;

	public ConnectionListener(ServerSocket s, Class<Obj> c) {
		server = s;
		type = c;
	}
	
	public ArrayList<Connection<Obj>> getClients(){
		return clients;
	}
	
	public void close(){
		running = false;
		for(Connection<Obj> c : clients){
			c.close();
		}
		try {
			server.close();
		} catch (IOException e) {}
	}
	
	@Override
	public void run(){
		while(running){
			try {
				Connection<Obj> c = new Connection<>(server.accept(), count, type);
				c.listen();
				clients.add(c);
				count++;
				System.out.println("Connection established");
			} catch (IOException e) {
				if(!server.isClosed())
					System.out.println("Connection failed");
			}
		}
	}
}
