package ca.ulaval.glo4003.projet.base.ws.application.infraction;

public class InfractionDto {
    public String code;
    public String offense;
    public String cost;

    @Override
    public String toString() {
        return "InfractionDto{" +
                "code='" + code + '\'' +
                ", offense='" + offense + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
