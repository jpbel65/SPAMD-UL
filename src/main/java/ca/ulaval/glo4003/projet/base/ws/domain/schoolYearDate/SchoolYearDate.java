package ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate;

import java.time.LocalDate;
import java.util.Objects;

public class SchoolYearDate {

  final private LocalDate initialDate;
  private int yearOne;
  private int yearTwo;

  public SchoolYearDate(LocalDate initialDate) {
    this.initialDate = initialDate;
    this.yearOne = setYearOne(initialDate);
    this.yearTwo = setYearTwo(initialDate);
  }

  public LocalDate getDate() {
    return this.initialDate;
  }

  public int getYearOfSchoolMonth(int month) {
    int year = yearOne;
    if (month < 9) year = yearTwo;

    return year;
  }

  private int setYearOne(LocalDate initialDate) {
    yearOne = initialDate.getYear();

    if (initialDate.getMonthValue() < 9) {
      yearOne--;
    }
    return yearOne;
  }

  private int setYearTwo(LocalDate initialDate) {
    yearTwo = initialDate.getYear();

    if (initialDate.getMonthValue() >= 9) {
      yearTwo++;
    }
    return yearTwo;
  }

  public boolean isDateInCurrentSchoolYear(LocalDate date) {
    return (date.getMonthValue() >= 9 && date.getYear() == yearOne) ||
        (date.getMonthValue() < 9 && date.getYear() == yearTwo);
  }

  @Override
  public boolean equals(Object o){
    if (o == null || getClass() != o.getClass()) return false;
    SchoolYearDate otherSchoolYearDate = (SchoolYearDate)o;
    return initialDate.equals(otherSchoolYearDate.initialDate);
  }

  @Override
  public int hashCode(){
    return Objects.hash(initialDate);
  }

}
