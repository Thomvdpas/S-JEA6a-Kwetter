package main.exception;

/**
 * @author Thom van de Pas on 23-4-2018
 */
public class SystemException extends RuntimeException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }
}
