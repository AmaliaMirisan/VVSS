package inventory.entityLab4;
import inventory.model.Part;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PartTest {
    private int partId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    private final Part part = new Part(1, "part1", 15, 5, 3, 30);

    @Test
    void testGetName(){
        assertEquals("part1", part.getName());
    }

    @Test
    void testGetPrice(){
        assertEquals(15, part.getPrice());
    }
}
