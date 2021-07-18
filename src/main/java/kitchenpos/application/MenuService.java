package kitchenpos.application;

import kitchenpos.domain.menu.Menu;
import kitchenpos.domain.menu.MenuProduct;
import kitchenpos.domain.menu.Price;
import kitchenpos.domain.product.Product;
import kitchenpos.repository.MenuGroupRepository;
import kitchenpos.repository.MenuRepository;
import kitchenpos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;

    public MenuService(
            final MenuRepository menuRepository,
            final MenuGroupRepository menuGroupRepository,
            final ProductRepository productRepository
    ) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Menu create(final Menu menu) {
        final Price price = menu.getPrice();

        if (menuGroupRepository.existsById(menu.getMenuGroupId())) {
            throw new IllegalArgumentException();
        }

        final Set<MenuProduct> menuProducts = menu.getMenuProducts();

        // TODO 도메인 이벤트를 통한 관심사 분리 를 적용해보면 어떠할까? @DomainEvents 활용하기
        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProduct menuProduct : menuProducts) {
            final Product product = productRepository.findProductById(menuProduct.getProductId());
            BigDecimal multiply = product.getMultiplyPrice(menuProduct.getQuantity());
            sum = sum.add(multiply);
        }

        if (price.getValue().compareTo(sum) > 0) {
            throw new IllegalArgumentException("Price");
        }

        return menuRepository.save(menu);
    }

    public List<Menu> list() {
        return menuRepository.findAll();
    }
}
