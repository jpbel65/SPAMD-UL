package ca.ulaval.glo4003.projet.base.ws.api.report.price;

import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceDto;
import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceReportService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(PriceReportResource.PRICE_REPORT)
public class PriceReportResource {
    static final String PRICE_REPORT = "/report/price";

    private PriceReportService priceReportService;

    public PriceReportResource(PriceReportService priceReportService) {
        this.priceReportService = priceReportService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/access")
    public PriceDto getAccessPermitPriceReport() {
        return priceReportService.getAccessPermitPriceReport();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/access/{vehicleConsumption}")
    public PriceDto getAccessPermitPriceReportWithVehicleConsumption(@PathParam("vehicleConsumption") String vehicleConsumption) {
        return priceReportService.getAccessPermitPriceReportWithVehicleConsumption(vehicleConsumption);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/parking")
    public PriceDto getParkingPermitPriceReport() {
        return priceReportService.getParkingPermitPriceReport();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contravention")
    public PriceDto getContraventionPriceReport() {
        return priceReportService.getContraventionPriceReport();
    }
}
