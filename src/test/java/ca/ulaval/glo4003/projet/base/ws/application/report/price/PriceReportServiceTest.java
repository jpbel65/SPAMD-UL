package ca.ulaval.glo4003.projet.base.ws.application.report.price;

import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.InvalidVehicleConsumptionException;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PriceReportServiceTest {
    private PriceReportService priceReportService;
    private SchoolYearDate schoolYearDate;
    private final String ANY_VEHICLE_CONSUMPTION = "Gourmande";
    private final String ANY_BAD_VEHICLE_CONSUMPTION = "Piscine";

    @Mock
    private PriceAssembler priceAssembler;
    @Mock
    private Price price;
    @Mock
    private AccessPermitRepository accessPermitRepository;
    @Mock
    private ParkingPermitRepository parkingPermitRepository;
    @Mock
    private ParkingPermit parkingPermit;
    @Mock
    private SchoolYearFactory schoolYearFactory;


    @Before
    public void setUp() {
        priceReportService = new PriceReportService(priceAssembler,
                accessPermitRepository,
                parkingPermitRepository,
                schoolYearFactory);
        schoolYearDate = new SchoolYearDate(LocalDate.now());
        BDDMockito.when(accessPermitRepository.getTotalPricesForSchoolYear(schoolYearDate)).thenReturn(price);
        BDDMockito.when(accessPermitRepository.getTotalPricesForVehicleConsumptionInSchoolYear(schoolYearDate, VehicleConsumption.fromString(ANY_VEHICLE_CONSUMPTION))).thenReturn(price);
        BDDMockito.when(parkingPermitRepository.getTotalPricesForSchoolYear(schoolYearDate)).thenReturn(price);
        BDDMockito.when(price.add(price)).thenReturn(price);
        BDDMockito.when(schoolYearFactory.create()).thenReturn(schoolYearDate);
    }

    @Test
    public void whenGetAccessPermitPriceReport_thenPriceIsGetFromAccessPermitRepository() {
        priceReportService.getAccessPermitPriceReport();

        BDDMockito.verify(accessPermitRepository).getTotalPricesForSchoolYear(schoolYearDate);
    }

    @Test
    public void whenGetAccessPermitPriceReport_thenAssemblerIsCall() {
        priceReportService.getAccessPermitPriceReport();

        BDDMockito.verify(priceAssembler).create(price);
    }

    @Test
    public void whenGetAccessPermitPriceReportWithVehicleConsumption_thenPriceIsGetFromAccessPermitRepository() {
        priceReportService.getAccessPermitPriceReportWithVehicleConsumption(ANY_VEHICLE_CONSUMPTION);

        BDDMockito.verify(accessPermitRepository).getTotalPricesForVehicleConsumptionInSchoolYear(schoolYearDate,
                VehicleConsumption.fromString(ANY_VEHICLE_CONSUMPTION));
    }

    @Test
    public void whenGetAccessPermitPriceReportWithVehicleConsumption_thenAssemblerIsCall() {
        priceReportService.getAccessPermitPriceReportWithVehicleConsumption(ANY_VEHICLE_CONSUMPTION);

        BDDMockito.verify(priceAssembler).create(price);
    }

    @Test(expected = InvalidVehicleConsumptionException.class)
    public void givenBadVehicleConsumption_whenGetAccessPermitPriceReportWithVehicleConsumption_thenThrowInvalidVehicleConsumptionException() {
        priceReportService.getAccessPermitPriceReportWithVehicleConsumption(ANY_BAD_VEHICLE_CONSUMPTION);
    }

    @Test
    public void whenGetParkingPermitPriceReport_thenPriceIsGetFromParkingPermitRepository() {
        priceReportService.getParkingPermitPriceReport();

        BDDMockito.verify(parkingPermitRepository).getTotalPricesForSchoolYear(schoolYearDate);
    }

    @Test
    public void whenGetParkingPermitPriceReport_thenAssemblerIsCall() {
        priceReportService.getParkingPermitPriceReport();

        BDDMockito.verify(priceAssembler).create(price);
    }

    @Test
    public void whenGetInfractionPriceReport_thenFetchParkingPermits() {
        priceReportService.getContraventionPriceReport();

        BDDMockito.verify(parkingPermitRepository).findAll();
    }

    private void givenManyPermits(int numberOfPermits, int pricePerPermit) {
        List<ParkingPermit> permits = new ArrayList<>();
        for (int i = 0; i < numberOfPermits; i++) {
            permits.add(parkingPermit);
        }
        BDDMockito.given(parkingPermit.getTotalPriceOfContraventionsForSchoolYear(schoolYearDate)).willReturn(new Price(pricePerPermit));
        BDDMockito.given(parkingPermitRepository.findAll()).willReturn(permits);
    }

    @Test
    public void givenManyParkingPermit_whenGetInfractionPriceReport_thenGetTotalPriceOfContraventionIsCalledAsManyTimes() {
        int numberOfPermits = 10;
        givenManyPermits(numberOfPermits, 1);

        priceReportService.getContraventionPriceReport();

        BDDMockito.verify(parkingPermit, Mockito.times(numberOfPermits)).getTotalPriceOfContraventionsForSchoolYear(schoolYearDate);
    }

    @Test
    public void givenManyParkingPermit_whenGetInfractionPriceReport_thenAssemblerIsCall() {
        int numberOfPermits = 10;
        int pricePerPermit = 5;
        givenManyPermits(numberOfPermits, pricePerPermit);

        priceReportService.getContraventionPriceReport();

        Price exptectedPrice = new Price(numberOfPermits * pricePerPermit);
        BDDMockito.verify(priceAssembler).create(exptectedPrice);
    }

    @Test
    public void whenGetTotalRevenues_thenPriceIsGetFromAllRepository() {
        priceReportService.getTotalRevenues();

        BDDMockito.verify(parkingPermitRepository).findAll();
        BDDMockito.verify(parkingPermitRepository).getTotalPricesForSchoolYear(schoolYearDate);
        BDDMockito.verify(accessPermitRepository).getTotalPricesForSchoolYear(schoolYearDate);
    }

    @Test
    public void whenGetTotalRevenues_thenRepositoryPriceIsAddTogether() {
        BDDMockito.given(parkingPermitRepository.findAll()).willReturn(Collections.singletonList(parkingPermit));
        BDDMockito.given(parkingPermit.getTotalPriceOfContraventionsForSchoolYear(schoolYearDate)).willReturn(price);

        priceReportService.getTotalRevenues();

        BDDMockito.verify(price, Mockito.times(2)).add(price);
    }
}
