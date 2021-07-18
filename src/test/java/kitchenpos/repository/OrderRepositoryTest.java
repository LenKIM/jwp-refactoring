package kitchenpos.repository;

import kitchenpos.domain.order.*;
import kitchenpos.repository.config.DataJdbcConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.HashSet;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void name() {
        Order of = Order.of(OrderTableRef.of(1L), OrderStatus.COOKING, LocalDateTime.now(), new HashSet<>());
        Order save = orderRepository.save(of);
        Assertions.assertThat(save.getId()).isNotNull();
    }
}