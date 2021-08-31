package ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.AccessCodeNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.AccessPermitNotFoundException;
import com.google.common.truth.Truth;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AccessPermitRepositoryInMemoryTest {

  private static final String PARKING_PERMIT_ID_1 = "123";
  private static final String PARKING_PERMIT_ID_2 = "456";
  private static final String PARKING_PERMIT_ACCESS_CODE = "CODE";
  private static final VehicleConsumption ANY_VEHICLE_CONSUMPTION = VehicleConsumption.GREEDY;

  @Mock
  private AccessPermit accessPermit1;
  @Mock
  private AccessPermit accessPermit2;

  private AccessPermitRepository accessPermitRepositoryInMemory;

  @Before
  public void setUp() {
    Mockito.when(accessPermit1.getId()).thenReturn(PARKING_PERMIT_ID_1);
    Mockito.when(accessPermit2.getId()).thenReturn(PARKING_PERMIT_ID_2);
    Mockito.when(accessPermit1.getPrice()).thenReturn(new Price(3));
    Mockito.when(accessPermit2.getPrice()).thenReturn(new Price(2));
    BDDMockito.when(accessPermit1.verifyVehicleConsumption(ANY_VEHICLE_CONSUMPTION)).thenReturn(true);
    accessPermitRepositoryInMemory = new AccessPermitRepositoryInMemory();
  }

  @Test
  public void givenANewRepository_whenFindAll_thenRepositoryIsEmpty() {
    List<AccessPermit> accessPermits = accessPermitRepositoryInMemory.findAll();

    assertTrue(accessPermits.isEmpty());
  }

  @Test
  public void givenAccessPermit_whenSave_thenAccessPermitIsSaved() {
    accessPermitRepositoryInMemory.save(accessPermit1);

    assertEquals(1, accessPermitRepositoryInMemory.findAll().size());
  }

  @Test
  public void givenMultipleAccessPermits_whenSave_thenAllAccessPermitsAreSaved() {
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    assertEquals(2, accessPermitRepositoryInMemory.findAll().size());
  }

  @Test(expected = AccessPermitNotFoundException.class)
  public void givenAUnsavedAccessPermit_whenFindById_thenThrowAccessPermitIdNotFoundException() {
    accessPermitRepositoryInMemory.save(accessPermit1);

    accessPermitRepositoryInMemory.findById(PARKING_PERMIT_ID_2);
  }

  @Test
  public void givenASavedAccessPermit_whenFindById_thenShouldReturnAccessPermit() {
    accessPermitRepositoryInMemory.save(accessPermit1);

    Object accessPermit = accessPermitRepositoryInMemory.findById(PARKING_PERMIT_ID_1);

    assertEquals(accessPermit, accessPermit1);
  }

  @Test
  public void givenSavedAccessPermits_whenFindById_thenTheCorrespondingAccessPermitIsReturned() {
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    AccessPermit accessPermit = accessPermitRepositoryInMemory.findById(PARKING_PERMIT_ID_1);

    assertEquals(PARKING_PERMIT_ID_1, accessPermit.getId());
  }

  @Test
  public void givenSavedAccessPermits_whenFindAll_thenAllAccessPermitsAreReturned() {
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    List<AccessPermit> permits = accessPermitRepositoryInMemory.findAll();

    Truth.assertThat(permits).contains(accessPermit1);
    Truth.assertThat(permits).contains(accessPermit2);
  }

  @Test(expected = AccessCodeNotFoundException.class)
  public void givenAUnsavedAccessPermit_whenFindByAccessCode_thenThrowAccessCodeNotFoundException() {
    accessPermitRepositoryInMemory.findByAccessCode(PARKING_PERMIT_ACCESS_CODE);
  }

  @Test(expected = AccessCodeNotFoundException.class)
  public void givenNullAccessCode_whenFindByAccessCode_thenThrowAccessCodeNotFoundException() {
    accessPermitRepositoryInMemory.findByAccessCode(null);
  }

  @Test
  public void givenASavedAccessPermitWithAccessCode_whenFindByAccessCode_thenShouldReturnAccessPermit() {
    Mockito.when(accessPermit1.getAccessCode()).thenReturn(PARKING_PERMIT_ACCESS_CODE);
    accessPermitRepositoryInMemory.save(accessPermit1);

    Object permit = accessPermitRepositoryInMemory.findByAccessCode(PARKING_PERMIT_ACCESS_CODE);

    assertEquals(accessPermit1, permit);
  }

  @Test
  public void givenOneSavedAccessPermitWithAccessCodeAndOneWithoutIt_whenFindByAccessCode_thenShouldReturnAccessPermit() {
    Mockito.when(accessPermit1.getAccessCode()).thenReturn(PARKING_PERMIT_ACCESS_CODE);
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    AccessPermit permit = accessPermitRepositoryInMemory.findByAccessCode(PARKING_PERMIT_ACCESS_CODE);

    assertEquals(accessPermit1, permit);
  }

  @Test
  public void givenMultipleAccessPermitsInRepository_whenGetTotalPricesForSchoolYear_thenTotalPriceReturnsCorrectValue() {
    Mockito.when(accessPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(accessPermit2.getCreatedDate()).thenReturn(LocalDate.of(2021, 2, 14));
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    Price price = accessPermitRepositoryInMemory.getTotalPricesForSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10)));

    Assert.assertEquals(new Price(5), price);
  }

  @Test
  public void givenMultipleAccessPermitsInRepository_whenGetTotalPricesForSchoolYear_thenTotalPriceReturnsOnlyValueForPermitsInSchoolYear() {
    Mockito.when(accessPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(accessPermit2.getCreatedDate()).thenReturn(LocalDate.of(2022, 2, 14));
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    Price price = accessPermitRepositoryInMemory.getTotalPricesForSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10)));

    Assert.assertEquals(new Price(3), price);
  }

  @Test
  public void givenMultipleAccessPermitsInRepository_whenGetTotalPricesForSchoolYearAndVehicleConsumption_thenTotalPriceReturnsCorrectValue() {
    Mockito.when(accessPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(accessPermit2.getCreatedDate()).thenReturn(LocalDate.of(2021, 2, 14));
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);
    BDDMockito.when(accessPermit2.verifyVehicleConsumption(ANY_VEHICLE_CONSUMPTION)).thenReturn(true);

    Price price = accessPermitRepositoryInMemory.getTotalPricesForVehicleConsumptionInSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10)), ANY_VEHICLE_CONSUMPTION);

    Assert.assertEquals(new Price(5), price);
  }

  @Test
  public void givenMultipleAccessPermitsInRepository_whenGetTotalPricesForSchoolYearAndVehicleConsumption_thenTotalPriceReturnsOnlyValueForPermitsInSchoolYear() {
    Mockito.when(accessPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(accessPermit2.getCreatedDate()).thenReturn(LocalDate.of(2022, 2, 14));
    accessPermitRepositoryInMemory.save(accessPermit1);
    accessPermitRepositoryInMemory.save(accessPermit2);

    Price price = accessPermitRepositoryInMemory.getTotalPricesForVehicleConsumptionInSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10)), ANY_VEHICLE_CONSUMPTION);

    Assert.assertEquals(new Price(3), price);
  }

}
