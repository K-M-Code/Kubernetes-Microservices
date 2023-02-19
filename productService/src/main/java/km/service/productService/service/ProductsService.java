package km.service.productService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import km.service.productService.repositories.ProductsRepository;
import km.service.productService.entities.Products;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(int id) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
    }

    public List<Products> getProductsBySupplierID(int supplierID) {
        return productsRepository.findBySupplierID(supplierID);
    }

    public List<Products> getProductsByCategoryID(int categoryID) {
        return productsRepository.findByCategoryID(categoryID);
    }

    public List<Products> searchProductsByName(String name) {
        return productsRepository.findByProductNameContainingIgnoreCase(name);
    }

    public Products addProduct(Products products) {
        return productsRepository.save(products);
    }

    public Products updateProduct(int id, Products products) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Products existingProduct = optionalProduct.get();
            existingProduct.setSku(products.getSku());
            existingProduct.setProductName(products.getProductName());
            existingProduct.setProductDescription(products.getProductDescription());
            existingProduct.setSupplierID(products.getSupplierID());
            existingProduct.setCategoryID(products.getCategoryID());
            existingProduct.setQuantityPerUnit(products.getQuantityPerUnit());
            existingProduct.setUnitSize(products.getUnitSize());
            existingProduct.setUnitPrice(products.getUnitPrice());
            existingProduct.setMsrp(products.getMsrp());
            existingProduct.setUnitWeight(products.getUnitWeight());
            existingProduct.setUnitsInStock(products.getUnitsInStock());
            existingProduct.setPicture(products.getPicture());
            return productsRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
    }

    public void deleteProduct(int id) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
        	productsRepository.delete(optionalProduct.get());
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
    }

    public List<Products> getProductsInStock() {
        return productsRepository.findByUnitsInStockGreaterThan(0);
    }

    public List<Products> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productsRepository.findByUnitPriceBetween(minPrice, maxPrice);
    }

    public List<Products> getProductsByWeight(double minWeight, double maxWeight) {
        return productsRepository.findByUnitWeightBetween(minWeight, maxWeight);
    }
}



//@Service
//public class ProductsService {
//
//    @Autowired
//    private ProductsRepository productRepository;
//
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Products getProductById(int id) {
//        Optional<Products> product = productRepository.findById(id);
//        return product.orElse(null);
//    }
//
//    public List<Products> getProductsBySupplierID(int supplierID) {
//        return productRepository.findBySupplierID(supplierID);
//    }
//
//    public List<Products> getProductsByCategoryID(int categoryID) {
//        return productRepository.findByCategoryID(categoryID);
//    }
//
//    public List<Products> searchProductsByName(String name) {
//        return productRepository.findByProductNameContainingIgnoreCase(name);
//    }
//
//    public Products addProduct(Products products) {
//        return productRepository.save(products);
//    }
//
//    public Products updateProduct(int id, Products products) {
//        Optional<Products> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            products.setProductID(id);
//            return productRepository.save(products);
//        } else {
//            return null;
//        }
//    }
//
//    public void deleteProduct(int id) {
//        productRepository.deleteById(id);
//    }
//}
