package networking2;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	ServerSocket server;

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public boolean start(){
		try {
			server = new ServerSocket(1337);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
