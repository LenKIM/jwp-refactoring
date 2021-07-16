package kitchenpos.repository;

import kitchenpos.domain.product.Name;
import kitchenpos.domain.product.Price;
import kitchenpos.domain.product.Product;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

// @DataJdbcTest 는 Custom Config 를 Import 하지 못한다.
@DataJdbcTest
@Import(value = DataJdbcConfig.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void notNull() {
        assertThat(productRepository).isNotNull();
    }

    @Test
    void saveAndFind() {
        Product apple = Product.of(Name.of("apple"), Price.of(BigDecimal.ONE));
        Product save = productRepository.save(apple);
        assertThat(save.getId()).isNotNull();

        Product product = productRepository.findProductById(save.getId());
        assertThat(save).isEqualTo(product);
    }
}