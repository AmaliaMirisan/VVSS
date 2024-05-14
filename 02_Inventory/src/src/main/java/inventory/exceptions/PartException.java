package inventory.exceptions;

public class PartException extends Throwable {
    public PartException() {
    }
    public PartException(String message) {
        super(message);
    }

    public PartException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartException(Throwable cause) {
        super(cause);
    }

    public PartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
