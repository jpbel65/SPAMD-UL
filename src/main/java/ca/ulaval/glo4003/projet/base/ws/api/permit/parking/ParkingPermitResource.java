package ca.ulaval.glo4003.projet.base.ws.api.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ContraventionDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path(ParkingPermitResource.PARKING_PERMIT)
public class ParkingPermitResource {
    static final String PARKING_PERMIT = "parking/permit";
    static final String CONTRAVENTION = "contravention";

    private final ParkingPermitService parkingPermitService;

    public ParkingPermitResource(ParkingPermitService parkingPermitService) {
        this.parkingPermitService = parkingPermitService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createParkingPermit(ParkingPermitDto parkingPermitDto) {
        ParkingPermit parkingPermit = parkingPermitService.createParkingPermit(parkingPermitDto);
        return Response.created(URI.create(PARKING_PERMIT+"/"+ parkingPermit.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public ParkingPermitDto getParkingPermit(@PathParam("id") String id) {
        return parkingPermitService.findParkingPermit(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/verify")
    public Response verify(@PathParam("id") String id, ContraventionDto verificationInfo) {
        ContraventionDto contraventionDto = parkingPermitService.verifyPermit(id, verificationInfo);
        return Response
            .created(URI.create(PARKING_PERMIT + "/" + id + "/"+ CONTRAVENTION + "/" + contraventionDto.id))
            .entity(contraventionDto)
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{permitId}/" + CONTRAVENTION + "/{contraventionId}")
    public ContraventionDto getContravention(@PathParam("permitId") String permitId, @PathParam("contraventionId") String contraventionId) {
        return parkingPermitService.findContraventionById(permitId, contraventionId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{permitId}/" + CONTRAVENTION + "/{contraventionId}/pay")
    public Response payContravention(@PathParam("permitId") String permitId, @PathParam("contraventionId") String contraventionId) {
        parkingPermitService.payContravention(permitId, contraventionId);
        return Response.status(200).build();
    }
}
