package org.app;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PickerTest {

    @Test
    public void testPickerConstructor() {
        Picker picker = new Picker("P1");
        assertNotNull(picker);
        assertEquals("P1", picker.getId());
        assertEquals(Duration.ZERO, picker.getTotalOrderTime());
        assertEquals(new ArrayList<Order>(), picker.getOrders());
    }

    @Test
    public void testPickerGettersAndSetters() {
        Picker picker = new Picker("P2");
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("order-1", BigDecimal.valueOf(1.0), Duration.ofMinutes(30), LocalTime.of(9,0)));
        orders.add(new Order("order-2", BigDecimal.valueOf(1.0), Duration.ofMinutes(45), LocalTime.of(9,0)));
        picker.setTotalOrderTime(Duration.ofMinutes(75));
        picker.setOrders(orders);
        assertEquals("P2", picker.getId());
        assertEquals(Duration.ofMinutes(75), picker.getTotalOrderTime());
        assertEquals(orders, picker.getOrders());
    }

    @Test
    public void testAddOrder() {
        Picker picker = new Picker("P3");
        Order order1 = new Order("order-1", BigDecimal.valueOf(1.0), Duration.ofMinutes(30), LocalTime.of(9,0));
        Order order2 = new Order("order-2", BigDecimal.valueOf(1.0), Duration.ofMinutes(45), LocalTime.of(9,0));
        picker.addOrder(order1);
        picker.addOrder(order2);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        assertEquals(orders, picker.getOrders());
    }
}