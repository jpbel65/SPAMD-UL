package ca.ulaval.glo4003.projet.base.ws.infrastructure.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeRepository;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.InitiativeNotFoundException;

import java.util.*;

public class InitiativeRepositoryInMemory implements InitiativeRepository {
    private final Map<String, Initiative> initiatives = new HashMap<>();

    @Override
    public void save(Initiative initiative) {
        initiatives.put(initiative.getCode(), initiative);
    }

    @Override
    public Initiative findByCode(String code) {
        if (!initiatives.containsKey(code)) throw new InitiativeNotFoundException(code);

        return initiatives.get(code);
    }

    @Override
    public List<Initiative> getAllInitiatives() {
        return new ArrayList<>(initiatives.values());
    }

    @Override
    public Price getTotalCost() {
        return initiatives.values().stream()
                          .map(Initiative::getCost)
                          .reduce(Price::add)
                          .orElse(new Price(0));
    }
}
