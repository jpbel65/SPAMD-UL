package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

import java.util.List;

public class UsageReportDto {
    public int total;
    public List<UsageByZoneDto> byZone;

    @Override
    public String toString() {
        return "UsageReportDto{" +
                "total=" + total +
                ", byZone=" + byZone +
                '}';
    }
}
