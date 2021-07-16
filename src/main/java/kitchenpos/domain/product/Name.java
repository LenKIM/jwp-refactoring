package kitchenpos.domain.product;

import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

public class Name {

    @Column("name")
    private String value;

    private Name(String name) {
        this.value = name;
    }

    public static Name of(String value) {
        return new Name(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + value + '\'' +
                '}';
    }
}
