package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;

import java.util.List;

public interface AccessPermitRepository {
  List<AccessPermit> findAll();

  AccessPermit findById(String id);

  AccessPermit findByAccessCode(String accessCode);

  Price getTotalPricesForSchoolYear(SchoolYearDate schoolYearDate);

  Price getTotalPricesForVehicleConsumptionInSchoolYear(SchoolYearDate schoolYearDate, VehicleConsumption vehicleConsumption);

  void save(AccessPermit accessPermit);
}
