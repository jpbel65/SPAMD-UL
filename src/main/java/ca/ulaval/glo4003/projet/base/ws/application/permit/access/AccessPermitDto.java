package ca.ulaval.glo4003.projet.base.ws.application.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.user.UserDto;
import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleDto;

public class AccessPermitDto {

    public String id;
    public String accessType;
    public UserDto user;
    public VehicleDto vehicle;
    public String period;
    public String dayOfTheWeek;
    public String deliveryMode;
    public String deliveryAddress;
    public String accessCode;

    @Override
    public String toString() {
        return "AccessPermitDto{" +
               "id='" + id + '\'' +
               ", accessType='" + accessType + '\'' +
               ", user=" + user +
               ", vehicle=" + vehicle +
               ", period='" + period + '\'' +
               ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
               ", deliveryMode='" + deliveryMode + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", accessCode='" + accessCode + '\'' +
               '}';
    }
}
