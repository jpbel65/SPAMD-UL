package ca.ulaval.glo4003.projet.base.ws.application.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDateTime;

public class ParkingUsageAssembler {

    public ParkingUsage create(ParkingUsageDto parkingUsageDto){
        Zone zone = Zone.fromString(parkingUsageDto.zone);
        LocalDateTime date = LocalDateTime.now();
        return new ParkingUsage(zone, date);
    }

}
