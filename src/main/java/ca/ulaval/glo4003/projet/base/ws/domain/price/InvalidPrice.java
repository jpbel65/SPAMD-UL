package ca.ulaval.glo4003.projet.base.ws.domain.price;

public class InvalidPrice extends RuntimeException{
    private final static String ERROR_MESSAGE = "The Value of the price is Invalid";

    public InvalidPrice() {
        super(ERROR_MESSAGE);
    }
}
