package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionAssembler;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ContraventionAssemblerTest {

    private final static String ID = "ID";
    private final static Zone A_ZONE = Zone.ZONE_1;
    private final static LocalDateTime ANY_DATE_TIME = LocalDateTime.now();
    private final static String A_HOUR = LocalTime.now().withSecond(0).withNano(0).toString();

    private ContraventionAssembler contraventionAssembler;
    private ContraventionDto contraventionDto;
    private Contravention contravention;

    @Mock
    private IdGenerator idGenerator;

    @Before
    public void setUp() {
        contraventionAssembler = new ContraventionAssembler(new InfractionAssembler(), idGenerator);
        contraventionDto = new ContraventionDto();
        contraventionDto.zone = A_ZONE.toString();
        contraventionDto.hour = A_HOUR;
        contravention = new Contravention(ID, A_ZONE, ANY_DATE_TIME);
    }

    @Test
    public void givenContraventionDto_whenAssemblingContravention_thenShouldReturnContravention() {
        Contravention actualContravention = contraventionAssembler.create(contraventionDto);

        assertEquals(A_ZONE, actualContravention.getZone());
        assertEquals(ANY_DATE_TIME.withSecond(0).withNano(0), actualContravention.getDate());
    }

    @Test
    public void givenContravention_whenAssemblingContraventionDto_thenShouldReturnContraventionDto() {
        ContraventionDto actualContraventionDto = contraventionAssembler.create(contravention);

        assertEquals(contravention.getZone().toString(), actualContraventionDto.zone);
        assertEquals(contravention.getDate().toLocalTime().toString(), actualContraventionDto.hour);
        assertEquals(contravention.getInfractions().size(), actualContraventionDto.infractions.size());
    }

    @Test
    public void whenAssemblingContravention_thenCallIdGenerator(){
        contraventionAssembler.create(contraventionDto);

        BDDMockito.verify(idGenerator).generateId();
    }
}
