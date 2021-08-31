package ca.ulaval.glo4003.projet.base.ws.domain.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public class Infraction {

    private InfractionCode code;
    private String offense;
    private Price cost;

    public Infraction() {
    }

    public Infraction(InfractionCode code, String offense, Price cost) {
        this.code = code;
        this.offense = offense;
        this.cost = cost;
    }

    public InfractionCode getCode() {
        return code;
    }

    public String getOffense() {
        return offense;
    }

    public Price getCost() {
        return cost;
    }
}
