package ca.ulaval.glo4003.projet.base.ws.domain.infraction;

public interface InfractionStorage {

    Infraction findByCode(InfractionCode code);
}
