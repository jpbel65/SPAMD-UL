package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.InvalidDayOfTheWeekException;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class AccessPermitTest {

    private final static String ANY_ID = "ID";
    private final static AccessType ANY_TYPE = AccessType.BIKE;
    private final static String ANY_CODE = "CODE";
    private final static AccessPeriod ANY_PERIOD = AccessPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final static AccessPeriod ONE_DAY_PER_WEEK_PERIOD = AccessPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final static DayOfTheWeek ANY_DAY = DayOfTheWeek.FRIDAY;
    private final static DayOfTheWeek NULL_DAY = DayOfTheWeek.NULL;
    private final static LocalDate ANY_DATE = LocalDate.now();
    private final static VehicleConsumption ANY_VEHICLE_CONSUMPTION = VehicleConsumption.GREEDY;
    private final static DeliveryMode ANY_DELIVERY_MODE = DeliveryMode.EMAIL;
    private final static String ANY_ADDRESS = "123 rue des yo-yo";

    private AccessPermit accessPermit;
    private AddressBook addressBook;
    @Mock
    private DeliveryProcedure deliveryProcedure;
    @Mock
    private Vehicle vehicle;
    @Mock
    private User user;
    @Mock
    private Price price;


    @Before
    public void setUp() {
        addressBook = new AddressBook(ANY_ADDRESS);
        accessPermit = new AccessPermit(ANY_ID, ANY_TYPE, user, vehicle, ANY_PERIOD, ANY_DAY, ANY_CODE, price, ANY_DATE, ANY_DELIVERY_MODE, deliveryProcedure, addressBook);
    }

    @Test(expected = InvalidAccessDayException.class)
    public void givenAMondayAccessPermit_whenVerifyingDateTimeOnTuesday_thenThrowInvalidDateInfraction() {
        AccessPermit mondayPermit = new AccessPermit(ANY_ID, ANY_TYPE, user, vehicle, ANY_PERIOD, DayOfTheWeek.MONDAY, ANY_CODE, price, ANY_DATE, ANY_DELIVERY_MODE, deliveryProcedure, addressBook);
        LocalDateTime anyTuesday = LocalDateTime.of(2020, 9, 22, 12, 34);

        mondayPermit.verifyDateTime(anyTuesday);
    }

    @Test(expected = InvalidAccessTypeException.class)
    public void givenAnAutomobileAccessPermit_whenVerifyIfBikeType_thenThrowInvalidAccessTypeInfraction() {
        AccessPermit automobilePermit = new AccessPermit(ANY_ID, AccessType.AUTOMOBILE, user, vehicle, ANY_PERIOD, ANY_DAY, ANY_CODE, price, ANY_DATE, ANY_DELIVERY_MODE, deliveryProcedure, addressBook);

        automobilePermit.verifyType(AccessType.BIKE);
    }

    @Test
    public void whenVerifyingVehicleConsumption_thenVerifyVehicleDoVerityVehicleConsumption() {
        accessPermit.verifyVehicleConsumption(ANY_VEHICLE_CONSUMPTION);

        BDDMockito.verify(vehicle).verifyVehicleConsumption(ANY_VEHICLE_CONSUMPTION);
    }

    @Test
    public void whenSendAccessCode_thenDeliveryProcedureSendAccessCode() {
        accessPermit.sendAccessCode();

        BDDMockito.verify(deliveryProcedure).sendPermitId(addressBook, ANY_CODE);
    }

    @Test(expected = InvalidDayOfTheWeekException.class)
    public void givenBadDayOfTheWeekForOneDayPerWeekPeriod_whenValidPermit_thenThrowInvalidDayOfTheWeekException() {
        accessPermit = new AccessPermit(ANY_ID, ANY_TYPE, user, vehicle, ONE_DAY_PER_WEEK_PERIOD, NULL_DAY, ANY_CODE, price, ANY_DATE, ANY_DELIVERY_MODE, deliveryProcedure, addressBook);

        accessPermit.validPermit();
    }
}
