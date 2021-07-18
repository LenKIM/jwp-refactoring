package kitchenpos.application;

import kitchenpos.domain.tablegroup.OrderTableRef;
import kitchenpos.domain.tablegroup.TableGroup;
import kitchenpos.repository.TableGroupRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class TableGroupServiceTest {

    @Mock
    TableGroupRepository tableGroupRepository;

    @Test
    void create() {
        HashSet<OrderTableRef> orderTables = Sets.newHashSet();
        TableGroup tableGroup = TableGroup.of(orderTables);

        tableGroupRepository.save(tableGroup);

    }
}