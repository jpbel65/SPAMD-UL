package ca.ulaval.glo4003.projet.base.ws.application.accessRequest;

import ca.ulaval.glo4003.projet.base.ws.domain.access.AccessRequest;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;

public class AccessRequestService {
    private final AccessRequestAssembler accessRequestAssembler;
    private final AccessPermitRepository accessPermitRepository;

    public AccessRequestService(AccessRequestAssembler accessRequestAssembler, AccessPermitRepository accessPermitRepository) {
        this.accessRequestAssembler = accessRequestAssembler;
        this.accessPermitRepository = accessPermitRepository;
    }

    public void createAccessRequest(AccessRequestDto accessRequestDto) {
        AccessRequest accessRequest = accessRequestAssembler.create(accessRequestDto);
        AccessPermit accessPermit = accessPermitRepository.findByAccessCode(accessRequest.getAccessCode());
        accessRequest.verify(accessPermit);
    }
}
