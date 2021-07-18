package kitchenpos.application;

import kitchenpos.domain.order.Order;
import kitchenpos.domain.order.OrderLineItem;
import kitchenpos.domain.order.OrderStatus;
import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.repository.MenuRepository;
import kitchenpos.repository.OrderLineItemRepository;
import kitchenpos.repository.OrderRepository;
import kitchenpos.repository.OrderTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemDao;
    private final OrderTableRepository orderTableRepository;

    public OrderService(
            final MenuRepository menuRepository,
            final OrderRepository orderRepository,
            final OrderLineItemRepository orderLineItemDao,
            final OrderTableRepository orderTableRepository
    ) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.orderLineItemDao = orderLineItemDao;
        this.orderTableRepository = orderTableRepository;
    }

    @Transactional
    public Order create(final Order order) {
        Set<OrderLineItem> orderLineItems = order.getOrderLineItems();

        if (CollectionUtils.isEmpty(orderLineItems)) {
            throw new IllegalArgumentException("should have orderLineItems");
        }

        // TODO DomainEvent
        final List<Long> menuIds = orderLineItems.stream()
                .map(orderLineItem -> orderLineItem
                        .getMenuRef()
                        .getMenuId())
                .collect(Collectors.toList());

        if (orderLineItems.size() != menuRepository.countByIdIn(menuIds)) {
            throw new IllegalArgumentException("Not Match Size");
        }

        final OrderTable orderTable = orderTableRepository.findOrderTableById(order.getOrderTableRef().getOrderTableId());

        if (orderTable.isEmpty()) {
            throw new IllegalArgumentException("Should have not orderTable empty");
        }



        return orderRepository.save(order);
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order changeOrderStatus(final Long orderId, final OrderStatus orderStatus) {
        final Order find = orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);

        if (Objects.equals(OrderStatus.COMPLETION.name(), find.getOrderStatus().name())) {
            throw new IllegalArgumentException("Invalid OrderStatus");
        }

        find.changeStatus(orderStatus);

        return orderRepository.save(find);
    }
}
