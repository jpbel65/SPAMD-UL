package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

public class ParkingPermitDto {

    public String id;
    public String deliveryMode;
    public String deliveryAddress;
    public String period;
    public String zone;
    public String dayOfTheWeek;

    @Override
    public String toString() {
        return "ParkingPermitDto{" +
                "id='" + id + '\'' +
                ", deliveryMode='" + deliveryMode + '\'' +
                ", address='" + deliveryAddress + '\'' +
                ", period='" + period + '\'' +
                ", zone='" + zone + '\'' +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                '}';
    }
}
