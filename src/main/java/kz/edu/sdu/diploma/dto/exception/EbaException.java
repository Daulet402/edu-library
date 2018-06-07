package kz.edu.sdu.diploma.dto.exception;

public class EbaException extends Exception {
    public EbaException() {
    }

    public EbaException(String message) {
        super(message);
    }

    public EbaException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbaException(Throwable cause) {
        super(cause);
    }

    public EbaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
