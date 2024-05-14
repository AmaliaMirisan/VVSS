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

public class PartServiceTest {
    @Mock
    private final PartRepository partRepository = mock(PartRepository.class);

    @Mock
    private final Part part = mock(Part.class);

    private final InventoryService inventoryService = new InventoryService(new InventoryRepository(), partRepository);

    @Test
    void testAddPartInvalidPrice() {
        when(part.getPrice()).thenReturn(0.0);

        try {
            inventoryService.addPart("part1",part.getPrice(),5, 1,10);
        } catch (PartException e) {
            assertEquals("The price must be greater than 0. ", e.getMessage());
        }
    }

    @Test
    void testAddPartInvalidInStock() {
        when(part.getInStock()).thenReturn(0);

        try {
            inventoryService.addPart("partTest",14, part.getInStock(),2,20);

        } catch (PartException e) {
            assertEquals("Inventory level must be greater than 0. ", e.getMessage());
        }
    }

    @Test
    void testAddPartSuccess() {
        when(part.getPartId()).thenReturn(10);
        when(part.getName()).thenReturn("part10");
        when(part.getPrice()).thenReturn(22.0);
        when(part.getInStock()).thenReturn(7);
        when(part.getMin()).thenReturn(1);
        when(part.getMax()).thenReturn(10);


        try {
            inventoryService.addPart(part.getName(), part.getPrice(), part.getInStock(), part.getMin(), part.getMax());
        } catch (PartException e) {
            fail();
        }
    }
}
