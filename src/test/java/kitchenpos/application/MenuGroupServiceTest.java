package kitchenpos.application;

import kitchenpos.domain.MenuGroup;
import kitchenpos.repository.MenuGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class MenuGroupServiceTest {

    @Autowired
    private MenuGroupService menuGroupService;

    @MockBean
    private MenuGroupRepository menuGroupRepository;

    @Test
    @DisplayName("이름을 가진 MenuGroup 을 생성한다.")
    void createTest() {
        MenuGroup menuGroup = MenuGroup.of("half-chicken");
        given(menuGroupRepository.save(menuGroup)).willReturn(menuGroup);

        MenuGroup save = menuGroupService.create(menuGroup);
        assertThat(save).isEqualTo(menuGroup);
    }

    @Test
    @DisplayName("복수의 MenuGroup 을 조회할 수 있다.")
    void listTest() {
        MenuGroup menuGroup = MenuGroup.of("half-chicken");
        given(menuGroupRepository.findAll()).willReturn(new ArrayList<>());

        List<MenuGroup> findMenuGroups = menuGroupService.list();
        assertThat(findMenuGroups).isEmpty();
    }

    @Configuration
    @Import(MenuGroupService.class)
    static class Config {
    }
}