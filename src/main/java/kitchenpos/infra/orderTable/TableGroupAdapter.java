package kitchenpos.infra.orderTable;

import kitchenpos.domain.orderTable.SafeTableGroup;
import kitchenpos.domain.orderTable.exceptions.InvalidTryChangeEmptyException;
import kitchenpos.domain.tableGroup.TableGroup;
import kitchenpos.domain.tableGroup.TableGroupRepository;
import org.springframework.stereotype.Component;

@Component
public class TableGroupAdapter implements SafeTableGroup {
    private final TableGroupRepository tableGroupRepository;

    public TableGroupAdapter(final TableGroupRepository tableGroupRepository) {
        this.tableGroupRepository = tableGroupRepository;
    }

    @Override
    public void canChangeEmptyStatus(final Long orderTableId) {
        if (tableGroupRepository.existsByOrderTablesOrderTableId(orderTableId)) {
           throw new InvalidTryChangeEmptyException("단체 지정된 주문 테이블의 자리 비움 상태를 바꿀 수 없습니다.");
        }
    }

    @Override
    public Long getTableGroupId(final Long orderTableId) {
        TableGroup tableGroup = tableGroupRepository.findTableGroupByOrderTablesOrderTableId(orderTableId)
                .orElse(null);

        if (tableGroup == null) {
            return null;
        }

        return tableGroup.getId();
    }
}