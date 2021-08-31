package ca.ulaval.glo4003.projet.base.ws.application.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleDto;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumptionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPermitServiceTest {

    private static final String ACCESS_CODE = "9659";
    private static final String PERIOD = "1j";
    private static final String VEHICLE_CONSUMPTION = "Gourmande";

    @Mock
    private AccessPermitAssembler accessPermitAssembler;
    @Mock
    private AccessPermitRepository accessPermitRepository;
    @Mock
    private VehicleConsumptionRepository vehicleConsumptionRepository;
    private AccessPermitService accessPermitService;
    private AccessPermitDto accessPermitDto;
    @Mock
    private AccessPermit accessPermit;
    @Mock
    private Price accessPermitPrice;
    private VehicleDto vehicleDto;


    @Before
    public void setup() {
        accessPermitDto = new AccessPermitDto();
        accessPermitDto.period = PERIOD;
        vehicleDto = new VehicleDto();
        vehicleDto.vehicleConsumption = VEHICLE_CONSUMPTION;
        accessPermitDto.vehicle = vehicleDto;
        BDDMockito.given(accessPermitAssembler.create(accessPermitDto, accessPermitPrice)).willReturn(accessPermit);
        accessPermitService = new AccessPermitService(accessPermitAssembler, accessPermitRepository, vehicleConsumptionRepository);
        BDDMockito.when(vehicleConsumptionRepository.getVehicleConsumptionPriceForAccessPeriod(VEHICLE_CONSUMPTION, PERIOD)).thenReturn(accessPermitPrice);
    }

    @Test
    public void whenCreate_thenAssembleAccessPermit() {
        accessPermitService.create(accessPermitDto);

        BDDMockito.verify(accessPermitAssembler).create(accessPermitDto, accessPermitPrice);
    }

    @Test
    public void whenCreate_thenSaveAccessPermit() {
        accessPermitService.create(accessPermitDto);

        BDDMockito.verify(accessPermitRepository).save(accessPermit);
    }

    @Test
    public void whenCreate_thenReturnAccessPermit() {
        Object permit = accessPermitService.create(accessPermitDto);

        Assert.assertEquals(accessPermit, permit);
    }

    @Test
    public void givenAPermitId_whenFindAccessPermit_thenShouldFindAccessPermitWithTheCorrectId() {
        accessPermitService.findAccessPermit(ACCESS_CODE);

        BDDMockito.verify(accessPermitRepository).findById(ACCESS_CODE);
    }

    @Test
    public void givenAccessPermit_whenFindAccessPermit_thenShouldCreateAccessPermit() {
        BDDMockito.given(accessPermitRepository.findById(ACCESS_CODE)).willReturn(accessPermit);

        accessPermitService.findAccessPermit(ACCESS_CODE);

        BDDMockito.verify(accessPermitAssembler).create(accessPermit);
    }

    @Test
    public void whenCreateAccessPermit_thenAccessPermitSendAccessCode(){
        accessPermitService.create(accessPermitDto);

        BDDMockito.verify(accessPermit).sendAccessCode();
    }

    @Test
    public void whenCreateAccessPermit_thenAccessPermitValidItself(){
        accessPermitService.create(accessPermitDto);

        BDDMockito.verify(accessPermit).validPermit();
    }
}
