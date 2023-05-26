package org.app;

import java.time.Duration;
import java.util.*;

//Class designed for the purposes of the first task - superseded by OrderValueMaximizer.
public class OrderCountMaximizer {

    /**
     * Chooses the best sorting method based on order completion count.
     *
     * @param store   store to which the sorted orders are to be applied.
     * @param store1  dummy store for test sorting purposes
     * @param store2  dummy store for test sorting purposes
     * @param store3  dummy store for test sorting purposes
     * @param orders1 dummy order list for test sorting purposes
     * @param orders2 dummy order list for test sorting purposes
     * @param orders3 dummy order list for test sorting purposes
     */
    protected static void maximize(Store store, Store store1, Store store2, Store store3, List<Order> orders1, List<Order> orders2, List<Order> orders3) {
        List<Order> ratioSorted = Sort.sortByUrgencyRatio(store1, orders1);
        List<Order> timeSorted = Sort.sortByTime(orders2);
        List<Order> deadlineSorted = Sort.sortByDeadline(orders3);
        int ratioSortedCount = assignOrders(store1, ratioSorted);
        int timeSortedCount = assignOrders(store2, timeSorted);
        int deadlineSortedCount = assignOrders(store3, deadlineSorted);
        int max = Math.max(Math.max(ratioSortedCount, timeSortedCount), deadlineSortedCount);
        if (ratioSortedCount == max) {
            assignOrders(store, ratioSorted);
        } else if (timeSortedCount == max) {
            assignOrders(store, timeSorted);
        } else {
            assignOrders(store, deadlineSorted);
        }
    }

    /**
     * Assigns orders in a round-robin fashion to each of the pickers favouring the ones with more free time
     * and based on previously selected sorting method.
     *
     * @param store  store to which the orders are to be assigned
     * @param orders a list of orders to assign
     * @return amount of orders assigned
     */
    protected static int assignOrders(Store store, List<Order> orders) {
        Duration totalTime = Duration.between(store.getPickingStartTime(), store.getPickingEndTime());
        int orderCount = 0;
        for (Order order : orders) {
            Picker mostAvailablePicker = null;
            Duration mostTime = Duration.ZERO;
            for (Picker picker : store.getPickers()) {
                if (totalTime.minus(picker.getTotalOrderTime()).compareTo(mostTime) > 0) {
                    if (!store.getPickingStartTime().plus(picker.getTotalOrderTime().plus(order.getPickingTime())).isAfter(order.getCompleteBy())) {
                        mostAvailablePicker = picker;
                        mostTime = totalTime.minus(picker.getTotalOrderTime());
                    }
                }
            }
            if (mostAvailablePicker != null) {
                order.setPickingStartTime(store.getPickingStartTime().plus(mostAvailablePicker.getTotalOrderTime()));
                mostAvailablePicker.addOrder(order);
                mostAvailablePicker.setTotalOrderTime(mostAvailablePicker.getTotalOrderTime().plus(order.getPickingTime()));
                orderCount++;
            }
        }
        return orderCount;
    }

}