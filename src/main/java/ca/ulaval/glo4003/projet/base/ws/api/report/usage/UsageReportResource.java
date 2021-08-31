package ca.ulaval.glo4003.projet.base.ws.api.report.usage;

import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageReportDto;
import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageReportService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(UsageReportResource.USAGE_REPORT)
public class UsageReportResource {
    static final String USAGE_REPORT = "/report/usage";

    private final UsageReportService usageReportService;

    public UsageReportResource(UsageReportService usageReportService) {
        this.usageReportService = usageReportService;
    }

    @GET
    @Path("/monthly")
    public UsageReportDto getMonthlyUsageReport(@QueryParam("month") int month) {
        return usageReportService.getMonthlyReport(month);
    }

    @GET
    @Path("/daily")
    public UsageReportDto getDailyUsageReport(@QueryParam("month") int month, @QueryParam("day") int day) {
        return usageReportService.getDailyReport(month, day);
    }
}
