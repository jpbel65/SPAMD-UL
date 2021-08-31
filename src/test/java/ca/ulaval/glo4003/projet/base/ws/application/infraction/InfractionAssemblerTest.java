package ca.ulaval.glo4003.projet.base.ws.application.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InfractionAssemblerTest {

    private InfractionAssembler infractionAssembler;
    private Infraction infraction;
    private InfractionCode ANY_CODE = InfractionCode.ZONE_01;
    private String ANY_OFFENSE = "OUCH";
    private Price ANY_COST = new Price(15);


    @Before
    public void setUp() {
        infractionAssembler = new InfractionAssembler();
        infraction = new Infraction(ANY_CODE, ANY_OFFENSE, ANY_COST);
    }

    @Test
    public void whenCreate_thenShouldReturnInfractionDto() {
        InfractionDto infractionDto = infractionAssembler.create(infraction);

        assertEquals(infraction.getOffense(),infractionDto.offense);
        assertEquals(infraction.getCode().toString(),infractionDto.code);
        assertEquals(infraction.getCost().toString(),infractionDto.cost);

    }
}
