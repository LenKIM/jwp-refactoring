package kitchenpos.domain.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

public class OrderLineItem {

    @Id
    private Long seq;

    @Column("ORDERS_LINE_ITEM_SEQ")
    private MenuRef menuRef;

    private long quantity;

    private OrderLineItem(Long seq, MenuRef menuRef, long quantity) {
        this.seq = seq;
        this.menuRef = menuRef;
        this.quantity = quantity;
    }

    public static OrderLineItem of(MenuRef menuRef, long quantity){
        return new OrderLineItem(null, menuRef, quantity);
    }

    public Long getSeq() {
        return seq;
    }

    public MenuRef getMenuRef() {
        return menuRef;
    }

    public long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineItem that = (OrderLineItem) o;
        return quantity == that.quantity && Objects.equals(seq, that.seq) && Objects.equals(menuRef, that.menuRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, menuRef, quantity);
    }

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "seq=" + seq +
                ", menuRef=" + menuRef +
                ", quantity=" + quantity +
                '}';
    }
}
