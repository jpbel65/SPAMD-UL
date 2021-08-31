package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;

import java.util.List;

public interface ParkingPermitRepository {
  List<ParkingPermit> findAll();

  ParkingPermit findById(String id);

  void save(ParkingPermit parkingPermit);

  Price getTotalPricesForSchoolYear(SchoolYearDate schoolYearDate);

}
