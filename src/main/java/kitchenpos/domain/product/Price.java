package kitchenpos.domain.product;

import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
    @Column("price")
    private BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value;
    }

    public static Price of(Long value){
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price of(BigDecimal value){
        return new Price(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
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
}
