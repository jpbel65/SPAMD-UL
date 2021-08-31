package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;

import java.time.YearMonth;
import java.util.List;

public class UsageReportService {
    private final ParkingUsageRepository parkingUsageRepository;
    private final UsageReportFactory usageReportFactory;
    private final SchoolYearFactory schoolYearFactory;

    public UsageReportService(ParkingUsageRepository parkingUsageRepository, UsageReportFactory usageReportFactory, SchoolYearFactory schoolYearFactory) {
        this.parkingUsageRepository = parkingUsageRepository;

        this.usageReportFactory = usageReportFactory;
        this.schoolYearFactory = schoolYearFactory;
    }

    public UsageReportDto getMonthlyReport(int month) {
        SchoolYearDate date = schoolYearFactory.create();
        int year = date.getYearOfSchoolMonth(month);
        List<ParkingUsage> parkingUsage = parkingUsageRepository.getParkingUsageByMonth(year, month);


        return usageReportFactory.createReportSummary(parkingUsage, YearMonth.of(year, month));
    }

    public UsageReportDto getDailyReport(int month, int day) {
        SchoolYearDate date = schoolYearFactory.create();
        int year = date.getYearOfSchoolMonth(month);
        List<ParkingUsage> parkingUsage = parkingUsageRepository.getParkingUsageByMonthAndDay(year, month, day);

        return usageReportFactory.createReportByDay(parkingUsage);
    }
}
