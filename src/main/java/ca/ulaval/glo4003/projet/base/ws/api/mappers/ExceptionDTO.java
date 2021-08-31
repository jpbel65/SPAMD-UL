package ca.ulaval.glo4003.projet.base.ws.api.mappers;

public class ExceptionDTO {

    public String code;
    public String message;

    @Override
    public String toString() {
        return  "ExceptionDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
