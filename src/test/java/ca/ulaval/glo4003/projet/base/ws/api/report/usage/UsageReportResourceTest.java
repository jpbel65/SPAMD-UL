package ca.ulaval.glo4003.projet.base.ws.api.report.usage;

import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsageReportResourceTest {
    private static final int ANY_MONTH = 7;
    private static final int ANY_DAY = 15;

    private UsageReportResource resource;

    @Mock
    private UsageReportService usageReportService;

    @Before
    public void setup() {
        resource = new UsageReportResource(usageReportService);
    }

    @Test
    public void whenGetMonthlyUsage_thenGetMonthlyReport() {
        resource.getMonthlyUsageReport(ANY_MONTH);

        verify(usageReportService).getMonthlyReport(ANY_MONTH);
    }

    @Test
    public void whenGetDailyUsage_thenGetDailyReport() {
        resource.getDailyUsageReport(ANY_MONTH, ANY_DAY);

        verify(usageReportService).getDailyReport(ANY_MONTH, ANY_DAY);
    }
}
