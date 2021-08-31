package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedureFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPeriod;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDate;
import java.util.UUID;

public class ParkingPermitAssembler {
    private DeliveryProcedureFactory deliveryProcedureFactory;
    private IdGenerator idGenerator;

    public ParkingPermitAssembler(DeliveryProcedureFactory deliveryProcedureFactory, IdGenerator idGenerator) {
        this.deliveryProcedureFactory = deliveryProcedureFactory;
        this.idGenerator = idGenerator;
    }

    public ParkingPermit create(ParkingPermitDto parkingPermitDto, Price parkingPermitPrice) {
        String id = idGenerator.generateId();
        Zone zone = Zone.fromString(parkingPermitDto.zone);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.fromString(parkingPermitDto.dayOfTheWeek);
        DeliveryMode deliveryMode = DeliveryMode.fromString(parkingPermitDto.deliveryMode);
        DeliveryProcedure deliveryProcedure = deliveryProcedureFactory.create(deliveryMode);
        String deliveryAddress = parkingPermitDto.deliveryAddress;
        ParkingPeriod period = ParkingPeriod.fromString(parkingPermitDto.period);
        return new ParkingPermit(
            id,
            zone,
            deliveryMode,
            new AddressBook(deliveryAddress),
            period,
            dayOfTheWeek,
            parkingPermitPrice,
            LocalDate.now(),
                deliveryProcedure);
    }

    public ParkingPermitDto create(ParkingPermit parkingPermit) {
        ParkingPermitDto parkingPermitDto = new ParkingPermitDto();
        parkingPermitDto.id = parkingPermit.getId();
        parkingPermitDto.zone = parkingPermit.getZone().toString();
        parkingPermitDto.dayOfTheWeek = parkingPermit.getDayOfTheWeek().toString();
        parkingPermitDto.deliveryAddress = parkingPermit.getDeliveryAddress().getAddress();
        parkingPermitDto.period = parkingPermit.getPeriod().toString();
        return parkingPermitDto;
    }
}
