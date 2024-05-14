package inventory.model;

import inventory.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;
    private InventoryRepository repo;
    private Product product;
    private ObservableList<Part> partList = FXCollections.observableArrayList();

    InventoryTest() {
    }

    @BeforeEach
    void setUp() {
        try {
            this.repo = new InventoryRepository();
            this.inventory = this.repo.getInventory();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    @Test
    @DisplayName("Empty search item")
    void lookUpProduct_TC01() {
        assert this.inventory.lookupProduct("") != null;

    }

    @Test
    @DisplayName("Search item found")
    void lookUpProduct_TC02() {
        assert this.inventory.lookupProduct("Clock").getProductId() == 1;

    }

    @Test
    @DisplayName("Search item not found")
    void lookUpProduct_TC03() {
        assert this.inventory.lookupProduct("TEST") == null;

    }

    @Test
    @DisplayName("Empty list")
    void lookUpProduct_TC04() {
        List<Product> prods = new ArrayList<>(this.inventory.getAllProducts());  // Copie pentru evitarea ConcurrentModificationException
        for (Product prod : prods) {
            this.inventory.removeProduct(prod);
        }

        Product result = this.inventory.lookupProduct("TEST");
        assertNotNull(result, "Expected a default product, but got null"); // Verifică că nu returnează null
        assertEquals(0, result.getProductId(), "Expected product ID to be 0 for non-existing product search"); // Verifică că ID-ul este 0
    }

}
