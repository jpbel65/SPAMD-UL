package ca.ulaval.glo4003.projet.base.ws.domain.price;

import ca.ulaval.glo4003.projet.base.ws.externResource.CsvReader;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.PeriodNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.PriceNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ZoneNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.price.ParkingPriceRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPriceRepositoryImplTest {
    @Mock
    CsvReader csvReader;

    private ParkingPriceRepository parkingPriceRepository;

    private static final String BAD_ZONE = "Nice Try";
    public static final String BAD_PERIOD = "Nice Try";
    public static final String GOOD_PERIOD = "1j";
    private final String GOOD_ZONE = "Zone1";
    private HashMap<String, Integer> ANY_LINES = new HashMap<>();
    private HashMap<String, Integer> ANY_COLUMNS = new HashMap<>();
    private String[][] ANY_CONTENTS;

    @Before
    public void setUp() {
        ANY_LINES.put(GOOD_ZONE.toUpperCase(),0);
        ANY_COLUMNS.put(GOOD_PERIOD.toUpperCase(),0);
        ANY_CONTENTS = new String[ANY_LINES.size()][ANY_COLUMNS.size()];
        ANY_CONTENTS[0][0] = "159";
        BDDMockito.when(csvReader.getLinesHeaders()).thenReturn(ANY_LINES);
        BDDMockito.when(csvReader.getColumnsHeaders()).thenReturn(ANY_COLUMNS);
        BDDMockito.when(csvReader.getContents()).thenReturn(ANY_CONTENTS);
        parkingPriceRepository = new ParkingPriceRepositoryImpl(csvReader);

    }

    @Test(expected = PriceNotFoundException.class)
    public void givenBadPeriod_whenGetZonePriceForPeriod_thenThrowPriceNotFoundException() {
        parkingPriceRepository.getZonePriceForPeriod(GOOD_ZONE, BAD_PERIOD);
    }

    @Test(expected = PriceNotFoundException.class)
    public void givenBadZone_whenGetZonePriceForPeriod_thenThrowPriceNotFoundException() {
        parkingPriceRepository.getZonePriceForPeriod(BAD_ZONE, GOOD_PERIOD);
    }
}
