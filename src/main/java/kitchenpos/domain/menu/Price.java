package kitchenpos.domain.menu;

import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {

    @Column("price")
    private BigDecimal value;

    private Price(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public static Price of(BigDecimal price){
        return new Price(price);
    }

    public static Price of(Long price){
        return new Price(BigDecimal.valueOf(price));
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }
}
