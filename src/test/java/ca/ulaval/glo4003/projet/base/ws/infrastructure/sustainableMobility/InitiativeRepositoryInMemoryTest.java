package ca.ulaval.glo4003.projet.base.ws.infrastructure.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeRepository;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.InitiativeNotFoundException;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InitiativeRepositoryInMemoryTest {
    private static final String ANY_CODE = "INITIATIVE_1";
    private static final String ANY_NAME = "Campagne de sensibilité xyz";
    private static final Price ANY_COST = new Price(42);

    private final InitiativeRepository repository = new InitiativeRepositoryInMemory();
    private final Initiative initiative = new Initiative(ANY_CODE, ANY_NAME, ANY_COST);

    @Test
    public void givenNewRepository_whenGetAllInitiatives_thenListIsEmpty() {
        InitiativeRepositoryInMemory repo = new InitiativeRepositoryInMemory();

        List<Initiative> initiatives = repo.getAllInitiatives();

        Truth.assertThat(initiatives).isEmpty();
    }
    
    @Test
    public void whenSave_thenGetAllInitiativesContainsSavedInitiative() {
        repository.save(initiative);

        List<Initiative> initiatives = repository.getAllInitiatives();
        Truth.assertThat(initiatives).contains(initiative);
    }

    @Test
    public void givenSavedInitiative_whenFindByCode_thenReturnSavedInitiative() {
        repository.save(initiative);

        Initiative returnedInitiative = repository.findByCode(initiative.getCode());

        assertEquals(initiative, returnedInitiative);
    }

    @Test(expected = InitiativeNotFoundException.class)
    public void givenNoInitiatives_whenFindByCode_thenThrowInitiativeNotFoundException() {
        repository.findByCode(ANY_CODE);
    }

    @Test
    public void givenManyInitiativesSaved_whenGetTotalCost_thenReturnSumOfInitiativesCost() {
        int numberOfInitiatives = 42;
        float cost = 500;
        saveManyInitiatives(numberOfInitiatives, cost);

        Price totalCost = repository.getTotalCost();

        Price expected = new Price(numberOfInitiatives * cost);
        assertEquals(expected, totalCost);
    }

    private void saveManyInitiatives(int n, float cost) {
        for (int i = 0; i < n; i++) {
            Initiative ini = new Initiative("INITIATIVE_" + i, ANY_NAME, new Price(cost));
            repository.save(ini);
        }
    }
}