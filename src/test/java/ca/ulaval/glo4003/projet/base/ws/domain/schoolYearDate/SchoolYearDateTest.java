package ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SchoolYearDateTest {

  private final static int ANY_SEP_TO_DEC_MONTH = 10;
  private final static int ANY_JAN_TO_AUG_MONTH = 3;
  private final static LocalDate ANY_DATE = LocalDate.now();
  private final static LocalDate ANY_DATE_NOT_IN_AUG = LocalDate.of(ANY_DATE.getYear(), 9, ANY_DATE.getDayOfMonth());
  private final static LocalDate DATE_OF_CURRENT_SCHOOL_YEAR = LocalDate.of(2020, 10, 10);

  private SchoolYearDate schoolYearDate;
  private SchoolYearDate schoolYearDateNotInAug;

  @Before
  public void setUp() {
    schoolYearDate = new SchoolYearDate(ANY_DATE);
    schoolYearDateNotInAug = new SchoolYearDate(ANY_DATE_NOT_IN_AUG);
  }

  @Test
  public void givenMonthFromSepToDec_whenGettingMonthSchoolYear_thenSchoolYearShouldBeLesserOrEqualToInitialYear() {
    assert (schoolYearDate.getYearOfSchoolMonth(ANY_SEP_TO_DEC_MONTH) <= ANY_DATE.getYear());
  }

  @Test
  public void givenMonthFromJanToAug_whenGettingMonthSchoolYear_thenSchoolYearShouldBeGreaterOrEqualToInitialYear() {
    assert (schoolYearDate.getYearOfSchoolMonth(ANY_JAN_TO_AUG_MONTH) >= ANY_DATE.getYear());
  }

  @Test
  public void givenDateInFirstHalfOfSchoolYear_thenDateIsInCurrentSchoolYear() {
    LocalDate date = LocalDate.of(2020, 11, 13);

    SchoolYearDate schoolYearDate = new SchoolYearDate(DATE_OF_CURRENT_SCHOOL_YEAR);

    Assert.assertTrue(schoolYearDate.isDateInCurrentSchoolYear(date));
  }

  @Test
  public void givenDateInSecondHalfOfSchoolYear_thenDateIsInCurrentSchoolYear() {
    LocalDate date = LocalDate.of(2021, 02, 13);

    SchoolYearDate schoolYearDate = new SchoolYearDate(DATE_OF_CURRENT_SCHOOL_YEAR);

    Assert.assertTrue(schoolYearDate.isDateInCurrentSchoolYear(date));
  }

  @Test
  public void givenDateNotInSchoolYear_thenDateIsNotInCurrentSchoolYear() {
    LocalDate date = LocalDate.of(2022, 02, 13);

    SchoolYearDate schoolYearDate = new SchoolYearDate(DATE_OF_CURRENT_SCHOOL_YEAR);

    Assert.assertFalse(schoolYearDate.isDateInCurrentSchoolYear(date));
  }
}
