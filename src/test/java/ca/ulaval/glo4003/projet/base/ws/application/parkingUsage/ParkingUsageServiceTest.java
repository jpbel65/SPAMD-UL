package ca.ulaval.glo4003.projet.base.ws.application.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingUsageServiceTest {
    private ParkingUsageService parkingUsageService;
    @Mock
    private ParkingUsageRepository parkingUsageRepository;
    @Mock
    private ParkingUsageAssembler parkingUsageAssembler;
    @Mock
    private ParkingUsageDto parkingUsageDto;
    @Mock
    private ParkingUsage parkingUsage;

    @Before
    public void setUp() {
        parkingUsageService = new ParkingUsageService(parkingUsageAssembler, parkingUsageRepository);
        BDDMockito.when(parkingUsageAssembler.create(parkingUsageDto)).thenReturn(parkingUsage);
    }

    @Test
    public void whenSaveParkingUsage_thenParkingUsageAssemblerCreate() {
        parkingUsageService.saveParkingUsage(parkingUsageDto);

        BDDMockito.verify(parkingUsageAssembler).create(parkingUsageDto);
    }

    @Test
    public void whenSaveParkingUsage_thenParkingUsageRepositorySave() {
        parkingUsageService.saveParkingUsage(parkingUsageDto);

        BDDMockito.verify(parkingUsageRepository).save(parkingUsage);
    }
}