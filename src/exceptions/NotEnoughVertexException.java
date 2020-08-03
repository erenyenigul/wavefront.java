package exceptions;

public class NotEnoughVertexException extends RuntimeException {

	public NotEnoughVertexException() {
		super("Not enough vertex is passed as argument to form the desired entity.");
	}
}
