package kitchenpos.repository;

import kitchenpos.domain.order.OrderLineItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {

    <S extends OrderLineItem> S save(S entity);

    Optional<OrderLineItem> findById(Long id);

    List<OrderLineItem> findAll();
}
