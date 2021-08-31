package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidZoneInfraction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NullParkingPermit extends ParkingPermit {

    public NullParkingPermit(String id, LocalDate date) {
        super(id, null, null, null, null, null, null, date, null);
    }

    public void verifyDateTime(LocalDateTime dateTime) {
        throw new InvalidDateInfraction();
    }

    public void verifyZone(String zone) {
            throw new InvalidZoneInfraction();
    }

    public void sendParkingPermitCode(){

    }
}
