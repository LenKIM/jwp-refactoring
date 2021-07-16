package kitchenpos.repository;

import kitchenpos.domain.ordertable.OrderTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderTableRepository extends CrudRepository<OrderTable, Long> {

    <S extends OrderTable> S save(S entity);

    Optional<OrderTable> findById(Long id);

    default OrderTable findOrderTableById(Long id) {
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }

    List<OrderTable> findAll();

    Set<OrderTable> findAllByIdIn(List<Long> orderTableIds);
}
