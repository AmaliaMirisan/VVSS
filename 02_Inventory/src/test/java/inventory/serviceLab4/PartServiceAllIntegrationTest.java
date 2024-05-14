package inventory.serviceLab4;

import inventory.exceptions.PartException;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.repository.PartRepository;
import inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PartServiceAllIntegrationTest {
    private final PartRepository partRepository = new PartRepository("data/parts.txt");

    private final Part part = new Part(11,"part11", 11, 7, 2, 33);
    private final InventoryService inventoryService = new InventoryService(new InventoryRepository(), partRepository);

    @Test
    void testAddPartInvalidPrice() {
        try {
            inventoryService.addPart("part1",part.getPrice(),5, 1,10);
        } catch (PartException e) {
            assertEquals("The price must be greater than 0. ", e.getMessage());
        }
    }
    @Test
    void testAddPartInvalidInStock() {
        try {
            inventoryService.addPart("partTest",14, part.getInStock(),2,20);

        } catch (PartException e) {
            assertEquals("Inventory level must be greater than 0. ", e.getMessage());
        }
    }

    @Test
    void testAddPartSuccess() {

        int size = inventoryService.getParts().size();

        try {
            inventoryService.addPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax());
        } catch (PartException e) {
            fail();
        }

        assertEquals(size + 1, inventoryService.getParts().size());

    }
}
