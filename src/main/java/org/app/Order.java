package org.app;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class Order {

    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;
    private long urgency; //time it takes to complete an order multiplied by the time before deadline
    private double valueToTime;
    private LocalTime pickingStartTime; //time of order preparation start

    public Order() {
    }

    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
        this.valueToTime = orderValue.doubleValue() / pickingTime.toMinutes();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Duration pickingTime) {
        this.pickingTime = pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalTime completeBy) {
        this.completeBy = completeBy;
    }

    public long getUrgency() {
        return urgency;
    }

    public void setUrgency(long urgency) {
        this.urgency = urgency;
    }

    public double getValueToTime() {
        return valueToTime;
    }

    public void setValueToTime(double valueToTime) {
        this.valueToTime = valueToTime;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

}
