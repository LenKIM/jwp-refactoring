package kitchenpos.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Objects;

public class Product {
    @Id
    private Long id;

    private Name name;

    private Price price;

    public Product() {
    }

    @PersistenceConstructor
    private Product(Long id, Name name, Price price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product of(Name name, Price price) {
        return new Product(null, name, price);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
