package ca.ulaval.glo4003.projet.base.ws.api.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestDto;
import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestService;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path(AccessPermitResource.PARKING_ACCESS)
public class AccessPermitResource {
    static final String PARKING_ACCESS = "parking/access";

    private final AccessPermitService accessPermitService;
    private final AccessRequestService accessRequestService;

    public AccessPermitResource(AccessPermitService accessPermitService,
                                AccessRequestService accessRequestService) {
        this.accessPermitService = accessPermitService;
        this.accessRequestService = accessRequestService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AccessPermitDto request) {
        AccessPermit accessPermit = accessPermitService.create(request);

        return Response.created(URI.create(PARKING_ACCESS +"/"+ accessPermit.getId())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("request")
    public Response accessRequest(AccessRequestDto request) {
        accessRequestService.createAccessRequest(request);
        return Response.status(200).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public AccessPermitDto getAccessPermit(@PathParam("id") String id) {
        return accessPermitService.findAccessPermit(id);
    }
}
