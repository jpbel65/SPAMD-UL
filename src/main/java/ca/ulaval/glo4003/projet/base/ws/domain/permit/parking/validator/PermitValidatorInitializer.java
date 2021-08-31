package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;

public class PermitValidatorInitializer {

    private final InfractionStorage infractionStorage;
    private Infraction zoneInfraction;
    private Infraction dateInfraction;

    public PermitValidatorInitializer(InfractionStorage infractionStorage) {
        this.infractionStorage = infractionStorage;
    }

    public PermitValidator createPermitValidatorChain() {
        fetchInfractions();
        PermitValidator zoneValidator = new PermitZoneValidator(zoneInfraction);
        PermitValidator dateValidator =  new PermitDateValidator(dateInfraction);

        zoneValidator.setNext(dateValidator);

        return zoneValidator;
    }

    private void fetchInfractions() {
        zoneInfraction = infractionStorage.findByCode(InfractionCode.ZONE_01);
        dateInfraction = infractionStorage.findByCode(InfractionCode.VIG_01);
    }
}
