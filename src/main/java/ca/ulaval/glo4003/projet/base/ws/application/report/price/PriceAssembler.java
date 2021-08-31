package ca.ulaval.glo4003.projet.base.ws.application.report.price;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public class PriceAssembler {

    public PriceDto create(Price price){
        PriceDto priceDto = new PriceDto();
        priceDto.price = price.getValue();
        return priceDto;
    }
}
