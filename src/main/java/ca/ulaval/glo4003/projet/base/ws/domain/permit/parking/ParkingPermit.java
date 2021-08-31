package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidZoneInfraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.ContraventionAlreadyExistsException;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.ContraventionNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingPermit {

    private final String id;
    private final Zone zone;
    private final DeliveryMode deliveryMode;
    private final AddressBook deliveryAddress;
    private final ParkingPeriod period;
    private final DayOfTheWeek dayOfTheWeek;
    private final Price price;
    private final LocalDate createdDate;
    private final Map<String, Contravention> contraventions = new HashMap<>();
    private final DeliveryProcedure deliveryProcedure;

    public ParkingPermit(String id, Zone zone, DeliveryMode deliveryMode,
                         AddressBook deliveryAddress, ParkingPeriod period, DayOfTheWeek dayOfTheWeek,
                         Price price, LocalDate date, DeliveryProcedure deliveryProcedure) {
        this.id = id;
        this.zone = zone;
        this.deliveryMode = deliveryMode;
        this.deliveryAddress = deliveryAddress;
        this.period = period;
        this.dayOfTheWeek = dayOfTheWeek;
        this.price = price;
        this.createdDate = date;
        this.deliveryProcedure = deliveryProcedure;
    }

    public Price getPrice() {
        return price;
    }

    public ParkingPeriod getPeriod() {
        return period;
    }

    public String getId() {
        return id;
    }

    public Zone getZone() {
        return zone;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public AddressBook getDeliveryAddress() {
        return deliveryAddress;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void verifyDateTime(LocalDateTime dateTime) {
        if (!dateTime.getDayOfWeek().equals(dayOfTheWeek.toDayOfWeek())) {
            throw new InvalidDateInfraction();
        }
    }

    public void verifyZone(Zone zone) {
        if (zone != this.zone) {
            throw new InvalidZoneInfraction();
        }
    }

    public void sendParkingPermitCode(){
        deliveryProcedure.sendPermitId(deliveryAddress, id);
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void addContravention(Contravention contravention) {
        if (contraventions.containsKey(contravention.getId())) throw new ContraventionAlreadyExistsException();

        contraventions.put(contravention.getId(), contravention);
    }

    public Contravention findContraventionById(String id) {
        if (!contraventions.containsKey(id)) throw new ContraventionNotFoundException(id);

        return contraventions.get(id);
    }

    public void payContravention(String id) {
        Contravention contravention = findContraventionById(id);
        contravention.pay();
    }

    public Price getTotalPriceOfContraventionsForSchoolYear(SchoolYearDate schoolYearDate) {
        Price total = new Price(0);

        for (Contravention contravention : contraventions.values()) {
            if (contravention.isPaid()) {
                total = total.add(contravention.getCostForSchoolYear(schoolYearDate));
            }
        }
        return total;
    }
}
