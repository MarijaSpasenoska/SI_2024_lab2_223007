import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void testNullAndEmptyList() {
        // Test Case 1: allItems is null
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());

        // Test Case 2: allItems is an empty list
        List<Item> items = new ArrayList<>();
        boolean result = SILab2.checkCart(items, 100);
        assertTrue(result);
    }
    @Test
    public void testVariousItems() {
        // Test Case 3: Invalid barcode
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "1234567890", 150, 0));
        items.add(new Item("Item2", "0234567895", 400, 0.15f));
        items.add(new Item("     ", "1q25qs563s", 400, 0));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, 100);
        });
        assertEquals("Invalid character in item barcode!", exception.getMessage());

        // Test Case 4: Missing barcode
        items.clear();
        items.add(new Item("Item3", "1234567890", 400, 0));
        items.add(new Item("Item4", "0234567895", 700, 0.15f));
        items.add(new Item("Item5", null, 180, 0.2f));
        exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, 100);
        });
        assertEquals("No barcode!", exception.getMessage());

        // Test Case 5: Various items, some with discounts
        items.clear();
        items.add(new Item("Item6", "8974154655", 300, 0));
        items.add(new Item("Item7", "0698481645", 800, 0.15f));
        items.add(new Item("Item8", "8546168413", 600, 0.2f));
        boolean result = SILab2.checkCart(items, 100);
        assertFalse(result);
    }
}

