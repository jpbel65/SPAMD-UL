package ca.ulaval.glo4003.projet.base.ws.domain.infraction;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.InfractionNotFoundException;

public enum InfractionCode {
    ZONE_01("ZONE_01"),
    ZONE_02("ZONE_02"),
    ZONE_03("ZONE_03"),
    VIG_01("VIG_01"),
    VIG_02("VIG_02"),
    VIG_03("VIG_03"),
    VIG_04("VIG_04"),
    TEMPS_01("TEMPS_01");

    private final String code;

    InfractionCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

    public static InfractionCode fromString(String code) throws InfractionNotFoundException {
        for (InfractionCode infractionCode : InfractionCode.values()) {
            if (code.toUpperCase().equals(infractionCode.code.toUpperCase())) {
                return infractionCode;
            }
        }
        throw new InfractionNotFoundException(code);
    }
}
