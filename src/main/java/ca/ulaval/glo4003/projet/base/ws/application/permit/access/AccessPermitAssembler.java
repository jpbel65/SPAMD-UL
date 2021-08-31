package ca.ulaval.glo4003.projet.base.ws.application.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedureFactory;
import ca.ulaval.glo4003.projet.base.ws.application.user.UserAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleAssembler;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPeriod;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessType;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;

import java.time.LocalDate;
import java.util.UUID;

public class AccessPermitAssembler {

    private final UserAssembler userAssembler;
    private final VehicleAssembler vehicleAssembler;
    private final DeliveryProcedureFactory deliveryProcedureFactory;
    private final IdGenerator idGenerator;

    public AccessPermitAssembler(UserAssembler userAssembler, VehicleAssembler vehicleAssembler, DeliveryProcedureFactory deliveryProcedureFactory, IdGenerator idGenerator) {
        this.userAssembler = userAssembler;
        this.vehicleAssembler = vehicleAssembler;
        this.deliveryProcedureFactory = deliveryProcedureFactory;
        this.idGenerator = idGenerator;
    }

    public AccessPermit create(AccessPermitDto accessPermitDto, Price accessPermitPrice) {
        String id = idGenerator.generateId();
        String accessCode = UUID.randomUUID().toString();
        AccessType type = AccessType.fromString(accessPermitDto.accessType);
        User user = userAssembler.create(accessPermitDto.user);
        Vehicle vehicle = vehicleAssembler.create(accessPermitDto.vehicle);
        AccessPeriod period = AccessPeriod.fromString(accessPermitDto.period);
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.fromString(accessPermitDto.dayOfTheWeek);
        DeliveryMode deliveryMode = DeliveryMode.fromString(accessPermitDto.deliveryMode);
        DeliveryProcedure deliveryProcedure = deliveryProcedureFactory.create(deliveryMode);
        AddressBook addressBook = new AddressBook(accessPermitDto.deliveryAddress);
        return new AccessPermit(id, type, user, vehicle, period, dayOfTheWeek, accessCode, accessPermitPrice, LocalDate.now(), deliveryMode, deliveryProcedure, addressBook);
    }

    public AccessPermitDto create(AccessPermit accessPermit) {
        AccessPermitDto accessPermitDto = new AccessPermitDto();
        accessPermitDto.id = accessPermit.getId();
        accessPermitDto.accessType = accessPermit.getType().toString();
        accessPermitDto.user = userAssembler.create(accessPermit.getUser());
        accessPermitDto.vehicle = vehicleAssembler.create(accessPermit.getVehicle());
        accessPermitDto.period = accessPermit.getPeriod().toString();
        accessPermitDto.dayOfTheWeek = accessPermit.getDayOfTheWeek().toString();
        accessPermitDto.accessCode = accessPermit.getAccessCode();
        return accessPermitDto;
    }

}
