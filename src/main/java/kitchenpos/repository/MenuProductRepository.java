package kitchenpos.repository;

import kitchenpos.domain.menu.MenuProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MenuProductRepository extends CrudRepository<MenuProduct, Long> {
    MenuProduct save(MenuProduct entity);

    Optional<MenuProduct> findBySeq(Long id);

    List<MenuProduct> findAll();

//    Set<MenuProduct> findAllByMenuId(Long menuId);
}
