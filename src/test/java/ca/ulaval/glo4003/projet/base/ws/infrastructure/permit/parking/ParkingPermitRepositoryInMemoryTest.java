package ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ParkingPermitNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPermitRepositoryInMemoryTest {
  private static final String ANY_ID_1 = "123";
  private static final String ANY_ID_2 = "456";

  @Mock
  private ParkingPermit parkingPermit1;
  @Mock
  private ParkingPermit parkingPermit2;

  private ParkingPermitRepository parkingPermitRepository;

  @Before
  public void setUp() {
    Mockito.when(parkingPermit1.getId()).thenReturn(ANY_ID_1);
    Mockito.when(parkingPermit2.getId()).thenReturn(ANY_ID_2);
    Mockito.when(parkingPermit1.getPrice()).thenReturn(new Price(3));
    Mockito.when(parkingPermit2.getPrice()).thenReturn(new Price(2));
    parkingPermitRepository = new ParkingPermitRepositoryInMemory();
  }

  @Test
  public void givenANewRepository_whenFindAll_then_repositoryIsEmpty() {
    List<ParkingPermit> payments = parkingPermitRepository.findAll();

    assertTrue(payments.isEmpty());
  }

  @Test
  public void givenParkingPermit_whenSave_thenPaymentIsSaved() {
    parkingPermitRepository.save(parkingPermit1);

    assertEquals(1, parkingPermitRepository.findAll().size());
  }

  @Test
  public void givenMultipleParkingPermit_whenSave_thenAllParkingPermitAreSaved() {
    parkingPermitRepository.save(parkingPermit1);
    parkingPermitRepository.save(parkingPermit2);

    assertEquals(2, parkingPermitRepository.findAll().size());
  }

  @Test(expected = ParkingPermitNotFoundException.class)
  public void givenAnInvalidParkingPermitId_when_FindById_thenThrowParkingPermitNotFoundException() {
    parkingPermitRepository.save(parkingPermit1);

    parkingPermitRepository.findById(ANY_ID_2);
  }

  @Test
  public void givenASavedParkingPermit_whenFindById_thenShouldReturnParkingPermit() {
    parkingPermitRepository.save(parkingPermit1);

    Object parkingPermit = parkingPermitRepository.findById(ANY_ID_1);

    assertEquals(parkingPermit1, parkingPermit);
  }

  @Test
  public void givenSavedParkingPermit_whenFindById_thenCorrespondingParkingPermitIsReturned() {
    parkingPermitRepository.save(parkingPermit1);
    parkingPermitRepository.save(parkingPermit2);

    ParkingPermit parkingPermit = parkingPermitRepository.findById(ANY_ID_1);

    assertEquals(parkingPermit, parkingPermit1);
  }

  @Test
  public void givenSaveParkingPermit_whenFindAll_thenAllParkingPermitAreReturned() {
    parkingPermitRepository.save(parkingPermit1);
    parkingPermitRepository.save(parkingPermit2);

    List<ParkingPermit> parkingPermits = parkingPermitRepository.findAll();

    assertTrue(parkingPermits.contains(parkingPermit1));
    assertTrue(parkingPermits.contains(parkingPermit2));
  }

  @Test
  public void givenMultipleParkingPermitsInRepository_thenTotalPriceReturnsCorrectValue() {
    Mockito.when(parkingPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(parkingPermit2.getCreatedDate()).thenReturn(LocalDate.of(2021, 2, 14));

    parkingPermitRepository.save(parkingPermit1);
    parkingPermitRepository.save(parkingPermit2);

    Assert.assertEquals(new Price(5), parkingPermitRepository.getTotalPricesForSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10))));
  }

  @Test
  public void givenMultipleParkingPermitsInRepository_thenTotalPriceReturnsOnlyValueForPermitsInSchoolYear() {
    Mockito.when(parkingPermit1.getCreatedDate()).thenReturn(LocalDate.of(2020, 11, 14));
    Mockito.when(parkingPermit2.getCreatedDate()).thenReturn(LocalDate.of(2022, 2, 14));

    parkingPermitRepository.save(parkingPermit1);
    parkingPermitRepository.save(parkingPermit2);

    Assert.assertEquals(new Price(3), parkingPermitRepository.getTotalPricesForSchoolYear(new SchoolYearDate(LocalDate.of(2020, 10, 10))));
  }
}
