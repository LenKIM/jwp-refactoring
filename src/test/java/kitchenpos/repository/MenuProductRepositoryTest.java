package kitchenpos.repository;

import kitchenpos.domain.menu.MenuProduct;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class MenuProductRepositoryTest {

    @Autowired
    MenuProductRepository menuProductRepository;

    @Test
    void saveAndFind() {
        MenuProduct menuProduct = MenuProduct.of(1L, 10L);
        MenuProduct save = menuProductRepository.save(menuProduct);
        assertThat(save.getSeq()).isNotNull();

        MenuProduct find = menuProductRepository.findBySeq(save.getSeq()).orElseThrow(IllegalArgumentException::new);
        assertThat(save).isEqualTo(find);

    }
}