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
public class PermitDateValidatorTest {

    private final InfractionCode DATE_INFRACTION_CODE = InfractionCode.VIG_01;
    private final String DATE_INFRACTION_OFFENSE = "vignette pas admissible pour la journée";
    private final Price DATE_INFRACTION_PRICE = new Price(22);
    private final Infraction dateInfraction = new Infraction(DATE_INFRACTION_CODE, DATE_INFRACTION_OFFENSE,
        DATE_INFRACTION_PRICE);
    private final String A_CONTRAVENTION_ID = "42g3bfs";
    private final String A_PARKING_PERMIT_ID = "4B35TNN";
    private final String A_PARKING_CODE = "4D9uinobjjob";
    private final Zone ANY_ZONE = Zone.ZONE_1;
    private final LocalDateTime A_CONTRAVENTION_LOCAL_DATETIME = LocalDateTime
        .of(2020, Month.NOVEMBER, 29, 19, 30, 40);
    private final DeliveryMode ANY_DELIVERY_MODE = DeliveryMode.EMAIL;
    private final String ANY_DELIVERY_ADDRESS = "jon.smith@email.com";
    private final AddressBook ANY_ADDRESS_BOOK = new AddressBook(ANY_DELIVERY_ADDRESS);
    private final ParkingPeriod ANY_PARKING_PERIOD = ParkingPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final DayOfTheWeek SAME_DAY_OF_THE_WEEK_AS_CONTRAVENTION =
        DayOfTheWeek.valueOf(A_CONTRAVENTION_LOCAL_DATETIME.getDayOfWeek().toString());
    private final DayOfTheWeek ANOTHER_DAY_OF_THE_WEEK = DayOfTheWeek.FRIDAY;
    private final Price ANY_PRICE = new Price(123);
    private final LocalDate ANY_LOCAL_DATE = LocalDate.now();

    private Contravention contravention;
    private PermitValidator permitValidator;

    @Mock
    public DeliveryProcedure deliveryProcedure;

    @Before
    public void setUp() {
        contravention = new Contravention(A_CONTRAVENTION_ID, ANY_ZONE, A_CONTRAVENTION_LOCAL_DATETIME);
        permitValidator = new PermitDateValidator(dateInfraction);
    }

    @Test
    public void givenAParkingPermitWithADifferentDayOfTheWeekThanTheContravention_whenVerify_thenAddInfractionToContravention() {
        ParkingPermit parkingPermitWithADifferentDate = new ParkingPermit(A_PARKING_PERMIT_ID, ANY_ZONE,
            ANY_DELIVERY_MODE, ANY_ADDRESS_BOOK, ANY_PARKING_PERIOD, ANOTHER_DAY_OF_THE_WEEK,
            ANY_PRICE, ANY_LOCAL_DATE, deliveryProcedure);

        permitValidator.verify(contravention, parkingPermitWithADifferentDate);

        Assert.assertTrue(contravention.getInfractions().contains(dateInfraction));
    }

    @Test
    public void givenAParkingPermitWithTheSameDayOfTheWeekAsTheContravention_whenVerify_thenDoNotAddInfractionToContravention() {
        ParkingPermit parkingPermitWithSameDateAsContravention = new ParkingPermit(A_PARKING_PERMIT_ID, ANY_ZONE,
            ANY_DELIVERY_MODE, ANY_ADDRESS_BOOK, ANY_PARKING_PERIOD,
            SAME_DAY_OF_THE_WEEK_AS_CONTRAVENTION, ANY_PRICE, ANY_LOCAL_DATE, deliveryProcedure);

        permitValidator.verify(contravention, parkingPermitWithSameDateAsContravention);

        Assert.assertFalse(contravention.getInfractions().contains(dateInfraction));
    }
}