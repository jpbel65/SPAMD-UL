package ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDateTime;

public class ParkingUsage {
    private Zone zone;
    private LocalDateTime date;

    public ParkingUsage(Zone zone, LocalDateTime date) {
        this.zone = zone;
        this.date = date;
    }

    public Zone getZone() {
        return zone;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
