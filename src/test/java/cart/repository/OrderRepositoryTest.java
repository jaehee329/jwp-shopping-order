package cart.repository;

import static cart.TestSource.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import cart.dao.OrderDao;
import cart.dao.OrderProductDao;
import cart.domain.Member;
import cart.domain.Order;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        OrderDao orderDao = new OrderDao(jdbcTemplate, dataSource);
        OrderProductDao orderProductDao = new OrderProductDao(jdbcTemplate, dataSource);
        this.orderRepository = new OrderRepository(orderDao, orderProductDao);
    }

    @Test
    void 주문을_DB에_추가한다() {
        // when
        Long orderId = orderRepository.insert(order1);

        // then
        AssertionsForClassTypes.assertThat(orderId).isNotNull();
    }

    @Test
    void 저장된_특정_주문을_조회한다() {
        // given
        Long orderId = orderRepository.insert(order1);

        // when
        Order persistOrder = orderRepository.findById(orderId);

        // then
        AssertionsForClassTypes.assertThat(persistOrder.getId()).isEqualTo(orderId);
    }

    @Test
    void 특정_사용자에_대한_주문을_조회한다() {
        // given
        Long orderId = orderRepository.insert(order1);
        Long memberId = order1.getMember().getId();

        // when
        List<Order> orders = orderRepository.findAllByMemberId(memberId);
        List<Member> members = orders.stream()
            .map(Order::getMember)
            .collect(Collectors.toList());

        // then
        assertThat(members).hasSize(1);
    }
}
