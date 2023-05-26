package org.app;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {

    @Test
    void testSortByUrgencyRatio() {
        Store store = new Store(new ArrayList<>(), LocalTime.of(9, 0), LocalTime.of( 21, 0));
        List<Order> orders = Arrays.asList(
                new Order("1", BigDecimal.valueOf(50.00), Duration.ofMinutes(30), LocalTime.of(12, 0)),
                new Order("2", BigDecimal.valueOf(30.00), Duration.ofMinutes(20), LocalTime.of(14, 0)),
                new Order("3", BigDecimal.valueOf(40.00), Duration.ofMinutes(40), LocalTime.of(16, 0))
        );
        List<Order> sortedOrders = Sort.sortByUrgencyRatio(store, orders);
        assertEquals("1", sortedOrders.get(0).getOrderId());
        assertEquals("2", sortedOrders.get(1).getOrderId());
        assertEquals("3", sortedOrders.get(2).getOrderId());
    }

    @Test
    public void testSortByValueToTimeRatio() {
        List<Order> orders = Arrays.asList(
                new Order("1", BigDecimal.valueOf(100.00), Duration.ofMinutes(10), LocalTime.of(11, 0)),
                new Order("2", BigDecimal.valueOf(50.00), Duration.ofMinutes(5), LocalTime.of(11, 0)),
                new Order("3", BigDecimal.valueOf(75.00), Duration.ofMinutes(15), LocalTime.of(11, 0))
        );
        List<Order> sortedOrders = Sort.sortByValueToTimeRatio(orders);
        assertEquals("2", sortedOrders.get(0).getOrderId());
        assertEquals("1", sortedOrders.get(1).getOrderId());
        assertEquals("3", sortedOrders.get(2).getOrderId());
    }

    @Test
    public void testSortByTime() {
        List<Order> orders = Arrays.asList(
                new Order("1", BigDecimal.valueOf(100.00), Duration.ofMinutes(10), LocalTime.of(11, 0)),
                new Order("2", BigDecimal.valueOf(50.00), Duration.ofMinutes(5), LocalTime.of(11, 5)),
                new Order("3", BigDecimal.valueOf(75.00), Duration.ofMinutes(15), LocalTime.of(10, 0))
        );
        List<Order> sortedOrders = Sort.sortByTime(orders);
        assertEquals("2", sortedOrders.get(0).getOrderId());
        assertEquals("1", sortedOrders.get(1).getOrderId());
        assertEquals("3", sortedOrders.get(2).getOrderId());
    }

    @Test
    public void testSortByDeadline() {
        List<Order> orders = Arrays.asList(
                new Order("1", BigDecimal.valueOf(100.00), Duration.ofMinutes(10), LocalTime.of(11, 0)),
                new Order("2", BigDecimal.valueOf(50.00), Duration.ofMinutes(5), LocalTime.of(11, 5)),
                new Order("3", BigDecimal.valueOf(75.00), Duration.ofMinutes(15), LocalTime.of(10, 0))
        );
        List<Order> sortedOrders = Sort.sortByDeadline(orders);
        assertEquals("3", sortedOrders.get(0).getOrderId());
        assertEquals("1", sortedOrders.get(1).getOrderId());
        assertEquals("2", sortedOrders.get(2).getOrderId());
    }

    @Test
    public void testSortByValue() {
        List<Order> orders = Arrays.asList(
                new Order("1", BigDecimal.valueOf(100.00), Duration.ofMinutes(10), LocalTime.of(11, 0)),
                new Order("2", BigDecimal.valueOf(50.00), Duration.ofMinutes(5), LocalTime.of(11, 5)),
                new Order("3", BigDecimal.valueOf(75.00), Duration.ofMinutes(15), LocalTime.of(10, 0))
        );
        List<Order> sortedOrders = Sort.sortByValue(orders);
        assertEquals("1", sortedOrders.get(0).getOrderId());
        assertEquals("3", sortedOrders.get(1).getOrderId());
        assertEquals("2", sortedOrders.get(2).getOrderId());
    }

}