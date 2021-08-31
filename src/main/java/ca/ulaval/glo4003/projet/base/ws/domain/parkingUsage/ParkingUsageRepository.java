package ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage;

import java.util.List;

public interface ParkingUsageRepository {
    void save(ParkingUsage parkingUsage);
    List<ParkingUsage> getParkingUsageByMonth(int year, int month);
    List<ParkingUsage> getParkingUsageByMonthAndDay(int year, int month, int day);
}
