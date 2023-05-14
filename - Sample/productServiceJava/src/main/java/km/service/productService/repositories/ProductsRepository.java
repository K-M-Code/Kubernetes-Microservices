package km.service.productService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import km.service.productService.entities.Products;

@Repository
public interface ProductsRepository extends MongoRepository<Products, Integer> {
    List<Products> findBySupplierID(int supplierID);
    List<Products> findByCategoryID(int categoryID);
    List<Products> findByProductNameContainingIgnoreCase(String productName);
	List<Products> findByUnitsInStockGreaterThan(int i);
	List<Products> findByUnitPriceBetween(double minPrice, double maxPrice);
	List<Products> findByUnitWeightBetween(double minWeight, double maxWeight);
}
