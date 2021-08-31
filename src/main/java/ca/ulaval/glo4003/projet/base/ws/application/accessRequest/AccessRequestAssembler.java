package ca.ulaval.glo4003.projet.base.ws.application.accessRequest;

import ca.ulaval.glo4003.projet.base.ws.domain.access.AccessRequest;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessType;

import java.time.LocalDateTime;

public class AccessRequestAssembler {

    public AccessRequest create(AccessRequestDto accessRequestDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        AccessType accessType = AccessType.fromString(accessRequestDto.accessType);
        return new AccessRequest(accessRequestDto.accessCode, accessType, localDateTime);
    }
}
