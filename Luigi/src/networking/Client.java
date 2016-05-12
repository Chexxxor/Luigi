package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private static final int PORT = 1991;
	private static Socket socket;
	private static DataOutputStream out;
	private static Listener listener;
	private static boolean running = true;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		socket = new Socket("localhost", PORT);
		listener = new Listener();
		listener.start();
		out = new DataOutputStream(socket.getOutputStream());
		Scanner input = new Scanner(System.in);
		while(running){
			String command = input.next();
			switch(command){
			case "close":
				//out.writeUTF(command);
				running = false;
				//break;
			default:
				out.writeUTF(command);
			}
		}
		input.close();
	}

	private static class Listener extends Thread {
		DataInputStream in;
		
		Listener() throws IOException{
			in = new DataInputStream(socket.getInputStream());
		}
		
		@Override
		public void run(){
			while(running){
				try {
					System.out.println(in.readUTF());
				} catch (IOException e) {}
			}
		}
	}
}
