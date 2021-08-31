package ca.ulaval.glo4003.projet.base.ws.application.report.price;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceAssemblerTest {
    private Price price;
    private final float ANY_FLOAT = 13;
    private PriceAssembler priceAssembler;

    @Before
    public void setUp() {
        priceAssembler = new PriceAssembler();
        price = new Price(ANY_FLOAT);
    }

    @Test
    public void whenCreatePriceDto_thenValueOfPriceDtoIsEqualToValueOfPrice() {
        PriceDto priceDto = priceAssembler.create(price);

        assertEquals(ANY_FLOAT, priceDto.price, 0);
    }
}
