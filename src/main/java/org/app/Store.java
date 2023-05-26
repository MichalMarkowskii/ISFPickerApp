package org.app;

import java.time.LocalTime;
import java.util.List;

public class Store {

    private List<Picker> pickers;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

    public Store() {
    }

    public Store(List<Picker> pickers, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    public List<Picker> getPickers() {
        return pickers;
    }

    public void setPickers(List<Picker> pickers) {
        this.pickers = pickers;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(LocalTime pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }

    public void printStoreOrders() {
        for (Picker picker: this.pickers) {
            for (Order order : picker.getOrders()) {
                System.out.println(picker.getId() + " " + order.getOrderId() + " " + order.getPickingStartTime());
            }
        }
    }

}
