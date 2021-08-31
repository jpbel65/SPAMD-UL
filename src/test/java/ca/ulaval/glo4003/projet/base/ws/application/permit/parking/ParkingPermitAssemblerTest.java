package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedureFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPeriod;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPermitAssemblerTest {

    private static final String ANY_ID = "123";
    private static final Zone A_ZONE = Zone.ZONE_1;
    private static final String DELIVERY_MODE = "EMAIL";
    private static final DeliveryMode DELIVERY_MODE_ENUM = DeliveryMode.fromString(DELIVERY_MODE);
    private static final String DELIVERY_ADDRESS = "an address";
    private static final DayOfTheWeek DAY_OF_THE_WEEK = DayOfTheWeek.FRIDAY;
    private static final ParkingPeriod ONE_DAY_PER_WEEK_PER_SESSION = ParkingPeriod.ONE_DAY_PER_WEEK_PER_SESSION;

    private ParkingPermitAssembler parkingPermitAssembler;
    private ParkingPermitDto parkingPermitDto;
    @Mock
    private ParkingPermit parkingPermit;
    @Mock
    private AddressBook addressBook;
    @Mock
    private Price price;
    @Mock
    private DeliveryProcedureFactory deliveryProcedureFactory;
    @Mock
    private IdGenerator idGenerator;

    @Before
    public void setUp() {
        parkingPermitAssembler = new ParkingPermitAssembler(deliveryProcedureFactory, idGenerator);
        parkingPermitDto = new ParkingPermitDto();
        parkingPermitDto.id = ANY_ID;
        parkingPermitDto.zone = A_ZONE.toString();
        parkingPermitDto.deliveryMode = DELIVERY_MODE;
        parkingPermitDto.dayOfTheWeek = DAY_OF_THE_WEEK.toString();
        parkingPermitDto.period = ONE_DAY_PER_WEEK_PER_SESSION.toString();

        BDDMockito.given(parkingPermit.getId()).willReturn(ANY_ID);
        BDDMockito.given(parkingPermit.getDeliveryAddress()).willReturn(addressBook);
        BDDMockito.given(addressBook.getAddress()).willReturn(DELIVERY_ADDRESS);
        BDDMockito.given(parkingPermit.getDayOfTheWeek()).willReturn(DAY_OF_THE_WEEK);
        BDDMockito.given(parkingPermit.getPeriod()).willReturn(ONE_DAY_PER_WEEK_PER_SESSION);
        BDDMockito.given(parkingPermit.getZone()).willReturn(A_ZONE);
    }


    @Test
    public void givenParkingPermitDTO_whenAssemblingParkingPermit_ThenShouldReturnParkingPermit() {
        ParkingPermit parkingPermit = parkingPermitAssembler.create(parkingPermitDto, price);

        Assert.assertEquals(parkingPermitDto.zone, parkingPermit.getZone().toString());
        Assert.assertEquals(parkingPermitDto.deliveryMode, parkingPermit.getDeliveryMode().toString());
        Assert.assertEquals(parkingPermitDto.deliveryAddress, parkingPermit.getDeliveryAddress().getAddress());
        Assert.assertEquals(parkingPermitDto.period, parkingPermit.getPeriod().toString());
        Assert.assertEquals(parkingPermitDto.dayOfTheWeek, parkingPermit.getDayOfTheWeek().toString());
        Assert.assertEquals(price, parkingPermit.getPrice());
    }

    @Test
    public void givenParkingPermit_whenAssemblingParkingPermitDTO_ThenShouldReturnParkingPermitDTO() {
        ParkingPermitDto parkingPermitDto = parkingPermitAssembler.create(parkingPermit);

        Assert.assertEquals(parkingPermit.getZone().toString(), parkingPermitDto.zone);
        Assert.assertEquals(parkingPermit.getDeliveryAddress().getAddress(), parkingPermitDto.deliveryAddress);
        Assert.assertEquals(parkingPermit.getPeriod().toString(), parkingPermitDto.period);
        Assert.assertEquals(parkingPermit.getDayOfTheWeek().toString(), parkingPermitDto.dayOfTheWeek);
    }

    @Test
    public void givenParkingPermit_whenAssemblingParkingPermitDTO_ThenVerifyDeliveryProcedureFactoryCreate() {
        parkingPermitAssembler.create(parkingPermitDto, price);

        BDDMockito.verify(deliveryProcedureFactory).create(DELIVERY_MODE_ENUM);
    }

    @Test
    public void whenAssemblingParkingPermit_thenCallIdGenerator(){
        parkingPermitAssembler.create(parkingPermitDto, price);

        BDDMockito.verify(idGenerator).generateId();
    }
}
