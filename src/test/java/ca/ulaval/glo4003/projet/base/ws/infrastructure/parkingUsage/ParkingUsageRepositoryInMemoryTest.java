package ca.ulaval.glo4003.projet.base.ws.infrastructure.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingUsageRepositoryInMemoryTest {
    private ParkingUsageRepository parkingUsageRepository;
    private ParkingUsage parkingUsage;
    private ParkingUsage otherParkingUsage;
    private final Zone A_ZONE = Zone.ZONE_1;
    private final LocalDateTime ANY_DATE = LocalDateTime.now();
    private final LocalDateTime OTHER_ANY_DATE = ANY_DATE.plusDays(1);

    @Before
    public void setUp(){
        parkingUsageRepository = new ParkingUsageRepositoryInMemory();
        parkingUsage = new ParkingUsage(A_ZONE, ANY_DATE);
        otherParkingUsage = new ParkingUsage(A_ZONE, OTHER_ANY_DATE);
    }

    @Test
    public void whenSaveParkingUsage_thenParkingUsageIsSave() {
        parkingUsageRepository.save(parkingUsage);
        List<ParkingUsage> parkingUsages = parkingUsageRepository.getParkingUsageByMonth(ANY_DATE.getYear(), ANY_DATE.getMonthValue());

        Assert.assertTrue(parkingUsages.contains(parkingUsage));
    }

    @Test
    public void givenSomeParkingUsage_whenGetParkingUsageByMonthAndDay_thenReturnParkingUsagesWithGoodDay() {
        parkingUsageRepository.save(parkingUsage);
        parkingUsageRepository.save(otherParkingUsage);

        List<ParkingUsage> parkingUsages = parkingUsageRepository.getParkingUsageByMonthAndDay(ANY_DATE.getYear(), ANY_DATE.getMonthValue(), ANY_DATE.getDayOfMonth());

        Assert.assertFalse(parkingUsages.contains(otherParkingUsage));
        Assert.assertTrue(parkingUsages.contains(parkingUsage));
    }
}
