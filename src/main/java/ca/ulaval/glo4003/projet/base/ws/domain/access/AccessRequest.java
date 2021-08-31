package ca.ulaval.glo4003.projet.base.ws.domain.access;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessType;

import java.time.LocalDateTime;

public class AccessRequest {
    private final String accessCode;
    private final AccessType accessType;
    private final LocalDateTime date;

    public AccessRequest(String accessCode, AccessType accessType, LocalDateTime date) {
        this.accessCode = accessCode;
        this.accessType = accessType;
        this.date = date;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void verify(AccessPermit accessPermit) {
        accessPermit.verifyDateTime(date);
        accessPermit.verifyType(accessType);
    }
}
