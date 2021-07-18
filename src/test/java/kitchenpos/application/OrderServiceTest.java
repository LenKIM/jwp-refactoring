package kitchenpos.application;

import kitchenpos.domain.order.*;
import kitchenpos.domain.ordertable.OrderTable;
import kitchenpos.repository.MenuRepository;
import kitchenpos.repository.OrderLineItemRepository;
import kitchenpos.repository.OrderRepository;
import kitchenpos.repository.OrderTableRepository;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderTableRepository orderTableRepository;
    @Mock
    private OrderLineItemRepository orderLineItemRepository;

    @InjectMocks
    OrderService orderService;

    private Order order;

    private OrderTable orderTable;
    private final static long ANY_ORDER_ID = 1L;
    private final static long ANY_ORDER_TABLE_ID = 1L;

    private final static long ORDER_LINE_ITEM_ID_1L = 1L;
    private final static long ORDER_LINE_ITEM_ID_2L = 2L;
    private final static long MENU_ID_1L = 1L;
    private final static long MENU_ID_2L = 2L;
    private OrderLineItem orderLineItem1;
    private OrderLineItem orderLineItem2;


    @BeforeEach
    void setUp() {
        // Order 를 만들기 위한 Dummy Data
        orderLineItem1 = OrderLineItem.of(MenuRef.of(MENU_ID_1L), 10);
        orderLineItem2 = OrderLineItem.of(MenuRef.of(MENU_ID_2L), 20);

        orderTable = OrderTable.of(10);
    }

    @Test
    @DisplayName("주문 항목(OrderLineItem)이 없다면 주문을 등록할 수 없다.")
    void exception_create() {
        order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID), Sets.newHashSet());

        assertThatThrownBy(() -> orderService.create(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("should have orderLineItems");
    }

    @Test
    @DisplayName("주문 항목의 갯수가 주문 항목의 메뉴의 갯수와 일치 하지 않으면 등록할 수 없다.")
    void exception2_create() {
        given(menuRepository.countByIdIn(Lists.list(ORDER_LINE_ITEM_ID_1L)))
                .willReturn(100L);

        OrderLineItem orderLineItem1 = OrderLineItem.of(MenuRef.of(MENU_ID_1L), 10);
        ReflectionTestUtils.setField(orderLineItem1, "seq", ORDER_LINE_ITEM_ID_1L);

        Order order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID),
                Sets.newLinkedHashSet(OrderLineItem.of(MenuRef.of(MENU_ID_1L), 10L)));

        assertThatThrownBy(() -> orderService.create(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Not Match Size")
        ;
    }

    @Test
    @DisplayName("주문된 테이블이 빈 테이블일 경우 주문이 수행되지 않는다.")
    void exception3_create() {
        orderTable.changeEmptyTable();
        given(menuRepository.countByIdIn(any())).willReturn(2L);

        given(orderTableRepository.findOrderTableById(ANY_ORDER_TABLE_ID)).willReturn(orderTable);

        Order order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID),
                Sets.newHashSet(Lists.list(orderLineItem1, orderLineItem2)));

        assertThatThrownBy(() -> orderService.create(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Should have not orderTable empty");
    }

    @Test
    @DisplayName("주문이 들어가면 처음 상태(order status)는 조리(COOKING) 상태가 된다.")
    void after_create_orderStatus_is_COOKING() {
        orderTable.changeNonEmptyTable();
        Order order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID),
                Sets.newHashSet(Lists.list(orderLineItem1, orderLineItem2)));

        given(orderTableRepository.findOrderTableById(ANY_ORDER_TABLE_ID)).willReturn(orderTable);
        given(menuRepository.countByIdIn(any())).willReturn(2L);
        given(orderRepository.save(any())).willReturn(order);


        Order saveOrder = orderService.create(order);

        assertThat(saveOrder.getOrderStatus()).isEqualTo(OrderStatus.COOKING);
    }

    @Test
    @DisplayName("주문의 상태(order status)를 식사 상태로 변경할 수 있다.")
    void changeOrderStatusTest() {
        order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID),
                Sets.newHashSet(Lists.list(orderLineItem1, orderLineItem2)));
        given(orderRepository.findById(ANY_ORDER_ID)).willReturn(Optional.of(order));
        order.changeStatus(OrderStatus.MEAL);
        given(orderRepository.save(any())).willReturn(order);

        Order changedOrder = orderService.changeOrderStatus(ANY_ORDER_ID, OrderStatus.MEAL);
        assertThat(changedOrder.getOrderStatus()).isEqualTo(OrderStatus.MEAL);
    }

    @Test
    @DisplayName("이미 계산이 완료된 주문은 주문 상태(order status)를 바꿀 수 없다.")
    void exception_changeOrderStatusTest() {
        order = Order.of(OrderTableRef.of(ANY_ORDER_TABLE_ID),
                Sets.newHashSet(Lists.list(orderLineItem1, orderLineItem2)));
        order.changeStatus(OrderStatus.COMPLETION);

        given(orderRepository.findById(ANY_ORDER_ID)).willReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.changeOrderStatus(ANY_ORDER_ID, OrderStatus.COMPLETION))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid OrderStatus");
    }
}