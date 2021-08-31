package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

public enum AccessPeriod {
    ONE_DAY_PER_WEEK_PER_SESSION("1j/semaine/session"),
    SCHOOL_YEAR("3 session");

    private final String period;

    AccessPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return period;
    }

    public static AccessPeriod fromString(String period) throws PeriodNotFoundException {
        for (AccessPeriod accessPeriod : AccessPeriod.values()) {
            if (period.toUpperCase().equals(accessPeriod.period.toUpperCase())){
                return accessPeriod;
            }
        }
        throw new PeriodNotFoundException(period);
    }
}
