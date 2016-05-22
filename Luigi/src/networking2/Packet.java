package networking2;

import java.io.Serializable;

public class Packet implements Serializable{
	private static final long serialVersionUID = 1L;
	enum Command{EXIT, START, ERROR, BAD_CLASS};

	String message = null;
	Command command;
	
	public Packet(Command c){
		command = c;
	}

	public Packet(Command c, String m) {
		command = c;
		message = m;
	}

}
