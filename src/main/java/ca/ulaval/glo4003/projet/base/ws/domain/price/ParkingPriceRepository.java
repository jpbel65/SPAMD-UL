package ca.ulaval.glo4003.projet.base.ws.domain.price;

public interface ParkingPriceRepository {
    Price getZonePriceForPeriod(String zone, String period);
}
