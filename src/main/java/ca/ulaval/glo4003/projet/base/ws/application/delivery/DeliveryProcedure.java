package ca.ulaval.glo4003.projet.base.ws.application.delivery;

import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;

public interface DeliveryProcedure {
    void sendPermitId(AddressBook addressBook, String permitId);
}
