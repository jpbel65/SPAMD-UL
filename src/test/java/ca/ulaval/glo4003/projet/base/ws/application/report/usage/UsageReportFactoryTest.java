package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsageReportFactoryTest {
    private static final LocalDateTime FIRST_DAY_OF_THE_MONTH = LocalDateTime.of(2020, 7, 1, 0, 0);
    private static final YearMonth YEAR_MONTH = YearMonth.from(FIRST_DAY_OF_THE_MONTH);
    private static final int ANY_NUMBER_OF_REPORTS = 42;
    UsageReportFactory factory;

    @Mock(answer = Answers.RETURNS_SELF) private UsageByZoneBuilder usageReportBuilder;

    @Before
    public void setup() {
        factory =  new UsageReportFactory(usageReportBuilder);
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenBuildReportWithTotal() {
        List<ParkingUsage> parkingUsages = givenRequestsInManyZone(5);

        factory.createReportSummary(parkingUsages, YEAR_MONTH);

        verify(usageReportBuilder).withTotal(any());
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenBuildReportWithAverage() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportSummary(usages, YEAR_MONTH);

        verify(usageReportBuilder).withAverage(any(), eq(YEAR_MONTH.lengthOfMonth()));
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenBuildReportWithBusiestDay() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportSummary(usages, YEAR_MONTH);

        verify(usageReportBuilder).withBusiestDay(any());
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenBuildReportWithLeastBusyDay() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportSummary(usages, YEAR_MONTH);

        verify(usageReportBuilder).withLeastBusyDay(any());
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenBuildReport() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportSummary(usages, YEAR_MONTH);

        verify(usageReportBuilder).build();
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportSummary_thenReturnedReportIsNotNull() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);
        
        UsageReportDto report = factory.createReportSummary(usages, YEAR_MONTH);

        assertNotNull(report);
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportByDay_thenBuildReportWithTotal() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportByDay(usages);

        verify(usageReportBuilder).withTotal(any());
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportByDay_thenBuildReport() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        factory.createReportByDay(usages);

        verify(usageReportBuilder).build();
    }

    @Test
    public void givenAnyNumberOfReports_whenCreateReportByDay_thenReturnedReportIsNotNull() {
        List<ParkingUsage> usages = givenRequestsInManyZone(ANY_NUMBER_OF_REPORTS);

        UsageReportDto report = factory.createReportByDay(usages);

        assertNotNull(report);
    }

    private List<ParkingUsage> givenRequestsInManyZone(int... zones) {
        List<ParkingUsage> requests = new ArrayList<>();

        for (int i = 0;i<zones.length;i++) {
            ParkingUsage request = new ParkingUsage(Zone.values()[i], FIRST_DAY_OF_THE_MONTH);
            requests.addAll(Collections.nCopies(zones[i], request));
        }

        return requests;
    }

}