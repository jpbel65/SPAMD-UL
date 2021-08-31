package ca.ulaval.glo4003.projet.base;

import ca.ulaval.glo4003.projet.base.ws.api.mappers.InvalidDateInfractionMapper;
import ca.ulaval.glo4003.projet.base.ws.api.mappers.InvalidFieldExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.api.mappers.ResourceNotFoundExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.api.mappers.TimeParserExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.api.parkingUsage.ParkingUsageResource;
import ca.ulaval.glo4003.projet.base.ws.api.permit.access.AccessPermitResource;
import ca.ulaval.glo4003.projet.base.ws.api.permit.parking.ParkingPermitResource;
import ca.ulaval.glo4003.projet.base.ws.api.report.price.PriceReportResource;
import ca.ulaval.glo4003.projet.base.ws.api.report.usage.UsageReportResource;
import ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility.InitiativeBudgetResource;
import ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility.InitiativeResource;
import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestService;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedureFactory;
import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionService;
import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageService;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitService;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ContraventionAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitService;
import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearFactory;
import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceReportService;
import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageByZoneBuilder;
import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageReportFactory;
import ca.ulaval.glo4003.projet.base.ws.application.report.usage.UsageReportService;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeBudgetAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.SustainableMobilityService;
import ca.ulaval.glo4003.projet.base.ws.application.user.UserAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.vehicle.VehicleAssembler;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.DeliveryMode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;
import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsageRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.NullParkingPermitFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidator;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidatorInitializer;
import ca.ulaval.glo4003.projet.base.ws.domain.price.ParkingPriceRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudgetFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumptionRepository;
import ca.ulaval.glo4003.projet.base.ws.externResource.CsvReader;
import ca.ulaval.glo4003.projet.base.ws.http.CORSResponseFilter;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery.EmailDelivery;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery.PostalDelivery;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery.SSPDelivery;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.infraction.InfractionStorageInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.parkingUsage.ParkingUsageRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.access.AccessPermitRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.permit.parking.ParkingPermitRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.price.ParkingPriceRepositoryImpl;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.sustainableMobility.InitiativeRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.vehicle.VehicleConsumptionRepositoryImpl;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class ProjectConfig extends ResourceConfig {
    private final static String PATH_OF_CONSUMPTION_FEES = "src/main/java/ca/ulaval/glo4003/projet/base/ws/externResource/frais-acces.csv";
    private final static String PATH_OF_ZONE_FEES = "src/main/java/ca/ulaval/glo4003/projet/base/ws/externResource/frais-zone.csv";
    private final static float INITIATIVE_BUDGET_PORTION = 40;

    public ProjectConfig() {
        registerResources();
        registerExceptionMappers();
        register(CORSResponseFilter.class);
    }

    private void registerResources() {
        CsvReader consumptionCsvReader = new CsvReader(PATH_OF_CONSUMPTION_FEES);
        CsvReader zoneCsvReader = new CsvReader(PATH_OF_ZONE_FEES);
        InfractionStorage infractionStorage = new InfractionStorageInMemory();
        PermitValidatorInitializer permitValidatorInitializer = new PermitValidatorInitializer(infractionStorage);
        PermitValidator permitValidator =  permitValidatorInitializer.createPermitValidatorChain();

        AccessPermitRepositoryInMemory accessPermitRepository = new AccessPermitRepositoryInMemory();
        ParkingPermitRepository parkingPermitRepository = new ParkingPermitRepositoryInMemory();
        ParkingUsageRepository parkingUsageRepository = new ParkingUsageRepositoryInMemory();
        VehicleConsumptionRepository vehicleConsumptionRepository = new VehicleConsumptionRepositoryImpl(consumptionCsvReader);
        ParkingPriceRepository parkingPriceRepository = new ParkingPriceRepositoryImpl(zoneCsvReader);
        InitiativeRepository initiativeRepository = new InitiativeRepositoryInMemory();

        DeliveryProcedureFactory deliveryProcedureFactory = new DeliveryProcedureFactory(createDeliveryProcedures());
        IdGenerator idGenerator = new IdGenerator();

        AccessPermitAssembler accessPermitAssembler = new AccessPermitAssembler(new UserAssembler(idGenerator), new VehicleAssembler(idGenerator), deliveryProcedureFactory, idGenerator);
        ParkingPermitAssembler parkingPermitAssembler = new ParkingPermitAssembler(deliveryProcedureFactory, idGenerator);
        ContraventionAssembler contraventionAssembler = new ContraventionAssembler(new InfractionAssembler(), idGenerator);
        AccessRequestAssembler accessRequestAssembler = new AccessRequestAssembler();
        ParkingUsageAssembler parkingUsageAssembler = new ParkingUsageAssembler();
        InitiativeAssembler initiativeAssembler = new InitiativeAssembler();
        InitiativeBudgetAssembler initiativeBudgetAssembler = new InitiativeBudgetAssembler();
        PriceAssembler priceAssembler = new PriceAssembler();
        SchoolYearFactory schoolYearFactory = new SchoolYearFactory();

        InitiativeBudgetFactory initiativeBudgetFactory = new InitiativeBudgetFactory();

        InfractionService infractionService = new InfractionService(infractionStorage);
        ParkingUsageService parkingUsageService = new ParkingUsageService(parkingUsageAssembler, parkingUsageRepository);
        NullParkingPermitFactory nullParkingPermitFactory = new NullParkingPermitFactory();
        ParkingPermitService parkingPermitService = new ParkingPermitService(parkingPermitRepository, parkingPermitAssembler, parkingPriceRepository, contraventionAssembler, permitValidator, nullParkingPermitFactory, infractionService);

        AccessRequestService accessRequestService = new AccessRequestService(accessRequestAssembler, accessPermitRepository);
        AccessPermitService accessPermitService = new AccessPermitService(accessPermitAssembler, accessPermitRepository, vehicleConsumptionRepository);
        PriceReportService priceReportService = new PriceReportService(priceAssembler, accessPermitRepository, parkingPermitRepository, schoolYearFactory);
        SustainableMobilityService sustainableMobilityService = new SustainableMobilityService(INITIATIVE_BUDGET_PORTION, initiativeRepository, initiativeAssembler, initiativeBudgetFactory, initiativeBudgetAssembler, priceReportService);

        AccessPermitResource accessPermitResource = new AccessPermitResource(accessPermitService, accessRequestService);
        ParkingPermitResource parkingPermitResource = new ParkingPermitResource(parkingPermitService);
        ParkingUsageResource parkingUsageResource = new ParkingUsageResource(parkingUsageService);
        PriceReportResource priceReportResource = new PriceReportResource(priceReportService);
        InitiativeResource initiativeResource = new InitiativeResource(sustainableMobilityService);
        InitiativeBudgetResource initiativeBudgetResource = new InitiativeBudgetResource(sustainableMobilityService);

        UsageByZoneBuilder usageReportBuilder = new UsageByZoneBuilder();
        UsageReportFactory usageReportFactory = new UsageReportFactory(usageReportBuilder);
        UsageReportService usageReportService = new UsageReportService(parkingUsageRepository, usageReportFactory, schoolYearFactory);
        UsageReportResource usageReportResource = new UsageReportResource(usageReportService);

        register(new Root());
        register(accessPermitResource);
        register(parkingPermitResource);
        register(usageReportResource);
        register(parkingUsageResource);
        register(priceReportResource);
        register(initiativeResource);
        register(initiativeBudgetResource);
    }

    private Map<DeliveryMode, DeliveryProcedure> createDeliveryProcedures() {
        Map<DeliveryMode, DeliveryProcedure> procedures = new HashMap<>();
        procedures.put(DeliveryMode.SNAIL_MAIL, new PostalDelivery());
        procedures.put(DeliveryMode.EMAIL, new EmailDelivery());
        procedures.put(DeliveryMode.SSP_OFFICE, new SSPDelivery());
        return procedures;
    }

    private void registerExceptionMappers() {
        register(new InvalidDateInfractionMapper());
        register(new ResourceNotFoundExceptionMapper());
        register(new InvalidFieldExceptionMapper());
        register(new TimeParserExceptionMapper());
    }

    @Path("/")
    public static class Root {
        @GET
        public Response ping() {
            return Response.ok("SPAMD-UL Api is alive", MediaType.TEXT_PLAIN).build();
        }
    }
}
