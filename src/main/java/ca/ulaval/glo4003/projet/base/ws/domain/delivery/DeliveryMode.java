package ca.ulaval.glo4003.projet.base.ws.domain.delivery;

public enum DeliveryMode {
    EMAIL("EMAIL"),
    SNAIL_MAIL("SNAIL_MAIL"),
    SSP_OFFICE("SSP_OFFICE");

    private final String deliveryModeCode;

    DeliveryMode(String deliveryModeCode) {
        this.deliveryModeCode = deliveryModeCode;
    }

    @Override
    public String toString() { return deliveryModeCode; }

    public static DeliveryMode fromString(String deliveryModeCode) throws InvalidDeliveryModeException {
        for (DeliveryMode deliveryMode : DeliveryMode.values()) {
            if(deliveryModeCode.toUpperCase().equals(deliveryMode.deliveryModeCode.toUpperCase())) {
                return deliveryMode;
            }
        }

        throw new InvalidDeliveryModeException(deliveryModeCode);
    }
}
