package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;

public class PermitDateValidator extends PermitValidator {

    private final Infraction infraction;

    public PermitDateValidator(Infraction infraction) {
        this.infraction = infraction;
    }

    @Override
    public void verify(Contravention contravention, ParkingPermit permit) {
        try {
            permit.verifyDateTime(contravention.getDate());
        }
        catch (InvalidDateInfraction e) {
            contravention.addInfraction(infraction);
        }

        verifyNext(contravention, permit);
    }
}
