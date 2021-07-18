package kitchenpos.domain.order;

import org.springframework.data.relational.core.mapping.Table;

@Table("ORDERS_ORDER_TABLE_REF")
public class OrderTableRef {

    long orderTableId;


    private OrderTableRef(long orderTableId) {
        this.orderTableId = orderTableId;
    }

    public static OrderTableRef of(long orderTableId){
        return new OrderTableRef(orderTableId);
    }

    public long getOrderTableId() {
        return orderTableId;
    }
}
