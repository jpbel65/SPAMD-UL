package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

public class UsageByZoneDto {
    public String zone;
    public int total;
    public Double average;
    public UsageByDayDto busiestDay;
    public UsageByDayDto leastBusyDay;

    @Override
    public String toString() {
        return "UsageByZoneDto{" +
                "zone='" + zone + '\'' +
                ", total=" + total +
                ", average=" + average +
                ", busiestDay=" + busiestDay +
                ", leastBusyDay=" + leastBusyDay +
                '}';
    }
}
