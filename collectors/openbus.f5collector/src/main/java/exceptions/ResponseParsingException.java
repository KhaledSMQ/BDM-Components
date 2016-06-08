package exceptions;

public class ResponseParsingException extends Exception {

    public ResponseParsingException() {
    }

    public ResponseParsingException(String message) {
        super(message);
    }

    public ResponseParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseParsingException(Throwable cause) {
        super(cause);
    }

    public ResponseParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
