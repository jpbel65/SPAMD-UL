package ca.ulaval.glo4003.projet.base.ws.infrastructure.price;

import ca.ulaval.glo4003.projet.base.ws.domain.price.ParkingPriceRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.externResource.CsvReader;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.PriceNotFoundException;

import java.util.HashMap;
import java.util.Optional;

public class ParkingPriceRepositoryImpl implements ParkingPriceRepository {

  private HashMap<String, Integer> periods;
  private HashMap<String, Integer> zones;
  private Price[][] prices;

  public ParkingPriceRepositoryImpl(CsvReader csvReader) {
    this.zones = csvReader.getLinesHeaders();
    this.periods = csvReader.getColumnsHeaders();
    prices = new Price[this.zones.size()][this.periods.size()];
    for (int i = 0; i < csvReader.getContents().length; i++) {
      for (int j = 0; j < csvReader.getContents()[i].length; j++) {
        prices[i][j] = new Price(Float.parseFloat(csvReader.getContents()[i][j]));
      }
    }
  }

  private void verifyZoneExists(String zone) {
    Optional.ofNullable(zones.get(zone.toUpperCase()))
        .orElseThrow(()-> new PriceNotFoundException(zone));
  }

  private void verifyPeriodExists(String period) {
    Optional.ofNullable(periods.get(period.toUpperCase()))
        .orElseThrow(()-> new PriceNotFoundException(period));
  }

  @Override
  public Price getZonePriceForPeriod(String zone, String period) {
    verifyZoneExists(zone);
    verifyPeriodExists(period);
    return prices[zones
        .get(zone.toUpperCase())][periods.get(period.toUpperCase())];
  }
}
