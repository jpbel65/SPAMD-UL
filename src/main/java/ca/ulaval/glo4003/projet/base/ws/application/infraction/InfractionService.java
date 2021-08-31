package ca.ulaval.glo4003.projet.base.ws.application.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InfractionException;

public class InfractionService {

    private final InfractionStorage infractionStorage;

    public InfractionService(InfractionStorage infractionStorage) {
        this.infractionStorage = infractionStorage;
    }

    public Infraction createInfraction(InfractionException infractionException) {
        return infractionStorage.findByCode(infractionException.getInfractionCode());
    }

    public Infraction createInvalidParkingPermitInfraction() {
        return infractionStorage.findByCode(InfractionCode.VIG_02);
    }
}
