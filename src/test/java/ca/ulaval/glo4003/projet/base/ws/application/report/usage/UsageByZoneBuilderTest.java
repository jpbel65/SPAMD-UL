package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UsageByZoneBuilderTest {
    public static final Zone A_ZONE = Zone.ZONE_1;
    private static final ParkingUsage ANY_PARKING_USAGE = new ParkingUsage(A_ZONE, LocalDateTime.now());
    private static final LocalDateTime FIRST_DAY_OF_THE_MONTH = LocalDateTime.of(2020, 7, 1, 0, 0);

    private final UsageByZoneBuilder builder = new UsageByZoneBuilder();

    @Test
    public void whenBuildingWithAnything_thenReturnSelf() {
        List<ParkingUsage> anyRequests = givenRequests(1);
        UsageByZoneBuilder returnedBuilder = builder.withTotal(anyRequests);

        assertEquals(builder, returnedBuilder);
    }

    @Test
    public void givenPreviousBuild_whenBuildAgain_thenReportsAreNotEquals() {
        UsageByZoneDto previousReport = builder.build();

        UsageByZoneDto newReport = builder.build();

        assertNotEquals(previousReport, newReport);
    }

    @Test
    public void givenANumberOfRequests_whenBuildWithTotal_thenReportTotalIsANumber() {
        int aNumber = 5;
        List<ParkingUsage> requests = givenRequests(aNumber);

        UsageByZoneDto reports = builder.withTotal(requests).build();

        assertEquals(aNumber, reports.total);
    }

    @Test
    public void givenManyRequestsOnManyDays_whenBuildWithAveragePerDay_thenAveragePerDaysIsTotalDividedByNumberOfDays() {
        int numberOnDay1 = 5;
        int numberOnDay2 = 3;
        int numberOnDay3 = 42;
        int numberOfDaysInMonth = YearMonth.from(FIRST_DAY_OF_THE_MONTH).lengthOfMonth();
        List<ParkingUsage> requests = givenRequestsOnManyDays(numberOnDay1, numberOnDay2, numberOnDay3);

        UsageByZoneDto report = builder.withAverage(requests, numberOfDaysInMonth).build();

        Double average = (numberOnDay1 + numberOnDay2 + numberOnDay3) / (double) numberOfDaysInMonth;
        assertEquals(average, report.average);
    }

    @Test
    public void givenManyRequestsOnManyDays_whenBuildWithBusiestDay_thenBusiestDayIsDayWithMostRequests() {
        int busiestDay = 42;
        int day2 = 5;
        int day3 = 7;
        List<ParkingUsage> requests = givenRequestsOnManyDays(busiestDay, day2, day3);

        UsageByZoneDto report = builder.withBusiestDay(requests).build();

        String expectedDate = FIRST_DAY_OF_THE_MONTH.toLocalDate().toString();
        assertEquals(expectedDate, report.busiestDay.date);
        assertEquals(busiestDay, report.busiestDay.total);
    }

    @Test
    public void givenManyRequestsOnManyDays_whenBuildWithLeastBusyDay_thenLeastBusyDayIsDayWithLeastRequests() {
        int leastBusyDay = 3;
        int day2 = 42;
        int day3 = 57;
        List<ParkingUsage> requests = givenRequestsOnManyDays(leastBusyDay, day2, day3);

        UsageByZoneDto report = builder.withLeastBusyDay(requests).build();

        String expectedDate = FIRST_DAY_OF_THE_MONTH.toLocalDate().toString();
        assertEquals(expectedDate, report.leastBusyDay.date);
        assertEquals(leastBusyDay, report.leastBusyDay.total);
    }

    private List<ParkingUsage> givenRequests(int n) {
        return new ArrayList<>(Collections.nCopies(n, ANY_PARKING_USAGE));
    }

    private List<ParkingUsage> givenRequestsOnManyDays(int... numberPerDay) {
        List<ParkingUsage> requests = new ArrayList<>();

        for (int day = 0; day < numberPerDay.length; day++) {
            int numberOfRequests = numberPerDay[day];
            LocalDateTime requestDate = FIRST_DAY_OF_THE_MONTH.plusDays(day);
            ParkingUsage request = new ParkingUsage(A_ZONE, requestDate);
            requests.addAll(Collections.nCopies(numberOfRequests, request));
        }

        return requests;
    }
}
