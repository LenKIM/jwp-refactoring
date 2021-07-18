package kitchenpos.repository;

import kitchenpos.domain.menugroup.MenuGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuGroupRepository extends CrudRepository<MenuGroup, Long> {

    <S extends MenuGroup> S save(S entity);

    Optional<MenuGroup> findById(Long id);

    default MenuGroup findMenuGroupById(Long id){
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }

    List<MenuGroup> findAll();

    boolean existsById(Long id);
}
