package kitchenpos.domain.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table("ORDERS")
public class Order {

    @Id
    private Long id;

    @Column("ORDERS_ID")
    private OrderTableRef orderTable;

    private OrderStatus orderStatus;
    private LocalDateTime orderedTime;

    @Column("ORDERS_ID")
    private Set<OrderLineItem> orderLineItems;

    @PersistenceConstructor
    private Order(Long id, OrderTableRef orderTable, OrderStatus orderStatus, LocalDateTime orderedTime, Set<OrderLineItem> orderLineItems) {
        this.id = id;
        this.orderTable = orderTable;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.orderLineItems = orderLineItems;
    }

    public static Order of(OrderTableRef orderTable, OrderStatus orderStatus, LocalDateTime orderedTime, Set<OrderLineItem> orderLineItems) {
        return new Order(null, orderTable, orderStatus, orderedTime, orderLineItems);
    }

    public Long getId() {
        return id;
    }

    public OrderTableRef getOrderTableRef() {
        return orderTable;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public Set<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
}
