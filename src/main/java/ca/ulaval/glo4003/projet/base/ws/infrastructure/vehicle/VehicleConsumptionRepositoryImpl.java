package ca.ulaval.glo4003.projet.base.ws.infrastructure.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumptionRepository;
import ca.ulaval.glo4003.projet.base.ws.externResource.CsvReader;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.VehicleConsumptionNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.PeriodNotFoundException;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.PriceNotFoundException;

import java.util.HashMap;
import java.util.Optional;

public class VehicleConsumptionRepositoryImpl implements VehicleConsumptionRepository {

    private HashMap<String, Integer> vehicleConsumptionHeader;
    private HashMap<String, Integer> periodHeader;
    private Price[][] prices;

    public VehicleConsumptionRepositoryImpl(CsvReader csvReader) {
        this.vehicleConsumptionHeader = csvReader.getLinesHeaders();
        this.periodHeader = csvReader.getColumnsHeaders();
        prices = new Price[this.vehicleConsumptionHeader.size()][this.periodHeader.size()];
        for (int i = 0; i < csvReader.getContents().length; i++) {
            for (int j = 0; j < csvReader.getContents()[i].length; j++) {
                prices[i][j] = new Price(Float.parseFloat(csvReader.getContents()[i][j]));
            }
        }
    }

    private void verifyPriceForVehicleConsumptionExists(String vehicleConsumption) {
        Optional.ofNullable(vehicleConsumptionHeader.get(vehicleConsumption.toUpperCase()))
                .orElseThrow(()-> new PriceNotFoundException(vehicleConsumption));
    }

    private void verifyPriceForAccessPeriodExists(String period) {
        Optional.ofNullable(periodHeader.get(period.toUpperCase()))
                .orElseThrow(()-> new PriceNotFoundException(period));
    }

    @Override
    public Price getVehicleConsumptionPriceForAccessPeriod(String vehicleConsumption, String period) {
        verifyPriceForVehicleConsumptionExists(vehicleConsumption);
        verifyPriceForAccessPeriodExists(period);
        return prices[vehicleConsumptionHeader
                .get(vehicleConsumption.toUpperCase())][periodHeader.get(period.toUpperCase())];
    }
}
