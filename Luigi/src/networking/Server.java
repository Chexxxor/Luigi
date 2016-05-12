package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import static networking.Server.Status.*;

public class Server {
	public static enum Status{
		ONLINE, OFFLINE, STARTING, STOPPING, ERROR;
		
		static Status status = OFFLINE;
	};
	private static final int PORT = 1993;
	private static final int CLIENT_LIMIT = 10;
	private static boolean serverRunning = true;
	private static ServerSocket server;
	private static ArrayList<Client> clients = new ArrayList<>();
	private static Thread input = new Input();
	private static Thread listener = new Listener();

	private static class Input extends Thread {
		@Override
		public void run(){
			Scanner input = new Scanner(System.in);
			while(serverRunning){
				String command = input.nextLine();
				switch(command){
				case "exit":
					for(Client client : clients){
						try {
							client.close();
							client.join();
						} catch (Exception e){}
					}
					serverRunning = false;
					status = STOPPING;
					System.out.println("Server stopping...");
					break;
					default:
						System.out.println("Unkown command: \"" + command + "\"");
				}
			}
			input.close();
		}
	}
	
	private static class Listener extends Thread {
		@Override
		public void run(){
			System.out.println("Server starting...");
			status = STARTING;
			try {
				server = new ServerSocket(PORT);
				System.out.println("Server online...");
				status = ONLINE;
				while(serverRunning){
					synchronized (clients) {
						if(CLIENT_LIMIT > clients.size()){
							try {
								System.out.println("Connection attempted...");
								/*Client c = new Client(server.accept());
								clients.add(c);
								c.start();*/
								new Client(server.accept());
							} catch (Exception e){}
						}
						try {
							wait();
						} catch (Exception e){}
					}
				}
			} catch (Exception e1){
				status = ERROR;
			}
		}
	}
	
	private static class Client extends Thread{
		private int id;
		private Socket socket = null;
		private boolean running = true;

		Client(Socket s){
			socket = s;
			synchronized (clients) {
				clients.add(this);
				id = clients.indexOf(this);
			}
			start();
		}

		@Override
		public void run(){
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				System.out.println("Client " + id + " connected");
				while(running){
					String command = in.readUTF();
					System.out.println("Client " + id + "> " + command);
					switch(command){
					case "close":
						out.writeUTF("closed");
						running = false;
						close();
						break;
					default:
						out.writeUTF("Recieved");
					}
				}
			}catch(Exception e){
			}finally{
				synchronized (clients) {
					clients.remove(this);
					listener.notify();
				}
			}
		}
		
		public void close() throws IOException{
			socket.close();
		}
	}
		
	public static void main(String[] args) throws IOException{
		input.start();
		listener.start();
	}
}
