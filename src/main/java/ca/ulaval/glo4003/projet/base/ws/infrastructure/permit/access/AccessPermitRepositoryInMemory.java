package ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.AccessCodeNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.AccessPermitNotFoundException;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccessPermitRepositoryInMemory implements AccessPermitRepository {

  private final Map<String, AccessPermit> accessPermits = new HashMap<>();

  @Override
  public List<AccessPermit> findAll() {
    return Lists.newArrayList(accessPermits.values());
  }

  @Override
  public AccessPermit findById(String id) {
    return Optional.ofNullable(accessPermits.get(id)).orElseThrow(() -> new AccessPermitNotFoundException(id));
  }

  @Override
  public void save(AccessPermit accessPermit) {
    accessPermits.put(accessPermit.getId(), accessPermit);
  }

  @Override
  public AccessPermit findByAccessCode(String accessCode) {
    return accessPermits
        .values()
        .stream()
        .filter(accessPermit -> accessPermit.getAccessCode().equals(accessCode))
        .findFirst()
        .orElseThrow(() -> new AccessCodeNotFoundException(accessCode));
  }

  @Override
  public Price getTotalPricesForSchoolYear(SchoolYearDate schoolYearDate) {
    Price total = new Price(0);

    for (AccessPermit accessPermit : accessPermits.values()) {
      if (schoolYearDate.isDateInCurrentSchoolYear(accessPermit.getCreatedDate())) {
        total = total.add(accessPermit.getPrice());
      }
    }
    return total;
  }

  @Override
  public Price getTotalPricesForVehicleConsumptionInSchoolYear(SchoolYearDate schoolYearDate, VehicleConsumption vehicleConsumption) {
    Price total = new Price(0);

    for (AccessPermit accessPermit : accessPermits.values()) {
      if (schoolYearDate.isDateInCurrentSchoolYear(accessPermit.getCreatedDate()) && accessPermit.verifyVehicleConsumption(vehicleConsumption)) {
        total = total.add(accessPermit.getPrice());
      }
    }
    return total;
  }
}
