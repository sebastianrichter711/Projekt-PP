package pl.polsl.pp.backapp.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String itemTypeName, Long id) {
        super(itemTypeName + " (" + id + ") not found in database");
    }
}
