package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

public enum AccessType {
    AUTOMOBILE("Automobile"),
    BIKE("Bike");

    private final String type;

    AccessType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static AccessType fromString(String type) throws AccessTypeNotFoundException {
        for (AccessType accessType : AccessType.values()) {
            if (type.toUpperCase().equals(accessType.type.toUpperCase())) {
                return accessType;
            }
        }
        throw new AccessTypeNotFoundException(type);
    }
}
