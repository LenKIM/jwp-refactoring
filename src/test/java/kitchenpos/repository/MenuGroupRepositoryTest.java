package kitchenpos.repository;

import kitchenpos.domain.menugroup.MenuGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
class MenuGroupRepositoryTest {

    @Autowired
    MenuGroupRepository menuGroupRepository;

    @Test
    void name() {
        assertThat(menuGroupRepository).isNotNull();
    }

    @Test
    void saveAndFind() {
        MenuGroup menuGroup = MenuGroup.of("half-chicken");
        MenuGroup save = menuGroupRepository.save(menuGroup);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void existTest() {
        MenuGroup menuGroup = MenuGroup.of("half-chicken");
        MenuGroup save = menuGroupRepository.save(menuGroup);

        assertThat(menuGroupRepository.existsById(save.getId())).isTrue();
    }
}