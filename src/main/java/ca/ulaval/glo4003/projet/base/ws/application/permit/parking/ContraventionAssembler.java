package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionAssembler;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class ContraventionAssembler {
    private InfractionAssembler infractionAssembler;
    private IdGenerator idGenerator;

    public ContraventionAssembler(InfractionAssembler infractionAssembler, IdGenerator idGenerator) {
        this.infractionAssembler = infractionAssembler;
        this.idGenerator = idGenerator;
    }

    public Contravention create(ContraventionDto contraventionDto) {
        String id = idGenerator.generateId();
        LocalTime time = LocalTime.parse(contraventionDto.hour);
        LocalDateTime date = LocalDateTime.now().with(time);
        Zone zone = Zone.fromString(contraventionDto.zone);
        return new Contravention(id, zone, date);
    }

    public ContraventionDto create(Contravention contravention) {
        ContraventionDto contraventionDto = new ContraventionDto();
        contraventionDto.zone = contravention.getZone().toString();
        contraventionDto.hour = contravention.getDate().toLocalTime().toString();
        contraventionDto.id = contravention.getId();
        for (Infraction infraction: contravention.getInfractions()) {
            contraventionDto.infractions.add(infractionAssembler.create(infraction));
        }

        return contraventionDto;
    }
}
