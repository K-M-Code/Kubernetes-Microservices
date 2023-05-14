package km.service.productService.controllers;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import km.service.productService.entities.Products;
//import km.service.productService.service.ProductsService;


//@RestController
//@RequestMapping("/api/products")
//public class ProductsController {
//
//    @Autowired
//    private ProductsService productsService;
//
//    @GetMapping("/")
//    public List<Products> getAllProducts() {
//        return productsService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Products getProductById(@PathVariable int id) {
//        return productsService.getProductById(id);
//    }
//
//    @GetMapping("/supplier/{supplierID}")
//    public List<Products> getProductsBySupplierID(@PathVariable int supplierID) {
//        return productsService.getProductsBySupplierID(supplierID);
//    }
//
//    @GetMapping("/category/{categoryID}")
//    public List<Products> getProductsByCategoryID(@PathVariable int categoryID) {
//        return productsService.getProductsByCategoryID(categoryID);
//    }
//
//    @GetMapping("/search")
//    public List<Products> searchProductsByName(@RequestParam String name) {
//        return productsService.searchProductsByName(name);
//    }
//
//    @PostMapping("/")
//    public Products addProduct(@RequestBody Products products) {
//        return productsService.addProduct(products);
//    }
//
//    @PutMapping("/{id}")
//    public Products updateProduct(@PathVariable int id, @RequestBody Products products) {
//        return productsService.updateProduct(id, products);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable int id) {
//    	productsService.deleteProduct(id);
//    }
//
//    @GetMapping("/in-stock")
//    public List<Products> getProductsInStock() {
//        return productsService.getProductsInStock();
//    }
//
//    @GetMapping("/by-price")
//    public List<Products> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
//        return productsService.getProductsByPriceRange(minPrice, maxPrice);
//    }
//
//    @GetMapping("/by-weight")
//    public List<Products> getProductsByWeight(@RequestParam double minWeight, @RequestParam double maxWeight) {
//        return productsService.getProductsByWeight(minWeight, maxWeight);
//    }
//}



// V2
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductsController {
//
//    @Autowired
//    private ProductsService productsService;
//
//    @GetMapping("")
//    public List<Products> getAllProducts() {
//        return productsService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Products getProductById(@PathVariable int id) {
//        return productsService.getProductById(id);
//    }
//
//    @GetMapping("/supplier/{supplierID}")
//    public List<Products> getProductsBySupplierID(@PathVariable int supplierID) {
//        return productsService.getProductsBySupplierID(supplierID);
//    }
//
//    @GetMapping("/category/{categoryID}")
//    public List<Products> getProductsByCategoryID(@PathVariable int categoryID) {
//        return productsService.getProductsByCategoryID(categoryID);
//    }
//
//    @GetMapping("/search")
//    public List<Products> searchProductsByName(@RequestParam String name) {
//        return productsService.searchProductsByName(name);
//    }
//
//    @PostMapping("")
//    public Products addProduct(@RequestBody Products products) {
//        return productsService.addProduct(products);
//    }
//
//    @PutMapping("/{id}")
//    public Products updateProducts(@PathVariable int id, @RequestBody Products products) {
//        return productsService.updateProduct(id, products);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable int id) {
//    	productsService.deleteProduct(id);
//    }
//
//    @GetMapping("/instock")
//    public List<Products> getProductsInStock() {
//        return productsService.getProductsInStock();
//    }
//
//    @GetMapping("/pricerange")
//    public List<Products> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
//        return productsService.getProductsByPriceRange(minPrice, maxPrice);
//    }
//
//    @GetMapping("/weight")
//    public List<Products> getProductsByWeight(@RequestParam double minWeight, @RequestParam double maxWeight) {
//        return productsService.getProductsByWeight(minWeight, maxWeight);
//    }
//}



//  Basic Impimentation

//@RestController
//@RequestMapping("/api/products")
//public class ProductsController {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @GetMapping("/{id}")
//    public Products getProductById(@PathVariable int id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("ProductID").is(id));
//        return mongoTemplate.findOne(query, Products.class);
//    }
//
//    @GetMapping
//    public List<Products> getAllProducts() {
//        return mongoTemplate.findAll(Products.class);
//    }
//
//    @PostMapping
//    public Products addProduct(@RequestBody Products products) {
//        return mongoTemplate.save(products);
//    }
//
//    @PutMapping("/{id}")
//    public Products updateProduct(@PathVariable int id, @RequestBody Products product) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("ProductID").is(id));
//        Update update = new Update();
//        update.set("SKU", product.getSku());
//        update.set("ProductName", product.getProductName());
//        update.set("ProductDescription", product.getProductDescription());
//        update.set("SupplierID", product.getSupplierID());
//        update.set("CategoryID", product.getCategoryID());
//        update.set("QuantityPerUnit", product.getQuantityPerUnit());
//        update.set("UnitSize", product.getUnitSize());
//        update.set("UnitPrice", product.getUnitPrice());
//        update.set("MSRP", product.getMsrp());
//        update.set("UnitWeight", product.getUnitWeight());
//        update.set("UnitsInStock", product.getUnitsInStock());
//        update.set("Picture", product.getPicture());
//        mongoTemplate.updateFirst(query, update, Products.class);
//        return mongoTemplate.findOne(query, Products.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable int id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("ProductID").is(id));
//        mongoTemplate.remove(query, Products.class);
//    }
//}




@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<Products> getAllProducts() {
        return mongoTemplate.findAll(Products.class, "products");
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ProductID").is(id));
        return mongoTemplate.findOne(query, Products.class, "products");
    }

    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return mongoTemplate.save(product);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Integer id, @RequestBody Products updatedProduct) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ProductID").is(id));
        // Update the product fields here based on updatedProduct
        // For example: update.set("ProductName", updatedProduct.getProductName());
        mongoTemplate.save(updatedProduct, "products");
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ProductID").is(id));
        mongoTemplate.remove(query, Products.class, "products");
    }
}