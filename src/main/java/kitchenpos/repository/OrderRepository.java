package kitchenpos.repository;

import kitchenpos.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order save(Order entity);

    Optional<Order> findById(Long id);

    List<Order> findAll();

//    boolean existsByOrderTableRefAndOrderStatusIn(Long orderTableId, List<String> orderStatuses);

//    boolean existsByOrderTableIdInAndOrderStatusIn(List<Long> orderTableIds, List<String> orderStatuses);

}
