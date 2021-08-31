package ca.ulaval.glo4003.projet.base.ws.application.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumptionRepository;

public class AccessPermitService {
    private final AccessPermitAssembler accessPermitAssembler;
    private final AccessPermitRepository accessPermitRepository;
    private final VehicleConsumptionRepository vehicleConsumptionRepository;

    public AccessPermitService(AccessPermitAssembler accessPermitAssembler,
                               AccessPermitRepository accessPermitRepository,
                               VehicleConsumptionRepository vehicleConsumptionRepository) {
        this.accessPermitAssembler = accessPermitAssembler;
        this.accessPermitRepository = accessPermitRepository;
        this.vehicleConsumptionRepository = vehicleConsumptionRepository;
    }

    public AccessPermit create(AccessPermitDto accessPermitDto) {
        Price accessPermitPrice = vehicleConsumptionRepository.getVehicleConsumptionPriceForAccessPeriod(
                accessPermitDto.vehicle.vehicleConsumption, accessPermitDto.period);
        AccessPermit accessPermit = accessPermitAssembler.create(accessPermitDto, accessPermitPrice);
        accessPermit.validPermit();
        accessPermit.sendAccessCode();
        accessPermitRepository.save(accessPermit);
        return accessPermit;
    }


    public AccessPermitDto findAccessPermit(String permitId) {
        AccessPermit accessPermit = accessPermitRepository.findById(permitId);
        return accessPermitAssembler.create(accessPermit);
    }
}
