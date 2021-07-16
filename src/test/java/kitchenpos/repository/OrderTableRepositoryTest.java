package kitchenpos.repository;

import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class OrderTableRepositoryTest {

    @Autowired
    OrderTableRepository orderTableRepository;

    @Test
    void name() {
        assertThat(orderTableRepository).isNotNull();
    }

    @Test
    void saveAndFind() {
        OrderTable orderTable = OrderTable.of(10);
        OrderTable save = orderTableRepository.save(orderTable);
        assertThat(save.getId()).isNotNull();

        OrderTable orderTableById = orderTableRepository.findOrderTableById(save.getId());
        assertThat(orderTableById).isEqualTo(save);
    }
}