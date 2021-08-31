package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidZoneInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;

public class PermitZoneValidator extends PermitValidator {

    private final Infraction infraction;

    public PermitZoneValidator(Infraction infraction) {
        this.infraction = infraction;
    }

    @Override
    public void verify(Contravention contravention, ParkingPermit permit) {
        try {
            permit.verifyZone(contravention.getZone());
        }
        catch (InvalidZoneInfraction e) {
            contravention.addInfraction(infraction);
        }

        verifyNext(contravention, permit);
    }
}
