package ca.ulaval.glo4003.projet.base.ws.application.report.usage;

public class UsageByDayDto {
    public String date;
    public int total;

    @Override
    public String toString() {
        return "UsageByDayDto{" +
                "date='" + date + '\'' +
                ", count=" + total +
                '}';
    }
}
