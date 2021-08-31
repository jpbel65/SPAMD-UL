package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidZoneInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.ContraventionAlreadyExistsException;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.ContraventionNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPermitTest {
    private ParkingPermit parkingPermit;
    private AddressBook addressBook;
    private final String ANY_ID = "123453";
    private final Zone ANY_ZONE = Zone.ZONE_1;
    private final InfractionCode ANY_CODE = InfractionCode.ZONE_01;
    private final String ANY_PARKING_PERMIT = "123453";
    private final String ANY_ADDRESS = "123453";
    private final LocalDate ANY_DATE = LocalDate.now();
    private final DeliveryMode ANY_DELIVERY_MODE = DeliveryMode.SNAIL_MAIL;
    private final DayOfTheWeek ANY_DAY_OF_THE_WEEK = DayOfTheWeek.MONDAY;
    private final ParkingPeriod ANY_PERIOD = ParkingPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final Price ANY_PRICE = new Price(159);
    @Mock
    private DeliveryProcedure deliveryProcedure;

    private static final LocalDateTime ANY_DATE_TIME = LocalDateTime.now();
    private static final SchoolYearDate SCHOOL_YEAR_DATE = new SchoolYearDate(LocalDate.now());

    private final Contravention contravention = new Contravention(ANY_ID, ANY_ZONE, ANY_DATE_TIME);

    @Before
    public void setUp() {
        addressBook = new AddressBook(ANY_ADDRESS);
        parkingPermit = new ParkingPermit(ANY_ID, ANY_ZONE, ANY_DELIVERY_MODE, addressBook, ANY_PERIOD,
                ANY_DAY_OF_THE_WEEK, ANY_PRICE, ANY_DATE, deliveryProcedure);
    }

    @Test(expected = InvalidDateInfraction.class)
    public void givenInvalidDate_whenVerifyDateTime_thenShouldThrowInvalidDateInfraction() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 1, 0, 0);

        parkingPermit.verifyDateTime(localDateTime);
    }

    @Test(expected = InvalidZoneInfraction.class)
    public void givenInvalidZone_whenVerifyZone_thenShouldThrowInvalidZoneInfraction() {
        Zone invalidZone = Zone.ZONE_2;

        parkingPermit.verifyZone(invalidZone);
    }

    @Test
    public void whenSendParkingPermitCode_thenVerifyDeliveryProcedureSendParkingPermitCode() {
        parkingPermit.sendParkingPermitCode();

        verify(deliveryProcedure).sendPermitId(addressBook, ANY_PARKING_PERMIT);
    }

    @Test(expected = ContraventionAlreadyExistsException.class)
    public void givenContraventionAlreadyAdded_whenAddContravention_thenThrowContraventionAlreadyExistsException() {
        parkingPermit.addContravention(contravention);

        parkingPermit.addContravention(contravention);
    }

    @Test
    public void givenContraventionAdded_whenFindContraventionById_thenContraventionIsFound() {
        parkingPermit.addContravention(contravention);

        Contravention contraventionById = parkingPermit.findContraventionById(contravention.getId());

        assertEquals(contravention, contraventionById);
    }

    @Test(expected = ContraventionNotFoundException.class)
    public void givenNoContraventionAdded_whenFindContraventionById_thenThrowContraventionNotFoundException() {
        parkingPermit.findContraventionById(ANY_ID);
    }

    @Test
    public void givenContraventionAdded_whenPayContravention_thenContraventionIsPaid() {
        parkingPermit.addContravention(contravention);

        parkingPermit.payContravention(contravention.getId());

        assertTrue(contravention.isPaid());
    }

    private void givenMultiplePaidContravention(int contraventionsValue) {
        for (int i = 0; i < contraventionsValue; i++) {
            Contravention c = new Contravention("c" + i, ANY_ZONE, ANY_DATE_TIME);
            c.addInfraction(new Infraction(ANY_CODE, "ANY OFFENSE", new Price(1)));
            parkingPermit.addContravention(c);
            parkingPermit.payContravention(c.getId());
        }
    }

    @Test
    public void givenMultiplePaidContraventions_whenGetTotalPriceOfContraventions_thenTotalPriceIsSumOfContraventionsPrice() {
        int totalValue = 10;
        givenMultiplePaidContravention(totalValue);

        Price price = parkingPermit.getTotalPriceOfContraventionsForSchoolYear(SCHOOL_YEAR_DATE);

        Price expectedPrice = new Price(totalValue);
        Assert.assertEquals(expectedPrice, price);
    }
}
