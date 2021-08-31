package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class UsageByZoneBuilder {
    private UsageByZoneDto report = new UsageByZoneDto();

    public UsageByZoneBuilder withZone(String zone) {
        report.zone = zone;
        return this;
    }

    public UsageByZoneBuilder withTotal(List<ParkingUsage> parkingUsages) {
        report.total = parkingUsages.size();
        return this;
    }

    public UsageByZoneBuilder withAverage(List<ParkingUsage> parkingUsages, int numberOfDays) {
        report.average = parkingUsages.size() / (double) numberOfDays;
        return this;
    }

    public UsageByZoneBuilder withBusiestDay(List<ParkingUsage> parkingUsages) {
        report.busiestDay = parkingUsages.stream()
                .collect(groupingBy(byDate, counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(makeUsageByDayDto)
                .orElse(null);

        return this;
    }

    public UsageByZoneBuilder withLeastBusyDay(List<ParkingUsage> parkingUsages) {
        report.leastBusyDay = parkingUsages.stream()
                .collect(groupingBy(byDate, counting()))
                .entrySet().stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .map(makeUsageByDayDto)
                .orElse(null);

        return this;
    }

    public UsageByZoneDto build() {
        UsageByZoneDto builtReport = report;
        report = new UsageByZoneDto();
        return builtReport;
    }

    private final Function<ParkingUsage, LocalDate> byDate = r -> r.getDate().toLocalDate();

    private final Function<Map.Entry<LocalDate, Long>, UsageByDayDto> makeUsageByDayDto = entry -> {
        UsageByDayDto dto = new UsageByDayDto();
        dto.date = entry.getKey().toString();
        dto.total = entry.getValue().intValue();
        return dto;
    };
}
