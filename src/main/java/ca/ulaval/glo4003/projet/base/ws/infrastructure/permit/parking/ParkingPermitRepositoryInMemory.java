package ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ParkingPermitNotFoundException;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingPermitRepositoryInMemory implements ParkingPermitRepository {

  private Map<String, ParkingPermit> parkingPermits = new HashMap<>();

  @Override
  public List<ParkingPermit> findAll() {
    return Lists.newArrayList(parkingPermits.values());
  }

  @Override
  public ParkingPermit findById(String id) {
    return Optional.ofNullable(parkingPermits.get(id)).orElseThrow(() -> new ParkingPermitNotFoundException(id));
  }

  @Override
  public void save(ParkingPermit parkingPermit) {
    parkingPermits.put(parkingPermit.getId(), parkingPermit);
  }

  @Override
  public Price getTotalPricesForSchoolYear(SchoolYearDate schoolYearDate) {
    Price total = new Price(0);

    for (ParkingPermit parkingPermit : parkingPermits.values()) {
      if (schoolYearDate.isDateInCurrentSchoolYear(parkingPermit.getCreatedDate())) {
        total = total.add(parkingPermit.getPrice());
      }
    }
    return total;
  }
}

