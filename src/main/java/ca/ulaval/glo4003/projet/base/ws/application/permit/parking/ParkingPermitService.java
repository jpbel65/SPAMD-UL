package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidator;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.NullParkingPermitFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.ParkingPriceRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ParkingPermitNotFoundException;

public class ParkingPermitService {
    private final ParkingPermitRepository parkingPermitRepository;
    private final ParkingPermitAssembler parkingPermitAssembler;
    private final ParkingPriceRepository parkingPriceRepository;

    private final ContraventionAssembler contraventionAssembler;
    private final PermitValidator permitValidator;
    private final NullParkingPermitFactory nullParkingPermitFactory;
    private final InfractionService infractionService;

    public ParkingPermitService(ParkingPermitRepository parkingPermitRepository,
                                ParkingPermitAssembler parkingPermitAssembler,
                                ParkingPriceRepository parkingPriceRepository,
                                ContraventionAssembler contraventionAssembler,
                                PermitValidator permitValidator,
                                NullParkingPermitFactory nullParkingPermitFactory,
                                InfractionService infractionService) {
        this.parkingPermitRepository = parkingPermitRepository;
        this.parkingPermitAssembler = parkingPermitAssembler;
        this.parkingPriceRepository = parkingPriceRepository;
        this.contraventionAssembler = contraventionAssembler;
        this.permitValidator = permitValidator;
        this.nullParkingPermitFactory = nullParkingPermitFactory;
        this.infractionService = infractionService;
    }

    public ParkingPermit createParkingPermit(ParkingPermitDto parkingPermitDto) {
        Price parkingPermitPrice = parkingPriceRepository
                .getZonePriceForPeriod(parkingPermitDto.zone, parkingPermitDto.period);
        ParkingPermit parkingPermit = parkingPermitAssembler.create(parkingPermitDto, parkingPermitPrice);
        parkingPermit.sendParkingPermitCode();
        parkingPermitRepository.save(parkingPermit);
        return parkingPermit;
    }

    public ParkingPermitDto findParkingPermit(String id) {
        ParkingPermit parkingPermit = parkingPermitRepository.findById(id);
        return parkingPermitAssembler.create(parkingPermit);
    }

    public ContraventionDto verifyPermit(String id, ContraventionDto contraventionDto) {
        Contravention contravention = contraventionAssembler.create(contraventionDto);
        ParkingPermit parkingPermit;

        try {
            parkingPermit = parkingPermitRepository.findById(id);
            permitValidator.verify(contravention, parkingPermit);
        }
        catch (ParkingPermitNotFoundException e) {
            parkingPermit = nullParkingPermitFactory.create();
            Infraction infraction = infractionService.createInvalidParkingPermitInfraction();
            contravention.addInfraction(infraction);
        }

        contravention.keepOnlyInfractionWithLargestCost();
        parkingPermit.addContravention(contravention);
        parkingPermitRepository.save(parkingPermit);

        return contraventionAssembler.create(contravention);
    }

    public ContraventionDto findContraventionById(String permitId, String contraventionId) {
        ParkingPermit parkingPermit = parkingPermitRepository.findById(permitId);
        Contravention contravention = parkingPermit.findContraventionById(contraventionId);
        return contraventionAssembler.create(contravention);
    }

    public void payContravention(String permitId, String contraventionId) {
        ParkingPermit parkingPermit = parkingPermitRepository.findById(permitId);
        parkingPermit.payContravention(contraventionId);
        parkingPermitRepository.save(parkingPermit);
    }
}
