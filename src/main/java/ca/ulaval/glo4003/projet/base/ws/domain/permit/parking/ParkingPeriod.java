package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.PeriodNotFoundException;

public enum ParkingPeriod {
    ONE_DAY_PER_WEEK_PER_SESSION("1j/sem/session");

    private final String period;

    ParkingPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return period;
    }

    public static ParkingPeriod fromString(String period) throws PeriodNotFoundException{
        for (ParkingPeriod parkingPeriod : ParkingPeriod.values()) {
            if (period.toUpperCase().equals(parkingPeriod.period.toUpperCase())){
                return parkingPeriod;
            }
        }
        throw new PeriodNotFoundException(period);
    }
}
