package inventory.service;

import inventory.exceptions.PartException;
import inventory.model.*;
import inventory.repository.InventoryRepository;
import inventory.repository.PartRepository;
import javafx.collections.ObservableList;

import java.util.List;

import static inventory.model.Part.isValidPart;

public class InventoryService {

    private InventoryRepository repo;
    private PartRepository partRepo;
    public InventoryService(InventoryRepository repo, PartRepository partRepo){
        this.repo =repo;
        this.partRepo =partRepo;
    }
    public void addInhousePart(String name, double price, int inStock, int min, int max, int partDynamicValue) throws IllegalArgumentException {
        String validationError = isValidPart(name, price, inStock, min, max, "");
        if (!validationError.isEmpty()) {
            throw new IllegalArgumentException(validationError);
        }

        // Dacă validarea trece, continuă cu crearea și adăugarea piesei
        InhousePart inhousePart = new InhousePart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(inhousePart);
    }

    /*public void addInhousePart(String name, double price, int inStock, int min, int  max, int partDynamicValue){
        InhousePart inhousePart = new InhousePart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(inhousePart);
    }*/
    public void addOutsourcePart(String name, double price, int inStock, int min, int  max, String partDynamicValue){
        OutsourcedPart outsourcedPart = new OutsourcedPart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(outsourcedPart);
    }

    public void addProduct(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts){
        Product product = new Product(repo.getAutoProductId(), name, price, inStock, min, max, addParts);
        repo.addProduct(product);
    }
    public void addPart(String name, double price, int inStock, int min, int  max) throws PartException {
        Part part = new Part(repo.getAutoProductId(), name, price, inStock, min, max);
        partRepo.add(part);
    }
    public ObservableList<Part> getAllParts() {
        return (ObservableList<Part>) partRepo.getAll();
    }
    public List<Part> getParts() {
        return partRepo.getAll();
    }

    public ObservableList<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    public Part lookupPart(String search) {
        return repo.lookupPart(search);
    }

    public Product lookupProduct(String search) {
        return repo.lookupProduct(search);
    }

    public void updateInhousePart(int partIndex, int partId, String name, double price, int inStock, int min, int max, int partDynamicValue){
        InhousePart inhousePart = new InhousePart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, inhousePart);
    }

    public void updateOutsourcedPart(int partIndex, int partId, String name, double price, int inStock, int min, int max, String partDynamicValue){
        OutsourcedPart outsourcedPart = new OutsourcedPart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, outsourcedPart);
    }

    public void updateProduct(int productIndex, int productId, String name, double price, int inStock, int min, int max, ObservableList<Part> addParts){
        Product product = new Product(productId, name, price, inStock, min, max, addParts);
        repo.updateProduct(productIndex, product);
    }

    public void deletePart(Part part){
        repo.deletePart(part);
    }

    public void deleteProduct(Product product){
        repo.deleteProduct(product);
    }

}