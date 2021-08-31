package ca.ulaval.glo4003.projet.base.ws.application.report.price;

import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermitRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate.SchoolYearDate;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;

public class PriceReportService {
    private PriceAssembler priceAssembler;
    private AccessPermitRepository accessPermitRepository;
    private ParkingPermitRepository parkingPermitRepository;
    private SchoolYearFactory schoolYearFactory;

    public PriceReportService(PriceAssembler priceAssembler,
                              AccessPermitRepository accessPermitRepository,
                              ParkingPermitRepository parkingPermitRepository, SchoolYearFactory schoolYearFactory) {
        this.priceAssembler = priceAssembler;
        this.accessPermitRepository = accessPermitRepository;
        this.parkingPermitRepository = parkingPermitRepository;
        this.schoolYearFactory = schoolYearFactory;
    }

    public PriceDto getAccessPermitPriceReport() {
        Price accessPermitPrice = accessPermitRepository.getTotalPricesForSchoolYear(schoolYearFactory.create());
        return priceAssembler.create(accessPermitPrice);
    }

    public PriceDto getAccessPermitPriceReportWithVehicleConsumption(String vehicleConsumption) {
        Price accessPermitPrice = accessPermitRepository.getTotalPricesForVehicleConsumptionInSchoolYear(
                schoolYearFactory.create(), VehicleConsumption.fromString(vehicleConsumption));
        return priceAssembler.create(accessPermitPrice);
    }

    public PriceDto getParkingPermitPriceReport() {
        Price parkingPermitPrice = parkingPermitRepository.getTotalPricesForSchoolYear(schoolYearFactory.create());
        return priceAssembler.create(parkingPermitPrice);
    }

    private Price getTotalContraventionPrice() {
        SchoolYearDate schoolYearDate = schoolYearFactory.create();
        return parkingPermitRepository.findAll().stream()
            .map(p -> p.getTotalPriceOfContraventionsForSchoolYear(schoolYearDate))
            .reduce(Price::add)
            .orElse(new Price(0));
    }

    public PriceDto getContraventionPriceReport() {
        Price contraventionPrice = getTotalContraventionPrice();
        return priceAssembler.create(contraventionPrice);
    }

    public Price getTotalRevenues() {
        SchoolYearDate schoolYearDate = schoolYearFactory.create();
        Price accessPermitPrice = accessPermitRepository.getTotalPricesForSchoolYear(schoolYearDate);
        Price parkingPermitPrice = parkingPermitRepository.getTotalPricesForSchoolYear(schoolYearDate);
        Price contraventionPrice = getTotalContraventionPrice();
        return contraventionPrice.add(parkingPermitPrice).add(accessPermitPrice);
    }
}
