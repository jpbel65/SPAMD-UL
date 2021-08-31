package ca.ulaval.glo4003.projet.base.ws.domain.price;

import java.util.Objects;

public class Price {
    private final float value;

    public float getValue() {
        return value;
    }

    public Price(float value) {
        this.value = value;
    }

    public Price add(float f){
        return new Price(value + f);
    }

    public Price add(Price price){
        return new Price(value + price.value);
    }

    public Price subtract(float f){
        return new Price(value - f);
    }

    public Price subtract(Price price){
        return new Price(value - price.value);
    }

    public Price multiply(float f){
        return new Price(value * f);
    }

    public Price multiply(Price price){
        return new Price(value * price.value);
    }

    public Price divide(float f){
        Price price = new Price(value / f);
        if (price.value == Float.POSITIVE_INFINITY || price.value == Float.NEGATIVE_INFINITY)throw new InvalidPrice();
        return price;
    }

    public Price divide(Price price){
        Price p = new Price(value / price.value);
        if (p.value == Float.POSITIVE_INFINITY || p.value == Float.NEGATIVE_INFINITY)throw new InvalidPrice();
        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Price otherPrice = (Price)o;
        return value == (otherPrice.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
