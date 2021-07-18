package kitchenpos.repository;

import kitchenpos.domain.order.MenuRef;
import kitchenpos.domain.order.OrderLineItem;
import kitchenpos.repository.config.DataJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataJdbcConfig.class)
class OrderLineItemRepositoryTest {

    @Autowired
    OrderLineItemRepository orderLineItemRepository;

    @Test
    void saveAndFind() {
        OrderLineItem of = OrderLineItem.of(MenuRef.of(1L), 10L);
        OrderLineItem save = orderLineItemRepository.save(of);
        assertThat(save.getSeq()).isNotNull();

        OrderLineItem byId = orderLineItemRepository.findById(save.getSeq()).orElseThrow(IllegalArgumentException::new);

        assertThat(byId).isEqualTo(save);
    }
}