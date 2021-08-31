package ca.ulaval.glo4003.projet.base.ws.domain.exceptions;

public class InvalidFieldException extends RuntimeException{
    private final String errorCode;

    public InvalidFieldException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
