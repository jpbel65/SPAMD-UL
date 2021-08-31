package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Contravention {

    private String id;
    private Zone zone;
    private LocalDateTime date;
    private final List<Infraction> infractions = new ArrayList<>();
    private boolean paid = false;

    public Contravention(String id, Zone zone, LocalDateTime date) {
        this.id = id;
        this.zone = zone;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Zone getZone() {
        return zone;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Infraction> getInfractions() {
        return infractions;
    }

    public void addInfraction(Infraction infraction) {
        infractions.add(infraction);
    }

    public void pay() {
        if (paid) throw new ContraventionAlreadyPaidException(id);
        else paid = true;
    }

    public void keepOnlyInfractionWithLargestCost() {
        if (infractions.size() != 0) {
            Infraction infractionWithLargestCost = infractions.stream()
                    .max(Comparator.comparing(infraction -> infraction.getCost().getValue()))
                    .get();

            infractions.clear();
            infractions.add(infractionWithLargestCost);
        }
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public Price getCostForSchoolYear(SchoolYearDate schoolYearDate) {
        Price total = new Price(0);
        if (schoolYearDate.isDateInCurrentSchoolYear(date.toLocalDate())) {
            for (Infraction infraction : infractions) {
                total = total.add(infraction.getCost());
            }
        }
        return total;
    }
}
