package ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InitiativeAssembler {

    public Initiative create(InitiativeDto initiativeDto) {
        String code = UUID.randomUUID().toString();

        return new Initiative(
            code,
            initiativeDto.name,
            new Price(initiativeDto.cost)
        );
    }

    public InitiativeDto create(Initiative initiative) {
        InitiativeDto initiativeDto = new InitiativeDto();
        initiativeDto.code = initiative.getCode();
        initiativeDto.name = initiative.getName();
        initiativeDto.cost = initiative.getCost().getValue();

        return initiativeDto;
    }

    public List<InitiativeDto> create(List<Initiative> initiatives) {
        return initiatives.stream().map(this::create).collect(Collectors.toList());
    }
}
