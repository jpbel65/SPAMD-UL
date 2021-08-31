package ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeDto;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.SustainableMobilityService;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path(InitiativeResource.SUSTAINABLE_MOBILITY)
public class InitiativeResource {
    static final String SUSTAINABLE_MOBILITY = "sustainable-mobility/initiative";

    private SustainableMobilityService sustainableMobilityService;

    public InitiativeResource(SustainableMobilityService sustainableMobilityService){
        this.sustainableMobilityService = sustainableMobilityService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInitiativeFund(InitiativeDto initiativeDto) {
        Initiative initiative = sustainableMobilityService.createInitiative(initiativeDto);
        return Response.created(URI.create(SUSTAINABLE_MOBILITY +"/"+ initiative.getCode())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{code}")
    public Response addToInitiativeFund(@PathParam("code") String code, InitiativeDto initiativeDto) {
        sustainableMobilityService.addFundsToInitiative(code, initiativeDto.cost);
        return Response.status(200).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<InitiativeDto> getInitiativeList() {
        return sustainableMobilityService.getInitiatives();
    }
}
