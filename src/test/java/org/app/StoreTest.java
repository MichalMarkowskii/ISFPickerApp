package org.app;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest {

    @Test
    public void testGetPickers() {
        List<Picker> pickers = new ArrayList<>();
        pickers.add(new Picker("1"));
        pickers.add(new Picker("2"));
        Store store = new Store();
        store.setPickers(pickers);
        assertEquals(pickers, store.getPickers());
    }

    @Test
    public void testGetPickingStartTime() {
        Store store = new Store();
        store.setPickingStartTime(LocalTime.NOON);
        assertEquals(LocalTime.NOON, store.getPickingStartTime());
    }

    @Test
    public void testGetPickingEndTime() {
        Store store = new Store();
        store.setPickingEndTime(LocalTime.NOON.plusHours(1));
        assertEquals(LocalTime.NOON.plusHours(1), store.getPickingEndTime());
    }

}
