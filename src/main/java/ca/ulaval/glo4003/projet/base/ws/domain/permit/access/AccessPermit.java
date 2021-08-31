package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.DayOfTheWeek;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.InvalidDayOfTheWeekException;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccessPermit {

    private final String id;
    private final AccessType type;
    private final User user;
    private final Vehicle vehicle;
    private final AccessPeriod period;
    private final DayOfTheWeek dayOfTheWeek;
    private final String accessCode;
    private final Price price;
    private final LocalDate createdDate;
    private final DeliveryMode deliveryMode;
    private final DeliveryProcedure deliveryProcedure;
    private final AddressBook addressBook;

    public AccessPermit(String id, AccessType type, User user, Vehicle vehicle, AccessPeriod period, DayOfTheWeek dayOfTheWeek,
                        String accessCode, Price price, LocalDate date, DeliveryMode deliveryMode, DeliveryProcedure deliveryProcedure, AddressBook addressBook) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.vehicle = vehicle;
        this.period = period;
        this.dayOfTheWeek = dayOfTheWeek;
        this.accessCode = accessCode;
        this.price = price;
        this.createdDate = date;
        this.deliveryMode = deliveryMode;
        this.deliveryProcedure = deliveryProcedure;
        this.addressBook = addressBook;
    }

    public void verifyDateTime(LocalDateTime dateTime) {
        if (!period.equals(AccessPeriod.SCHOOL_YEAR)) {
            if (dayOfTheWeek.equals(DayOfTheWeek.NULL) || (!dateTime.getDayOfWeek().equals(dayOfTheWeek.toDayOfWeek()))){
                throw new InvalidAccessDayException(dayOfTheWeek.toString());
            }
        }
    }

    public void verifyType(AccessType type) {
        if (!type.equals(this.type)) {
            throw new InvalidAccessTypeException(this.type, type);
        }
    }

    public String getId() {
        return id;
    }

    public AccessType getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public AccessPeriod getPeriod() {
        return period;
    }

    public Price getPrice() {
        return price;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public boolean verifyVehicleConsumption(VehicleConsumption vehicleConsumption) {
        return vehicle.verifyVehicleConsumption(vehicleConsumption);
    }

    public void sendAccessCode(){
        deliveryProcedure.sendPermitId(addressBook, accessCode);
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void validPermit(){
        if (period.equals(AccessPeriod.ONE_DAY_PER_WEEK_PER_SESSION) && dayOfTheWeek.equals(DayOfTheWeek.NULL)){
            throw new InvalidDayOfTheWeekException(dayOfTheWeek.toString());
        }
    }
}
