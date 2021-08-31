package ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery;

import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;

public class PostalDelivery implements DeliveryProcedure {

    @Override
    public void sendPermitId(AddressBook addressBook, String permitId) {
        System.out.println("Send package to " + addressBook.getAddress() + " with Id " + permitId);
    }
}
