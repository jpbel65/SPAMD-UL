package ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeBudgetDto;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.SustainableMobilityService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(InitiativeBudgetResource.SUSTAINABLE_MOBILITY_BUDGET)
public class InitiativeBudgetResource {
    static final String SUSTAINABLE_MOBILITY_BUDGET = "/sustainable-mobility/budget";

    private SustainableMobilityService sustainableMobilityService;

    public InitiativeBudgetResource(SustainableMobilityService sustainableMobilityService) {
        this.sustainableMobilityService = sustainableMobilityService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePercentForInitiatives(InitiativeBudgetDto initiativeBudgetDto) {
        sustainableMobilityService.setPercentForInitiatives(initiativeBudgetDto.percentForInitiatives);
        return Response.status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public InitiativeBudgetDto getInitiativeBudget() {
        return sustainableMobilityService.getBudget();
    }
}
