package kitchenpos.domain.tablegroup;

import kitchenpos.domain.ordertable.OrderTable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TableGroup {
    @Id
    private Long id;

    @Column("TABLE_GROUP_ID")
    private Set<OrderTableRef> orderTables;

    private LocalDateTime createdDate;

    public TableGroup() {
    }

    private TableGroup(Long id, LocalDateTime createdDate, Set<OrderTableRef> orderTables) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTables = orderTables;
    }

    public static TableGroup of(Set<OrderTableRef> orderTables) {
        return new TableGroup(null, LocalDateTime.now(), orderTables);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Set<OrderTableRef> getOrderTables() {
        return orderTables;
    }

    public void setOrderTables(final Set<OrderTableRef> orderTables) {
        this.orderTables = orderTables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableGroup that = (TableGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(orderTables, that.orderTables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderTables);
    }

    @Override
    public String toString() {
        return "TableGroup{" +
                "id=" + id +
                ", orderTables=" + orderTables +
                ", createdDate=" + createdDate +
                '}';
    }
}
