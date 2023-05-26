package org.app;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Picker {

    private String Id;
    private Duration totalOrderTime = Duration.ZERO; //total completion time of assigned orders
    private List<Order> orders = new ArrayList<>();

    public Picker(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Duration getTotalOrderTime() {
        return totalOrderTime;
    }

    public void setTotalOrderTime(Duration totalOrderTime) {
        this.totalOrderTime = totalOrderTime;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
