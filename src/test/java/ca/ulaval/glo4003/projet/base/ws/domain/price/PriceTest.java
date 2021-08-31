package ca.ulaval.glo4003.projet.base.ws.domain.price;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceTest {

    private final float ANY_FLOAT = 15;
    private final float ANY_FLOAT_AGAIN = 22;
    private final float ZERO_FLOAT = 0;
    private final Price ANY_PRICE = new Price(ANY_FLOAT);
    private final Price ANY_PRICE_AGAIN = new Price(ANY_FLOAT_AGAIN);
    private final Price ZERO_PRICE = new Price(ZERO_FLOAT);

    @Test
    public void whenAddFloatToPrice_thenReturnSumOfFloatInPrice() {
        Price price = ANY_PRICE.add(ANY_FLOAT_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT+ANY_FLOAT_AGAIN), price);
    }

    @Test
    public void whenAddPriceToPrice_thenReturnSumOfFloatInPrice() {
        Price price = ANY_PRICE.add(ANY_PRICE_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT_AGAIN+ANY_FLOAT), price);
    }

    @Test
    public void whenSubtractFloatToPrice_thenReturnSubtractOfFloatInPrice() {
        Price price = ANY_PRICE.subtract(ANY_FLOAT_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT-ANY_FLOAT_AGAIN), price);
    }

    @Test
    public void whenSubtractPriceToPrice_thenReturnSubtractOfFloatInPrice() {
        Price price = ANY_PRICE.subtract(ANY_PRICE_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT-ANY_FLOAT_AGAIN), price);
    }

    @Test
    public void whenMultiplyFloatToPrice_thenReturnMultiplyOfFloatInPrice() {
        Price price = ANY_PRICE.multiply(ANY_FLOAT_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT*ANY_FLOAT_AGAIN), price);
    }

    @Test
    public void whenMultiplyPriceToPrice_thenReturnMultiplyOfFloatInPrice() {
        Price price = ANY_PRICE.multiply(ANY_PRICE_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT_AGAIN*ANY_FLOAT), price);
    }

    @Test
    public void whenDivideFloatToPrice_thenReturnDivideOfFloatInPrice() {
        Price price = ANY_PRICE.divide(ANY_FLOAT_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT/ANY_FLOAT_AGAIN), price);
    }

    @Test
    public void whenDividePriceToPrice_thenReturnDivideOfFloatInPrice() {
        Price price = ANY_PRICE.divide(ANY_PRICE_AGAIN);

        Assert.assertEquals(new Price(ANY_FLOAT/ANY_FLOAT_AGAIN), price);
    }

    @Test(expected = InvalidPrice.class)
    public void whenDivideByZeroInFloat_thenErrorIsRaise(){
        ANY_PRICE.divide(ZERO_FLOAT);
    }

    @Test(expected = InvalidPrice.class)
    public void whenDivideByZeroInPrice_thenErrorIsRaise(){
        ANY_PRICE.divide(ZERO_PRICE);
    }
}
