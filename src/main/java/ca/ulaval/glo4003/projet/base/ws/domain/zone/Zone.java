package ca.ulaval.glo4003.projet.base.ws.domain.zone;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ZoneNotFoundException;

public enum Zone {
    ZONE_1("Zone1"),
    ZONE_2("Zone2"),
    ZONE_3("Zone3"),
    ZONE_R("ZoneR"),
    ZONE_VELO("ZoneVelo");

    private final String zoneName;

    Zone(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return this.zoneName;
    }

    public static Zone fromString(String zoneAsString) throws ZoneNotFoundException {
        for (Zone zone : Zone.values()) {
            if(zoneAsString.toUpperCase().equals(zoneAsString.toUpperCase())) {
                return zone;
            }
        }

        throw new ZoneNotFoundException(zoneAsString);
    }
}
