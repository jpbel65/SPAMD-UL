package ca.ulaval.glo4003.projet.base.ws.application.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;

public class InfractionAssembler {

    public InfractionDto create(Infraction infraction) {
        InfractionDto infractionDto = new InfractionDto();
        infractionDto.code = infraction.getCode().toString();
        infractionDto.offense = infraction.getOffense();
        infractionDto.cost = infraction.getCost().toString();
        return infractionDto;
    }
}
