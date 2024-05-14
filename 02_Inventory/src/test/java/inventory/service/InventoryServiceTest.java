package inventory.service;


import inventory.exceptions.PartException;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.repository.PartRepository;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

class InventoryServiceTest {
    private final Part part = new Part(11,"part11", 11, 7, 2, 33);

    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    private int partDynamicValue = 1;

    private final InventoryRepository invRepo = new InventoryRepository();
    private final PartRepository partRepo = new PartRepository("data/parts.txt");
    private final InventoryService service = new InventoryService(invRepo, partRepo);
    int partsSize = service.getParts().size();

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPart_BVA_valid_price() {
        name = "Part1";
        price = 25;
        inStock = 3;
        min = 1;
        max = 3;


        assert service.getParts().size() == partsSize;
        try {
            service.addPart(name, price, inStock, min, max);

        } catch (PartException e) {
            assert false;
        }
        assert service.getParts().size() == partsSize + 1;
        partsSize++;
    }
    @Test
    @Tag("BoundaryValueAnalysis")
    void addPart_BVA_invalid_price() {
        name = "Part1";
        price = -25;
        inStock = 3;
        min = 1;
        max = 3;

        try {
            service.addPart(name, price, inStock, min, max);
            //assert false;
        } catch (PartException e) {
            assert e.getMessage().equals("The price must be greater than 0. ");
        }
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPart_BVA_valid_name() {
        name = "Part2";
        price = 55;
        inStock = 7;
        min = 3;
        max = 15;


        assert service.getParts().size() == partsSize;
        try {
            service.addPart(name, price, inStock, min, max);

        } catch (PartException e) {
            assert false;
        }
        assert service.getParts().size() == partsSize + 1;
        partsSize++;
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPart_BVA_invalid_name() {
        name = "";
        price = 25;
        inStock = 3;
        min = 1;
        max = 3;


        try {
            service.addPart(name, price, inStock, min, max);
            //assert false;
        } catch (PartException e) {
            assert e.getMessage().equals("A name has not been entered. ");
        }
    }

    //-------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Valid Price ECP Test")
    void addPart_ECP_valid_price() {
        name = "Part3";
        price = 60;
        inStock = 5;
        min = 3;
        max = 15;


        assert service.getParts().size() == partsSize;
        try {
            service.addPart(name, price, inStock, min, max);

        } catch (PartException e) {
            assert false;
        }
        assert service.getParts().size() == partsSize + 1;
        partsSize++;
    }
    @Test
    @Timeout(5)
    void addPart_ECP_invalid_price() {
        name = "Part3";
        price = -60;
        inStock = 5;
        min = 3;
        max = 15;


        try {
            service.addPart(name, price, inStock, min, max);
            //assert false;
        } catch (PartException e) {
            assert e.getMessage().equals("The price must be greater than 0. ");
        }
    }
    @RepeatedTest(2)
    @DisplayName("Valid Name ECP Test")
    void addPart_ECP_valid_name() {
        name = "Part4";
        price = 25;
        inStock = 2;
        min = 1;
        max = 17;


        assert service.getParts().size() == partsSize;
        try {
            service.addPart(name, price, inStock, min, max);

        } catch (PartException e) {
            assert false;
        }
        assert service.getParts().size() == partsSize + 1;
        partsSize++;
    }
    @Test
    @Timeout(10)
    void addPart_ECP_invalid_name() {
        name = "";
        price = 25;
        inStock = 2;
        min = 1;
        max = 17;


        try {
            service.addPart(name, price, inStock, min, max);
            //assert false;
        } catch (PartException e) {
            assert e.getMessage().equals("A name has not been entered. ");
        }
    }
    //@Test
   // @Disabled("TO DO")
    //void dummyTest() {
       // assert false;
   // }

    }