package org.app;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

//Class for different types of sorting.
public class Sort {

    /**
     * Sorts orders by their urgency, calculated as the time it takes for the order to be completed multiplied by
     * the time before the order deadline.
     *
     * @param store  store to which the orders are to be applied (required for time before deadline calculation)
     * @param orders a list of orders to be sorted
     * @return a list of orders sorted by their urgency
     */

    protected static List<Order> sortByUrgencyRatio(Store store, List<Order> orders) {
        for (Order order : orders) {
            Duration timeRemaining = Duration.between(store.getPickingStartTime(), order.getCompleteBy());
            long urgency = order.getPickingTime().toMinutes() * timeRemaining.toMinutes();
            order.setUrgency(urgency);
        }
            orders.sort(Comparator.comparing(Order::getUrgency).thenComparing(Order::getOrderValue, Comparator.reverseOrder()).thenComparing(Order::getPickingTime).thenComparing(Order::getCompleteBy));
        return orders;
    }

    /**
     * Sorts orders by their value to time ratio.
     *
     * @param orders a list of orders to be sorted
     * @return a list of orders sorted by their value to time ratio
     */
    protected static List<Order> sortByValueToTimeRatio(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getValueToTime, Comparator.reverseOrder()).thenComparing(Order::getOrderValue).thenComparing(Order::getPickingTime).thenComparing(Order::getCompleteBy));
        return orders;
    }

    /**
     * Sorts orders by their completion time.
     *
     * @param orders a list of orders to be sorted
     * @return a list of orders sorted by their completion time
     */
    protected static List<Order> sortByTime(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getPickingTime).thenComparing(Order::getOrderValue, Comparator.reverseOrder()).thenComparing(Order::getCompleteBy));
        return orders;
    }

    /**
     * Sorts orders by their deadline.
     *
     * @param orders a list of orders to be sorted
     * @return a list of orders sorted by their deadline
     */
    protected static List<Order> sortByDeadline(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getCompleteBy).thenComparing(Order::getOrderValue, Comparator.reverseOrder()).thenComparing(Order::getPickingTime));
        return orders;
    }

    /**
     * Sorts orders by their value.
     *
     * @param orders a list of orders to be sorted
     * @return a list of orders sorted by their value
     */
    protected static List<Order> sortByValue(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getOrderValue, Comparator.reverseOrder()).thenComparing(Order::getPickingTime).thenComparing(Order::getCompleteBy));
        return orders;
    }

}
