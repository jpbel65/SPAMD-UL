package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsageReportServiceTest {
    private static final int ANY_YEAR = 2202;
    private static final int ANY_MONTH = 7;
    private static final int ANY_DAY = 17;

    private UsageReportService service;
    @Mock private ParkingUsageRepository parkingUsageRepository;
    @Mock private UsageReportFactory usageReportFactory;
    @Mock private SchoolYearFactory schoolYearFactory;
    @Mock private SchoolYearDate schoolYearDate;

    private final List<ParkingUsage> parkingUsages = new ArrayList<>();

    @Before
    public void setup() {
        given(parkingUsageRepository.getParkingUsageByMonth(ANY_YEAR, ANY_MONTH)).willReturn(parkingUsages);
        given(schoolYearDate.getYearOfSchoolMonth(ANY_MONTH)).willReturn(ANY_YEAR);

        service = new UsageReportService(parkingUsageRepository, usageReportFactory, schoolYearFactory);
        given(schoolYearFactory.create()).willReturn(schoolYearDate);
    }

    @Test
    public void whenGetMonthlyReport_thenFindAccessRequestByMonth() {
        service.getMonthlyReport(ANY_MONTH);

        verify(parkingUsageRepository).getParkingUsageByMonth(ANY_YEAR, ANY_MONTH);
    }

    @Test
    public void whenGetMonthlyReport_thenCreateReportSummary() {
        service.getMonthlyReport(ANY_MONTH);

        verify(usageReportFactory).createReportSummary(eq(parkingUsages), any());
    }

    @Test
    public void whenGetDailyReport_thenFindAccessRequestByMonth() {
        service.getDailyReport(ANY_MONTH, ANY_DAY);

        verify(parkingUsageRepository).getParkingUsageByMonthAndDay(ANY_YEAR, ANY_MONTH, ANY_DAY);
    }

    @Test
    public void whenGetDailyReport_thenCreateReportSummary() {
        service.getDailyReport(ANY_MONTH, ANY_DAY);

        verify(usageReportFactory).createReportByDay(parkingUsages);
    }
}