package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class UsageReportFactory {
    private final UsageByZoneBuilder usageByZoneBuilder;

    public UsageReportFactory(UsageByZoneBuilder usageByZoneBuilder) {
        this.usageByZoneBuilder = usageByZoneBuilder;
    }

    private UsageReportDto createReportBase(List<ParkingUsage> parkingUsages) {
        UsageReportDto report = new UsageReportDto();
        report.total = parkingUsages.size();
        return report;
    }

    public UsageReportDto createReportSummary(List<ParkingUsage> parkingUsages, YearMonth yearMonth) {
        UsageReportDto report = createReportBase(parkingUsages);

        Map<Zone, List<ParkingUsage>> usageByZone = groupByZone(parkingUsages);

        report.byZone = usageByZone.entrySet().stream()
            .map(entry -> usageByZoneBuilder
                .withZone(entry.getKey().toString())
                .withTotal(entry.getValue())
                .withAverage(entry.getValue(), yearMonth.lengthOfMonth())
                .withBusiestDay(entry.getValue())
                .withLeastBusyDay(entry.getValue())
                .build())
            .collect(Collectors.toList());

        return report;
    }

    public UsageReportDto createReportByDay(List<ParkingUsage> parkingUsages) {
        UsageReportDto report = createReportBase(parkingUsages);

        Map<Zone, List<ParkingUsage>> usageByZone = groupByZone(parkingUsages);

        report.byZone = usageByZone.entrySet().stream()
            .map(entry -> usageByZoneBuilder
                .withZone(entry.getKey().toString())
                .withTotal(entry.getValue())
                .build())
            .collect(Collectors.toList());

        return report;
    }

    private Map<Zone, List<ParkingUsage>> groupByZone(List<ParkingUsage> usages) {
        return usages.stream().collect(groupingBy(ParkingUsage::getZone));
    }
}
