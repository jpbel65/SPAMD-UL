package ca.ulaval.glo4003.projet.base.ws.domain.permit;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Optional;

public enum DayOfTheWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday"),
    NULL(null);

    private final String formattedDay;

    DayOfTheWeek(String formattedDay) {
        this.formattedDay = formattedDay;
    }

    @Override
    public String toString() {
        return this.formattedDay;
    }

    public static DayOfTheWeek fromString(String desiredDay) throws InvalidDayOfTheWeekException {
        if (!Optional.ofNullable(desiredDay).isPresent()){
            return NULL;
        }
        for (DayOfTheWeek dayOfTheWeek : DayOfTheWeek.values()) {
            if (dayOfTheWeek != DayOfTheWeek.NULL && desiredDay.toUpperCase().equals(dayOfTheWeek.formattedDay.toUpperCase())) {
                return dayOfTheWeek;
            }
        }

        throw new InvalidDayOfTheWeekException(desiredDay);
    }

    public DayOfWeek toDayOfWeek() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        TemporalAccessor accessor = formatter.parse(formattedDay);
        return DayOfWeek.from(accessor);
    }
}
