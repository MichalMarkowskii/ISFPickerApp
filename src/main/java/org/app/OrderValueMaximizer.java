package org.app;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Class designed for the purposes of the second task
public class OrderValueMaximizer {

    public static void main(String[] args) {
        Store store = JSONReader.readStore(args[0]);
        Store store1 = JSONReader.readStore(args[0]);
        Store store2 = JSONReader.readStore(args[0]);
        Store store3 = JSONReader.readStore(args[0]);
        Store store4 = JSONReader.readStore(args[0]);
        Store store5 = JSONReader.readStore(args[0]);
        List<Order> orders = JSONReader.readOrders(args[1]);
        List<Order> orders1 = JSONReader.readOrders(args[1]);
        List<Order> orders2 = JSONReader.readOrders(args[1]);
        List<Order> orders3 = JSONReader.readOrders(args[1]);
        List<Order> orders4 = JSONReader.readOrders(args[1]);
        maximize(store,store1,store2,store3,store4,store5,orders,orders1,orders2,orders3,orders4);
        store.printStoreOrders();
    }

    /**
     * Chooses the best sorting method based on total value of completed orders.
     *
     * @param store   store to which the sorted orders are to be applied.
     * @param store1  dummy store for test sorting purposes
     * @param store2  dummy store for test sorting purposes
     * @param store3  dummy store for test sorting purposes
     * @param orders1 dummy order list for test sorting purposes
     * @param orders2 dummy order list for test sorting purposes
     * @param orders3 dummy order list for test sorting purposes
     */
    public static void maximize(Store store,Store store1,Store store2,Store store3, Store store4,
                                 Store store5, List<Order> orders, List<Order> orders1, List<Order> orders2, List<Order> orders3, List<Order> orders4) {
        List<Order> urgencySorted = Sort.sortByUrgencyRatio(store1, orders);
        List<Order> timeSorted = Sort.sortByTime(orders1);
        List<Order> deadlineSorted = Sort.sortByDeadline(orders2);
        List<Order> valueSorted = Sort.sortByValue(orders3);
        List<Order> valueRatioSorted = Sort.sortByValueToTimeRatio(orders4);
        double urgencySortedCount = assignOrders(store1, urgencySorted);
        double timeSortedCount = assignOrders(store2, timeSorted);
        double deadlineSortedCount = assignOrders(store3, deadlineSorted);
        double valueSortedCount = assignOrders(store4, valueSorted);
        double valueRatioSortedCount = assignOrders(store5, valueRatioSorted);
        List<Double> values = Arrays.asList(urgencySortedCount, timeSortedCount, deadlineSortedCount, valueSortedCount, valueRatioSortedCount);
        double max = Collections.max(values);
        if (urgencySortedCount == max) {
            assignOrders(store, urgencySorted);
        } else if (timeSortedCount == max) {
            assignOrders(store, timeSorted);
        } else if (deadlineSortedCount == max) {
            assignOrders(store, deadlineSorted);
        } else if (valueSortedCount == max) {
            assignOrders(store, valueSorted);
        } else if (valueRatioSortedCount == max) {
            assignOrders(store, valueRatioSorted);
        }
    }

    /**
     * Assigns orders in a round-robin fashion to each of the pickers favouring the ones with more free time
     * and based on previously selected sorting method.
     *
     * @param store  store to which the orders are to be assigned
     * @param orders a list of orders to assign
     * @return total value of assigned orders
     */
    private static double assignOrders(Store store, List<Order> orders) {
        Duration totalTime = Duration.between(store.getPickingStartTime(), store.getPickingEndTime());
        double orderTotalValue = 0;
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
                orderTotalValue += order.getOrderValue().doubleValue();
            }
        }
        return orderTotalValue;
    }

}
