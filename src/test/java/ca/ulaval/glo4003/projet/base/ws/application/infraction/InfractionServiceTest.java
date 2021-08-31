package ca.ulaval.glo4003.projet.base.ws.application.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InfractionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InfractionServiceTest {

    private final static InfractionCode CODE = InfractionCode.ZONE_01;
    private final static InfractionCode CODE_FOR_INVALID_PERMIT_ID = InfractionCode.VIG_02;
    @Mock private InfractionException infractionException;
    @Mock private InfractionStorage infractionStorage;
    @Mock private Infraction infraction;
    private InfractionService infractionService;

    @Before
    public void setUp() {
        BDDMockito.given(infractionException.getInfractionCode()).willReturn(CODE);

        infractionService = new InfractionService(infractionStorage);
    }

    @Test
    public void givenInfractionException_whenCreateInfraction_thenShouldFindInfractionByCode() {
        BDDMockito.given(infractionException.getInfractionCode()).willReturn(CODE);

        infractionService.createInfraction(infractionException);

        BDDMockito.verify(infractionStorage).findByCode(infractionException.getInfractionCode());
    }

    @Test
    public void givenInfractionException_whenCreateInfraction_thenShouldReturnInfraction() {
        BDDMockito.given(infractionStorage.findByCode(infractionException.getInfractionCode()))
                .willReturn(infraction);

        Object actualInfraction = infractionService.createInfraction(infractionException);

        assertEquals(infraction, actualInfraction);
    }

    @Test
    public void whenCreateInvalidParkingPermitInfraction_thenInfractionStorageFindInvalidPermitIdInfraction() {
        infractionService.createInvalidParkingPermitInfraction();

        BDDMockito.verify(infractionStorage).findByCode(CODE_FOR_INVALID_PERMIT_ID);
    }

    @Test
    public void whenCreateInvalidParkingPermitInfraction_thenReturnInfraction() {
        BDDMockito.given(infractionStorage.findByCode(CODE_FOR_INVALID_PERMIT_ID)).willReturn(infraction);

        Infraction returnedInfraction = infractionService.createInvalidParkingPermitInfraction();

        assertSame(infraction, returnedInfraction);
    }
}
