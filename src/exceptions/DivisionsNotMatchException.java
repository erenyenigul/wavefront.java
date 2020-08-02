package exceptions;

public class DivisionsNotMatchException extends RuntimeException {

	public DivisionsNotMatchException() {
		super("Division of given two curves do not match!");
	}

}
