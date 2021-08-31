package ca.ulaval.glo4003.projet.base.ws.application.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;

public class ParkingUsageService {
    private ParkingUsageAssembler parkingUsageAssembler;
    private ParkingUsageRepository parkingUsageRepository;

    public ParkingUsageService(ParkingUsageAssembler parkingUsageAssembler, ParkingUsageRepository parkingUsageRepository) {
        this.parkingUsageAssembler = parkingUsageAssembler;
        this.parkingUsageRepository = parkingUsageRepository;
    }

    public void saveParkingUsage(ParkingUsageDto parkingUsageDto){
        ParkingUsage parkingUsage = parkingUsageAssembler.create(parkingUsageDto);
        parkingUsageRepository.save(parkingUsage);
    }
}
