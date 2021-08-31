package ca.ulaval.glo4003.projet.base.ws.application.delivery;

import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;

import java.util.Map;

public class DeliveryProcedureFactory {
    private Map<DeliveryMode,DeliveryProcedure> procedures;

    public DeliveryProcedureFactory(Map<DeliveryMode, DeliveryProcedure> procedures) {
        this.procedures = procedures;
    }

    public DeliveryProcedure create(DeliveryMode deliveryMode){
        return procedures.get(deliveryMode);
    }
}
