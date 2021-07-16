package kitchenpos.application;

import kitchenpos.domain.product.Name;
import kitchenpos.domain.product.Price;
import kitchenpos.domain.product.Product;
import kitchenpos.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    @DisplayName("물건을 등록할 수 있다.")
    void create() {
        Product apple = Product.of(Name.of("apple"), Price.of(10L));
        given(productRepository.save(any())).willReturn(apple);

        Product save = productService.create(apple);
        assertThat(save).isEqualTo(apple);
    }

    @Test
    @DisplayName("물건을 조회할 수 있다")
    void list() {
        given(productRepository.findAll()).willReturn(new ArrayList<>());

        List<Product> list = productService.list();
        assertThat(list).isEmpty();
    }
}