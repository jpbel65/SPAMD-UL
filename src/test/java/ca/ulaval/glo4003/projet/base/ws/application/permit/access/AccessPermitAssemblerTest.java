package ca.ulaval.glo4003.projet.base.ws.application.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedureFactory;
import ca.ulaval.glo4003.projet.base.ws.application.user.UserAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.user.UserDto;
import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleDto;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.InvalidDayOfTheWeekException;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPeriod;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessType;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AccessPermitAssemblerTest {

    private static final AccessType TYPE = AccessType.AUTOMOBILE;
    private final static String ID = "ID";
    private final static String DEFAULT_DAY = "Friday";
    private final static String ERROR = "ERROR";
    private final static String ACCESS_CODE = "1234";
    private final static DayOfTheWeek ENUM_DEFAULT_DAY = DayOfTheWeek.FRIDAY;
    private final static AccessPeriod PERIOD = AccessPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private final static float PRICE_VALUE = 12;
    private final static LocalDate ANY_DATE = LocalDate.now();
    private final static DeliveryMode ANY_DELIVERY_MODE = DeliveryMode.EMAIL;

    private AccessPermitAssembler accessPermitAssembler;
    @Mock
    private DeliveryProcedure deliveryProcedure;
    @Mock
    private AddressBook addressBook;
    @Mock
    private UserAssembler userAssembler;
    @Mock
    private VehicleAssembler vehicleAssembler;
    @Mock
    private UserDto userDto;
    @Mock
    private User user;
    @Mock
    private VehicleDto vehicleDto;
    @Mock
    private Vehicle vehicle;
    @Mock
    private DeliveryProcedureFactory deliveryProcedureFactory;
    @Mock
    private IdGenerator idGenerator;
    private AccessPermitDto accessPermitDto;
    private AccessPermit accessPermit;
    private Price accessPermitPrice;

    @Before
    public void setUp() {
        accessPermitAssembler = new AccessPermitAssembler(userAssembler, vehicleAssembler, deliveryProcedureFactory, idGenerator);
        accessPermitDto = new AccessPermitDto();
        accessPermitDto.accessType = TYPE.toString();
        accessPermitDto.user = userDto;
        accessPermitDto.vehicle = vehicleDto;
        accessPermitDto.dayOfTheWeek = DEFAULT_DAY;
        accessPermitDto.accessCode = ACCESS_CODE;
        accessPermitDto.period = PERIOD.toString();
        accessPermitDto.deliveryMode = ANY_DELIVERY_MODE.toString();
        accessPermitPrice = new Price(PRICE_VALUE);
        accessPermit = new AccessPermit(ID, TYPE, user, vehicle, PERIOD, ENUM_DEFAULT_DAY, ACCESS_CODE, accessPermitPrice,
                ANY_DATE, ANY_DELIVERY_MODE, deliveryProcedure, addressBook);

        BDDMockito.given(userAssembler.create(any(UserDto.class))).willReturn(user);
        BDDMockito.given(vehicleAssembler.create(any(VehicleDto.class))).willReturn(vehicle);
        BDDMockito.given(userAssembler.create(any(User.class))).willReturn(userDto);
        BDDMockito.given(vehicleAssembler.create(any(Vehicle.class))).willReturn(vehicleDto);
        BDDMockito.given(deliveryProcedureFactory.create(ANY_DELIVERY_MODE)).willReturn(deliveryProcedure);
    }

    @Test
    public void givenValidAccessPermitDto_whenAssemblingPermit_thenShouldReturnPermit() {
        AccessPermit actualPermit = accessPermitAssembler.create(accessPermitDto, accessPermitPrice);

        assertEquals(accessPermitDto.period,actualPermit.getPeriod().toString());
        assertEquals(accessPermitDto.dayOfTheWeek, actualPermit.getDayOfTheWeek().toString());
        assertEquals(accessPermitPrice, actualPermit.getPrice());
    }

    @Test(expected = InvalidDayOfTheWeekException.class)
    public void givenAccessPermitDtoWithIncorrectDay_whenAssemblingPermit_thenShouldThrowInvalidDayOfTheWeekException() {
        accessPermitDto.dayOfTheWeek = ERROR;

        accessPermitAssembler.create(accessPermitDto, accessPermitPrice);
    }

    @Test
    public void givenUserAssembler_whenAssemblingUser_thenShouldCallUserAssembler() {
        accessPermitAssembler.create(accessPermitDto, accessPermitPrice);

        Mockito.verify(userAssembler).create(userDto);
    }

    @Test
    public void givenVehicleAssembler_whenAssemblingVehicle_thenShouldCallVehicleAssembler() {
        accessPermitAssembler.create(accessPermitDto, accessPermitPrice);

        Mockito.verify(vehicleAssembler).create(vehicleDto);
    }

    @Test
    public void whenAssemblingPermitDto_thenShouldReturnPermitDto() {
        AccessPermitDto actualPermitDto = accessPermitAssembler.create(accessPermit);

        assertEquals(accessPermit.getId(), actualPermitDto.id);
        assertEquals(accessPermit.getPeriod().toString(), actualPermitDto.period);
        assertEquals(accessPermit.getDayOfTheWeek().toString(), actualPermitDto.dayOfTheWeek);
        assertEquals(accessPermit.getAccessCode(), actualPermitDto.accessCode);
    }

    @Test
    public void givenAccessPermit_whenAssemblingAccessPermitDto_thenShouldCreateUser() {
        accessPermitAssembler.create(accessPermit);

        BDDMockito.verify(userAssembler).create(any(User.class));
    }

    @Test
    public void givenAccessPermit_whenAssemblingAccessPermitDto_thenShouldCreateVehicle() {
        accessPermitAssembler.create(accessPermit);

        BDDMockito.verify(vehicleAssembler).create(any(Vehicle.class));
    }

    @Test
    public void whenAssemblingAccessPermit_thenCallIdGenerator(){
        accessPermitAssembler.create(accessPermitDto, accessPermitPrice);

        BDDMockito.verify(idGenerator).generateId();
    }
}
