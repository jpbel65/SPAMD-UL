package ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery;

import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;

public class SSPDelivery implements DeliveryProcedure {

    @Override
    public void sendPermitId(AddressBook addressBook, String permitId) {
        System.out.println("Send Permit " + permitId + " to SSP office");
    }
}
