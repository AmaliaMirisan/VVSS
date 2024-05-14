package inventory.repositoryLab4;

import inventory.exceptions.PartException;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.repository.PartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PartRepositoryTest {
    private final PartRepository partRepo = new PartRepository("data/parts.txt");

    @Mock
    private final Part part = mock(Part.class);

    @Test
    void testGetPartsActual() {
        List<Part> parts = partRepo.getAll();
        assertEquals(3, parts.size());
    }
    @Test
    void testAddPart() {

        when(part.getPartId()).thenReturn(10);
        when(part.getName()).thenReturn("part10");
        when(part.getPrice()).thenReturn(22.0);
        when(part.getInStock()).thenReturn(7);
        when(part.getMin()).thenReturn(1);
        when(part.getMax()).thenReturn(10);


        int size = partRepo.getAll().size();
        try {
            partRepo.add(part);
        } catch (PartException e) {
            fail();
        }
        assertEquals(size + 1, partRepo.getAll().size());
    }
}
