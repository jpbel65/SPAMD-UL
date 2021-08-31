package ca.ulaval.glo4003.projet.base.ws.infrastructure.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumptionRepository;
import ca.ulaval.glo4003.projet.base.ws.externResource.CsvReader;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.PriceNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.VehicleConsumptionNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.PeriodNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class VehicleConsumptionRepositoryImplTest {

    @Mock
    private CsvReader csvReader;

    public static final String BAD_VEHICLE_CONSUMPTION = "Nice Try";
    public static final String BAD_PERIOD = "Nice Try";
    public static final String GOOD_PERIOD = "1j";
    public static final String GOOD_VEHICLE_CONSUMPTION = "Gourmande";
    private VehicleConsumptionRepository vehicleConsumptionRepository;
    private HashMap<String, Integer> ANY_LINES = new HashMap<>();
    private HashMap<String, Integer> ANY_COLUMNS = new HashMap<>();
    private String[][] ANY_CONTENTS;

    @Before
    public void setUp() {
        ANY_LINES.put(GOOD_VEHICLE_CONSUMPTION.toUpperCase(),0);
        ANY_COLUMNS.put(GOOD_PERIOD.toUpperCase(),0);
        ANY_CONTENTS = new String[ANY_LINES.size()][ANY_COLUMNS.size()];
        ANY_CONTENTS[0][0] = "159";
        BDDMockito.when(csvReader.getLinesHeaders()).thenReturn(ANY_LINES);
        BDDMockito.when(csvReader.getColumnsHeaders()).thenReturn(ANY_COLUMNS);
        BDDMockito.when(csvReader.getContents()).thenReturn(ANY_CONTENTS);
        vehicleConsumptionRepository = new VehicleConsumptionRepositoryImpl(csvReader);
    }

    @Test(expected = PriceNotFoundException.class)
    public void givenBadVehicleConsumption_whenGetVehicleConsumptionPriceForAccessPeriod_thenThrowPriceNotFoundException() {
        vehicleConsumptionRepository.getVehicleConsumptionPriceForAccessPeriod(BAD_VEHICLE_CONSUMPTION, GOOD_PERIOD);
    }

    @Test(expected = PriceNotFoundException.class)
    public void givenBadPeriod_whenGetVehicleConsumptionPriceForAccessPeriod_thenThrowPRiceNotFoundException() {
        vehicleConsumptionRepository.getVehicleConsumptionPriceForAccessPeriod(GOOD_VEHICLE_CONSUMPTION, BAD_PERIOD);
    }
}
