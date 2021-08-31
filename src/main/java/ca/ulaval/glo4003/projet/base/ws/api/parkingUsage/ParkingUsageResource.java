package ca.ulaval.glo4003.projet.base.ws.api.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageDto;
import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ParkingUsageResource.PARKING_USAGE)
public class ParkingUsageResource {
    static final String PARKING_USAGE = "parking/usage";

    private ParkingUsageService parkingUsageService;

    public ParkingUsageResource(ParkingUsageService parkingUsageService) {
        this.parkingUsageService = parkingUsageService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addParkingUsage(ParkingUsageDto parkingUsageDto) {
        parkingUsageService.saveParkingUsage(parkingUsageDto);
        return Response.status(201).build();
    }
}
