package ca.ulaval.glo4003.projet.base.ws.api.report.price;

import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceReportService;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceReportResourceTest {

    private static final String ANY_VEHICLE_CONSUMPTION = "Gourmande";
    private PriceReportResource priceReportResource;
    @Mock
    private PriceReportService priceReportService;

    @Before
    public void setup() {
        priceReportResource = new PriceReportResource(priceReportService);
    }

    @Test
    public void whenGetAccessPermitPriceReport_thenPriceReportServiceGetAccessPermitPriceReport() {
        priceReportResource.getAccessPermitPriceReport();

        BDDMockito.verify(priceReportService).getAccessPermitPriceReport();
    }

    @Test
    public void whenGetAccessPermitPriceReportWithVehicleConsumption_thenPriceReportServiceGetAccessPermitPriceReportWithVehicleConsumption() {
        priceReportResource.getAccessPermitPriceReportWithVehicleConsumption(ANY_VEHICLE_CONSUMPTION);

        BDDMockito.verify(priceReportService).getAccessPermitPriceReportWithVehicleConsumption(ANY_VEHICLE_CONSUMPTION);
    }

    @Test
    public void whenGetParkingPermitPriceReport_thenPriceReportServiceGetParkingPermitPriceReport() {
        priceReportResource.getParkingPermitPriceReport();

        BDDMockito.verify(priceReportService).getParkingPermitPriceReport();
    }

    @Test
    public void whenGetInfractionPriceReport_thenPriceReportServiceGetInfractionPriceReport() {
        priceReportResource.getContraventionPriceReport();

        BDDMockito.verify(priceReportService).getContraventionPriceReport();
    }
}
