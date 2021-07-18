package kitchenpos.repository;

import kitchenpos.domain.menu.Menu;
import kitchenpos.domain.menu.Price;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void saveAndFind() {

        Menu menu = Menu.of("치킨세트", Price.of(100L), 1L, new HashSet<>());
        Menu save = menuRepository.save(menu);
        assertThat(save.getId()).isNotNull();

        Menu find = menuRepository.findById(save.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(find).isEqualTo(save);
    }
}