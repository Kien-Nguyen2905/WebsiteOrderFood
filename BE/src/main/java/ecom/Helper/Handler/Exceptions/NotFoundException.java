package ecom.Helper.Handler.Exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
