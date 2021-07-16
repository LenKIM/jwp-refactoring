package kitchenpos.repository;

import kitchenpos.domain.tablegroup.TableGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TableGroupRepository extends CrudRepository<TableGroup, Long> {

    <S extends TableGroup> S save(S entity);

    Optional<TableGroup> findById(Long id);

    default TableGroup findTableGroup(Long id){
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }

    List<TableGroup> findAll();
}
