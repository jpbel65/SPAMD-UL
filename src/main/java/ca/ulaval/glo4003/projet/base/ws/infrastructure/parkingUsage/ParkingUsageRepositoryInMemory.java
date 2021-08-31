package ca.ulaval.glo4003.projet.base.ws.infrastructure.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingUsageRepositoryInMemory implements ParkingUsageRepository {
    private List<ParkingUsage> parkingUsages = new ArrayList<>();

    @Override
    public void save(ParkingUsage parkingUsage) {
        this.parkingUsages.add(parkingUsage);
    }

    @Override
    public List<ParkingUsage> getParkingUsageByMonth(int year, int month) {
        return parkingUsages
                .stream()
                .filter(parkingUsage -> parkingUsage.getDate().getYear()==year)
                .filter(parkingUsage -> parkingUsage.getDate().getMonthValue()==month)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingUsage> getParkingUsageByMonthAndDay(int year, int month, int day) {
        return parkingUsages
                .stream()
                .filter(parkingUsage -> parkingUsage.getDate().getYear()==year)
                .filter(parkingUsage -> parkingUsage.getDate().getMonthValue()==month)
                .filter(parkingUsage -> parkingUsage.getDate().getDayOfMonth()==day)
                .collect(Collectors.toList());
    }
}
