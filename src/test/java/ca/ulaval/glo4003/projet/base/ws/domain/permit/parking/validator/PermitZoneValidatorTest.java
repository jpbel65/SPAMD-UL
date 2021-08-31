package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPeriod;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RunWith(MockitoJUnitRunner.class)
public class PermitZoneValidatorTest {

    private final InfractionCode ZONE_INFRACTION_CODE = InfractionCode.ZONE_01;
    private final String ZONE_INFRACTION_OFFENSE = "mauvaise zone";
    private final Price ZONE_INFRACTION_PRICE = new Price(55);
    private final Infraction zoneInfraction = new Infraction(ZONE_INFRACTION_CODE, ZONE_INFRACTION_OFFENSE,
        ZONE_INFRACTION_PRICE);
    private final String A_CONTRAVENTION_ID = "42g3bfs";
    private final String A_PARKING_PERMIT_ID = "4B35TNN";
    private final Zone A_ZONE = Zone.ZONE_1;
    private final Zone ANOTHER_ZONE = Zone.ZONE_2;
    private final LocalDateTime ANY_LOCAL_DATETIME = LocalDateTime.of(2020, Month.NOVEMBER, 29, 19, 30, 40);
    private final DeliveryMode ANY_DELIVERY_MODE = DeliveryMode.EMAIL;
    private final String ANY_DELIVERY_ADDRESS = "jon.smith@email.com";
    private final AddressBook ANY_ADDRESS_BOOK = new AddressBook(ANY_DELIVERY_ADDRESS);
    private final ParkingPeriod ANY_PARKING_PERIOD = ParkingPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final DayOfTheWeek ANY_DAY_OF_THE_WEEK = DayOfTheWeek.MONDAY;
    private final Price ANY_PRICE = new Price(123);
    private final LocalDate ANY_LOCAL_DATE = LocalDate.now();

    private Contravention contravention;
    private ParkingPermit parkingPermit;
    private PermitValidator permitValidator;
    @Mock
    private DeliveryProcedure deliveryProcedure;

    @Before
    public void setUp() {
        contravention = new Contravention(A_CONTRAVENTION_ID, A_ZONE, ANY_LOCAL_DATETIME);
        parkingPermit = new ParkingPermit(A_PARKING_PERMIT_ID, A_ZONE, ANY_DELIVERY_MODE,
            ANY_ADDRESS_BOOK, ANY_PARKING_PERIOD, ANY_DAY_OF_THE_WEEK, ANY_PRICE, ANY_LOCAL_DATE, deliveryProcedure);
        permitValidator = new PermitZoneValidator(zoneInfraction);
    }

    @Test
    public void givenAParkingPermitWithADifferentZoneThanTheContravention_whenVerify_thenAddInfractionToContravention() {
        ParkingPermit parkingPermitWithADifferentZone = new ParkingPermit(A_CONTRAVENTION_ID, ANOTHER_ZONE,
            ANY_DELIVERY_MODE, ANY_ADDRESS_BOOK, ANY_PARKING_PERIOD, ANY_DAY_OF_THE_WEEK,
            ANY_PRICE, ANY_LOCAL_DATE, deliveryProcedure);

        permitValidator.verify(contravention, parkingPermitWithADifferentZone);

        Assert.assertTrue(contravention.getInfractions().contains(zoneInfraction));
    }

    @Test
    public void givenAParkingPermitWithTheSameZoneAsTheContravention_whenVerify_thenDoNotAddInfractionToContravention() {
        permitValidator.verify(contravention, parkingPermit);

        Assert.assertFalse(contravention.getInfractions().contains(zoneInfraction));
    }
}
