package ca.ulaval.glo4003.projet.base.ws.domain.Id;

import java.util.UUID;

public class IdGenerator {
    public String generateId(){
        return UUID.randomUUID().toString();
    }
}
