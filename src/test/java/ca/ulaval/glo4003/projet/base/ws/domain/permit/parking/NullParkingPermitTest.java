package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidZoneInfraction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class NullParkingPermitTest {
    private NullParkingPermit parkingPermit;
    private String ANY_ID = "123";
    private LocalDate ANY_DATE = LocalDate.now();
    private String ANY_ZONE = "Gourmande";


    @Before
    public void setUp() {
        parkingPermit = new NullParkingPermit(ANY_ID, ANY_DATE);
    }

    @Test(expected = InvalidDateInfraction.class)
    public void whenVerifyDateTime_thenThrowInvalidDateInfraction() {
        parkingPermit.verifyDateTime(ANY_DATE.atStartOfDay());
    }

    @Test(expected = InvalidZoneInfraction.class)
    public void whenVerifyZone_thenThrowInvalidZoneInfraction() {
        parkingPermit.verifyZone(ANY_ZONE);
    }
}
