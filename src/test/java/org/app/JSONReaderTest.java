package org.app;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {


    @Test
    void readStore() {
        Picker picker = new Picker("P1");
        List<Picker> pickers = new ArrayList<>();
        pickers.add(picker);
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(10, 0);
        Store store = new Store(pickers, start, end);
        Store storeRead = JSONReader.readStore("src/test/resources/store.json");
        assertEquals(store.getPickingStartTime(), storeRead.getPickingStartTime());
        assertEquals(store.getPickingEndTime(), storeRead.getPickingEndTime());
        assertEquals(store.getPickers().get(0), picker);
    }

    @Test
    void readOrders() {
        Order order = new Order("order-1", new BigDecimal("0.00"), Duration.ofMinutes(60), LocalTime.of(10, 0));
        List<Order> ordersRead = JSONReader.readOrders("src/test/resources/orders.json");
        assertEquals(order.getOrderId(), ordersRead.get(0).getOrderId());
        assertEquals(order.getOrderValue(), ordersRead.get(0).getOrderValue());
        assertEquals(order.getPickingTime(), ordersRead.get(0).getPickingTime());
        assertEquals(order.getCompleteBy(), ordersRead.get(0).getCompleteBy());
    }
}