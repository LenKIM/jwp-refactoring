package kitchenpos.application;

import kitchenpos.domain.menu.Menu;
import kitchenpos.domain.menu.MenuProduct;
import kitchenpos.domain.menu.Price;
import kitchenpos.domain.menugroup.MenuGroup;
import kitchenpos.domain.product.Name;
import kitchenpos.domain.product.Product;
import kitchenpos.repository.MenuGroupRepository;
import kitchenpos.repository.MenuRepository;
import kitchenpos.repository.ProductRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MenuGroupRepository menuGroupRepository;

    @InjectMocks
    private MenuService menuService;

    private final static long ANY_MENU_ID = 1L;
    private final static long ANY_MENU_GROUP_ID = 1L;
    private final static long ANY_PRODUCT_ID = 1L;

    private MenuGroup menuGroup;
    private Menu menu;
    private Product dummyProduct;

    @BeforeEach
    void setUp() {
        menuGroup = MenuGroup.of("menuGroupName");
        ReflectionTestUtils.setField(menuGroup, "id", ANY_MENU_GROUP_ID);

        menu = Menu.of("tomato pasta", Price.of(BigDecimal.ZERO), 1L, new HashSet<>());

    }

    @Test
    @DisplayName("메뉴를 등록할 수 잇다.")
    void create_test() {
        given(menuGroupRepository.existsById(menuGroup.getId())).willReturn(false);
        given(menuRepository.save(menu)).willReturn(menu);

        menuService.create(menu);

        verify(menuRepository).save(menu);
    }

    @Test
    @DisplayName("메뉴를 등록하는 시점에 메뉴 그룹(MENU_GROUP)이 미리 등록되어 있어야 한다.")
    void menuGroup() {
        given(menuGroupRepository.existsById(menuGroup.getId())).willReturn(true);

        assertThatThrownBy(() -> menuService.create(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 전체 가격이 메뉴그룹의 가격 전체 합보다 높을 경우 등록될 수 없다.")
    void price() {
        dummyProduct = Product.of(Name.of("rice"), kitchenpos.domain.product.Price.of(BigDecimal.valueOf(10)));
        ReflectionTestUtils.setField(dummyProduct, "id", ANY_PRODUCT_ID);

        given(menuGroupRepository.existsById(menuGroup.getId())).willReturn(false);
        given(productRepository.findProductById(1L)).willReturn(dummyProduct);

        menu = Menu.of("tomato pasta sets", Price.of(BigDecimal.valueOf(100L)), ANY_MENU_GROUP_ID,
                Sets.newLinkedHashSet(MenuProduct.of(ANY_PRODUCT_ID, 1)));

        assertThatThrownBy(() -> menuService.create(menu))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Price");
    }
}