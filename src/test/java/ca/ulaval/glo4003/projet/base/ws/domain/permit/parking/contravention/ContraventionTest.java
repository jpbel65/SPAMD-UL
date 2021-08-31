package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ContraventionTest {

    private final String ANY_ID = "12312312313";
    private final Zone ANY_ZONE = Zone.ZONE_1;
    private final LocalDateTime ANY_DATE = LocalDateTime.now();
    private final InfractionCode ANY_INFRACTION_CODE = InfractionCode.ZONE_01;
    private final String ANY_INFRACTION_OFFENSE = "Ouch! This is offensive!";
    private final Price A_SMALLER_COST = new Price(10);
    private final Price A_LARGER_COST = new Price(20);
    private Infraction anInfraction;
    private Infraction anInfractionWithALargerCost;
    private List<Infraction> infractions = new ArrayList<>();
    private static final SchoolYearDate SCHOOL_YEAR_DATE = new SchoolYearDate(LocalDate.of(2020, 10, 10));


    @Mock
    private Infraction infraction1;
    @Mock
    private Infraction infraction2;
    private Contravention contravention;

    @Before
    public void setUp() {
        Mockito.when(infraction1.getCost()).thenReturn(new Price(3));
        Mockito.when(infraction2.getCost()).thenReturn(new Price(2));
        anInfraction = new Infraction(ANY_INFRACTION_CODE, ANY_INFRACTION_OFFENSE, A_SMALLER_COST);
        anInfractionWithALargerCost = new Infraction(ANY_INFRACTION_CODE, ANY_INFRACTION_OFFENSE, A_LARGER_COST);
        contravention = new Contravention(ANY_ID, ANY_ZONE, ANY_DATE);
    }

    @Test(expected = ContraventionAlreadyPaidException.class)
    public void GivenContraventionAlreadyPaid_whenPayContravention_thenThrowContraventionException() {
        contravention.pay();

        contravention.pay();
    }

    @Test
    public void givenAnInfraction_whenKeepOnlyInfractionWithLargestCost_thenContraventionShouldHaveThatOneInfraction() {
        contravention.addInfraction(anInfraction);

        contravention.keepOnlyInfractionWithLargestCost();

        Assert.assertTrue(contravention.getInfractions().contains(anInfraction));
    }

    @Test
    public void givenMultipleInfractions_whenKeepOnlyInfractionWithLargestCost_thenContraventionShouldHaveOnlyInfractionWithHighestCost() {
        contravention.addInfraction(anInfraction);
        contravention.addInfraction(anInfractionWithALargerCost);
        int expectInfractionListSize = 1;

        contravention.keepOnlyInfractionWithLargestCost();

        Assert.assertEquals(expectInfractionListSize, contravention.getInfractions().size());
        Assert.assertTrue(contravention.getInfractions().contains(anInfractionWithALargerCost));
    }

    @Test
    public void givenASchoolYear_thenTotalPriceIsCalculatedForAllInfractionsInSchoolYear() {
        contravention.addInfraction(infraction1);
        contravention.addInfraction(infraction2);

        Assert.assertEquals(new Price(5), contravention.getCostForSchoolYear(SCHOOL_YEAR_DATE));
    }

    @Test
    public void givenASchoolYearDifferentFromContraventionDate_thenTotalPriceForSchoolYearIsZero() {
        contravention.setDate(LocalDateTime.of(LocalDate.of(2022, 2, 14), LocalTime.now()));

        contravention.addInfraction(infraction1);
        contravention.addInfraction(infraction2);

        Assert.assertEquals(new Price(0), contravention.getCostForSchoolYear(SCHOOL_YEAR_DATE));
    }
}
