package networking2;

import java.util.Scanner;

public class InputListener extends Thread{
	private Scanner input;
	private boolean scannerResponsible = true;
	private boolean running = true;
	
	public InputListener(){}
	public InputListener(Scanner input){
		this.input = input;
		scannerResponsible = false;
	}
	
	public void close(){
		running = false;
		if(scannerResponsible)
			input.close();
		else
			interrupt();
	}
	
	@Override
	public void run(){
		Scanner in;
		if(input != null)
			in = input;
		else
			in = new Scanner(System.in);
		while(running){
			String command = input.nextLine();
		}
		if(input != null)
			input.close();
	}
}
