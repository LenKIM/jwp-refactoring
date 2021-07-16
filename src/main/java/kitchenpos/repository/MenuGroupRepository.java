package kitchenpos.repository;

import kitchenpos.domain.MenuGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuGroupRepository extends CrudRepository<MenuGroup, Long> {

    MenuGroup save(MenuGroup entity);

    Optional<MenuGroup> findById(Long id);

    List<MenuGroup> findAll();

    boolean existsById(Long id);
}
