package org.app;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderValueMaximizerTest {

    @Test
    public void testMaximize() {
        Store store = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        Store store1 = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        Store store2 = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        Store store3 = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        Store store4 = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        Store store5 = new Store(Arrays.asList(new Picker("1"), new Picker("2")), LocalTime.of(9,0), LocalTime.of(11,0));
        List<Order> orders = Arrays.asList(
                new Order("order1", BigDecimal.valueOf(60), Duration.ofMinutes(30), LocalTime.of(10, 0)),
                new Order("order2", BigDecimal.valueOf(15), Duration.ofMinutes(20), LocalTime.of(10, 0)),
                new Order("order3", BigDecimal.valueOf(20), Duration.ofMinutes(45), LocalTime.of(10, 0))
        );
        List<Order> orders1 = Arrays.asList(
                new Order("order1", BigDecimal.valueOf(60), Duration.ofMinutes(30), LocalTime.of(10, 0)),
                new Order("order2", BigDecimal.valueOf(15), Duration.ofMinutes(20), LocalTime.of(10, 0)),
                new Order("order3", BigDecimal.valueOf(20), Duration.ofMinutes(45), LocalTime.of(10, 0))
        );
        List<Order> orders2 = Arrays.asList(
                new Order("order1", BigDecimal.valueOf(60), Duration.ofMinutes(30), LocalTime.of(10, 0)),
                new Order("order2", BigDecimal.valueOf(15), Duration.ofMinutes(20), LocalTime.of(10, 0)),
                new Order("order3", BigDecimal.valueOf(20), Duration.ofMinutes(45), LocalTime.of(10, 0))
        );
        List<Order> orders3 = Arrays.asList(
                new Order("order1", BigDecimal.valueOf(60), Duration.ofMinutes(30), LocalTime.of(10, 0)),
                new Order("order2", BigDecimal.valueOf(15), Duration.ofMinutes(20), LocalTime.of(10, 0)),
                new Order("order3", BigDecimal.valueOf(20), Duration.ofMinutes(45), LocalTime.of(10, 0))
        );
        List<Order> orders4 = Arrays.asList(
                new Order("order1", BigDecimal.valueOf(60), Duration.ofMinutes(30), LocalTime.of(10, 0)),
                new Order("order2", BigDecimal.valueOf(15), Duration.ofMinutes(20), LocalTime.of(10, 0)),
                new Order("order3", BigDecimal.valueOf(20), Duration.ofMinutes(45), LocalTime.of(10, 0))
        );
        OrderValueMaximizer.maximize(store, store1, store2, store3, store4, store5, orders, orders1, orders2, orders3, orders4);
        assertEquals(2, store.getPickers().size());
        assertEquals(2, store.getPickers().get(0).getOrders().size());
        assertEquals(1, store.getPickers().get(1).getOrders().size());
        assertEquals("order1", store.getPickers().get(0).getOrders().get(0).getOrderId());
        assertEquals("order2", store.getPickers().get(0).getOrders().get(1).getOrderId());
        assertEquals("order3", store.getPickers().get(1).getOrders().get(0).getOrderId());
    }


}
