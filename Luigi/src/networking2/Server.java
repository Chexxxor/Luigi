package networking2;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	ServerSocket server;
	ConnectionListener<Packet> listener;

	public Server() {}

	public boolean start(){
		try {
			server = new ServerSocket(1337);
			listener = new ConnectionListener<>(server, Packet.class);
			listener.start();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
