package pl.polsl.pp.backapp.exception;

public class ItemExistsInDatabaseException extends RuntimeException {

    public ItemExistsInDatabaseException(String message) {
        super(message);
    }
}