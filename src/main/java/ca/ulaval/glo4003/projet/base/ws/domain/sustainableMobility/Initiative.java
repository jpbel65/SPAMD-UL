package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public class Initiative {
    private final String code;
    private final String name;
    private Price cost;

    public Initiative(String code, String name, Price cost) {
        this.code = code;
        this.name = name;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Price getCost() {
        return cost;
    }

    public void addFund(float funds) {
        cost = cost.add(funds);
    }
}
