package org.app;

import org.json.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Class for reading .json files.
public class JSONReader {

    /**
     * Reads .json file containing information about a store. Creates a Store object and
     * corresponding Picker objects.
     *
     * @param path path to .json file
     * @return a Store object based on information in the .json file
     */
    public static Store readStore(String path) {
        try {
            String storeString = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject storeJSON = new JSONObject(storeString);
            JSONArray storeArray = storeJSON.getJSONArray("pickers");
            List<Picker> pickers = new ArrayList<>();
            for (int i = 0; i < storeArray.length(); i++) {
                pickers.add(new Picker(storeArray.getString(i)));
            }
            LocalTime pickingStartTime = LocalTime.parse(storeJSON.getString("pickingStartTime"), DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime pickingEndTime = LocalTime.parse(storeJSON.getString("pickingEndTime"), DateTimeFormatter.ISO_LOCAL_TIME);
            return new Store(pickers, pickingStartTime, pickingEndTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Store();
    }

    /**
     * Reads .json file containing information about orders. Creates a list of Order objects.
     *
     * @param path path to .json file
     * @return a list of Order objects based on information in the .json file
     */
    public static List<Order> readOrders(String path) {
        try {
            String ordersString = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray ordersArray = new JSONArray(ordersString);
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < ordersArray.length(); i++) {
                JSONObject jsonObject = ordersArray.getJSONObject(i);
                String orderId = jsonObject.getString("orderId");
                BigDecimal orderValue = jsonObject.getBigDecimal("orderValue");
                Duration pickingTime = Duration.parse(jsonObject.getString("pickingTime"));
                LocalTime completeBy = LocalTime.parse(jsonObject.getString("completeBy"), DateTimeFormatter.ISO_LOCAL_TIME);
                orders.add(new Order(orderId, orderValue, pickingTime, completeBy));
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}