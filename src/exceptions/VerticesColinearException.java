package exceptions;

public class VerticesColinearException extends RuntimeException {

	public VerticesColinearException() {
		super("Vertices are colinear, therefore desired identity can not be formed");
	}
}
