package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionDto;

import java.util.ArrayList;
import java.util.List;

public class ContraventionDto {
    public String id;
    public String zone;
    public String hour;
    public List<InfractionDto> infractions = new ArrayList<>();

    @Override
    public String toString() {
        return "ContraventionDto{" +
                "id='" + id + '\'' +
                ", zone='" + zone + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
