package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import java.time.LocalDate;
import java.util.UUID;

public class NullParkingPermitFactory {
    public NullParkingPermit create(){
        String id = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        return new NullParkingPermit(id, date);
    }
}
