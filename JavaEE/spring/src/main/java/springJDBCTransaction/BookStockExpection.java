package springJDBCTransaction;

public class BookStockExpection extends RuntimeException {
    public BookStockExpection() {
        super();
    }

    public BookStockExpection(String message) {
        super(message);
    }

    public BookStockExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public BookStockExpection(Throwable cause) {
        super(cause);
    }

    protected BookStockExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
