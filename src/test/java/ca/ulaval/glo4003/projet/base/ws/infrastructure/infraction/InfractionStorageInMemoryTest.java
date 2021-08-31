package ca.ulaval.glo4003.projet.base.ws.infrastructure.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InfractionStorageInMemoryTest {

    private static final InfractionCode VALID_CODE = InfractionCode.VIG_02;
    private InfractionStorageInMemory inMemoryInfractionRepository;

    @Before
    public void setUp() {
        inMemoryInfractionRepository = new InfractionStorageInMemory();
    }

    @Test
    public void givenAValidCode_whenFindByCode_thenShouldReturnCorrespondingInfraction() {
        Infraction infraction = inMemoryInfractionRepository.findByCode(VALID_CODE);

        assertEquals(VALID_CODE, infraction.getCode());
    }
}
