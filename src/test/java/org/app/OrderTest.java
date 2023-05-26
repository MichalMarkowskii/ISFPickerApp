package org.app;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    @Test
    public void testOrderConstructor() {
        Order order = new Order("12345", new BigDecimal("10.00"), Duration.ofMinutes(30), LocalTime.of(14, 30));
        assertNotNull(order);
        assertEquals("12345", order.getOrderId());
        assertEquals(new BigDecimal("10.00"), order.getOrderValue());
        assertEquals(Duration.ofMinutes(30), order.getPickingTime());
        assertEquals(LocalTime.of(14, 30), order.getCompleteBy());
    }

    @Test
    public void testOrderGettersAndSetters() {
        Order order = new Order();
        order.setOrderId("67890");
        order.setOrderValue(new BigDecimal("20.00"));
        order.setPickingTime(Duration.ofMinutes(45));
        order.setCompleteBy(LocalTime.of(15, 30));
        order.setUrgency(1);
        order.setPickingStartTime(LocalTime.of(14, 45));

        assertEquals("67890", order.getOrderId());
        assertEquals(new BigDecimal("20.00"), order.getOrderValue());
        assertEquals(Duration.ofMinutes(45), order.getPickingTime());
        assertEquals(LocalTime.of(15, 30), order.getCompleteBy());
        assertEquals(1, order.getUrgency(), 0.001);
        assertEquals(LocalTime.of(14, 45), order.getPickingStartTime());
    }
}