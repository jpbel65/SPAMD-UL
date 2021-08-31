package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidator;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidatorInitializer;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitZoneValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PermitValidatorInitializerTest {

    @Mock private InfractionStorage infractionStorage;
    private PermitValidatorInitializer permitValidatorInitializer;

    @Before
    public void setUp() {
        permitValidatorInitializer = new PermitValidatorInitializer(infractionStorage);
    }

    @Test
    public void whenCreatePermitValidatorChain_thenReturnZoneValidator() {
        PermitValidator zoneValidator =  permitValidatorInitializer.createPermitValidatorChain();

        Assert.assertTrue(zoneValidator instanceof PermitZoneValidator);
    }
}