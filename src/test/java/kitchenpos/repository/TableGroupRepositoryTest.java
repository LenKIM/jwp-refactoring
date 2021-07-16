package kitchenpos.repository;

import kitchenpos.domain.tablegroup.OrderTableRef;
import kitchenpos.domain.tablegroup.TableGroup;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class TableGroupRepositoryTest {

    @Autowired
    private TableGroupRepository tableGroupRepository;

    @Test
    void saveAndFind() {

        OrderTableRef orderTableRef1 = new OrderTableRef(1L);
        OrderTableRef orderTableRef2 = new OrderTableRef(2L);
        OrderTableRef orderTableRef3 = new OrderTableRef(3L);

        TableGroup tableGroup = TableGroup.of(Sets.newSet(orderTableRef1, orderTableRef2, orderTableRef3));
        TableGroup save = tableGroupRepository.save(tableGroup);
        assertThat(save.getId()).isNotNull();

        TableGroup find = tableGroupRepository.findTableGroup(save.getId());
        assertThat(save.getId()).isEqualTo(find.getId());
    }
}