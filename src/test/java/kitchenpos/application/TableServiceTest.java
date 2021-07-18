package kitchenpos.application;

import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.repository.OrderRepository;
import kitchenpos.repository.OrderTableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/**
 * **주문 테이블(order table)**
 *
 * - 주문 테이블을 빈 테이블로 만들 수 있다.
 *   - 주문의 상태가 조리이거나, 식사의 경우에는 빈 테이블로 만들 수 없다.
 * - 주문 테이블에는 방문한 손님 수를 변경할 수 있다.
 *   - 만약 처음 방문한 손님의 수가 없을 경우, 변경할 수 없다.
 */
@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class TableServiceTest {

    @InjectMocks
    TableService tableService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderTableRepository orderTableRepository;
    

    @Test
    void createTest() {
        OrderTable expected = OrderTable.of(10);
        given(orderTableRepository.save(expected)).willReturn(expected);

        OrderTable actual = tableService.create(expected);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void changEmptyOrderTable() {
        OrderTable expected = OrderTable.of(10);
        given(orderTableRepository.save(expected)).willReturn(expected);

        OrderTable actual = tableService.create(expected);
        actual.changeEmpty();

        assertThat(actual.isEmpty()).isTrue();
    }
}