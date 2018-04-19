package commands;

import modele.Buffer;

public abstract class CommandBuffer implements Command{

	protected Buffer buffer;
	
	public CommandBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	
}
