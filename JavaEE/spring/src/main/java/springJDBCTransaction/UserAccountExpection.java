package springJDBCTransaction;

public class UserAccountExpection extends RuntimeException {
    public UserAccountExpection() {
        super();
    }

    public UserAccountExpection(String message) {
        super(message);
    }

    public UserAccountExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAccountExpection(Throwable cause) {
        super(cause);
    }

    protected UserAccountExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
