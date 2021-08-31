package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

import java.util.List;

public interface InitiativeRepository {
    void save(Initiative initiative);
    Initiative findByCode(String code);
    List<Initiative> getAllInitiatives();
    Price getTotalCost();
}
