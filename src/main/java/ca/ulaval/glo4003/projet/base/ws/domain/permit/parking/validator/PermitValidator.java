package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;

public abstract class PermitValidator {

    private PermitValidator nextValidator;

    public void setNext(PermitValidator next) {
        this.nextValidator = next;
    }

    public abstract void verify(Contravention contravention, ParkingPermit permit);

    protected void verifyNext(Contravention contravention, ParkingPermit permit) {
        if (nextValidator != null) {
            nextValidator.verify(contravention, permit);
        }
    }
}
