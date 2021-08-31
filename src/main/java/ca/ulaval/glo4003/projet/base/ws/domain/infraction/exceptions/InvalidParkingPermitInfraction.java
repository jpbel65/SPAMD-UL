package ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;

public class InvalidParkingPermitInfraction extends InfractionException {

    @Override
    public InfractionCode getInfractionCode() {
        return InfractionCode.VIG_02;
    }
    public String getInfractionMessage() {
        return "vignette invalide";
    }
}
