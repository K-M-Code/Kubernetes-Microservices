package km.service.productService.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ProductID",
"SKU",
"ProductName",
"ProductDescription",
"SupplierID",
"CategoryID",
"QuantityPerUnit",
"UnitSize",
"UnitPrice",
"MSRP",
"UnitWeight",
"UnitsInStock",
"Picture"
})


public class Products implements Serializable
{

@JsonProperty("ProductID")
private Integer productID;
@JsonProperty("SKU")
private String sku;
@JsonProperty("ProductName")
private String productName;
@JsonProperty("ProductDescription")
private String productDescription;
@JsonProperty("SupplierID")
private Integer supplierID;
@JsonProperty("CategoryID")
private Integer categoryID;
@JsonProperty("QuantityPerUnit")
private Integer quantityPerUnit;
@JsonProperty("UnitSize")
private String unitSize;
@JsonProperty("UnitPrice")
private Float unitPrice;
@JsonProperty("MSRP")
private Float msrp;
@JsonProperty("UnitWeight")
private Float unitWeight;
@JsonProperty("UnitsInStock")
private Integer unitsInStock;
@JsonProperty("Picture")
private String picture;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
private final static long serialVersionUID = -1317859771770267010L;

/**
* No args constructor for use in serialization
*
*/
public Products() {
}

/**
*
* @param unitPrice
* @param unitWeight
* @param unitsInStock
* @param supplierID
* @param productID
* @param unitSize
* @param quantityPerUnit
* @param productName
* @param picture
* @param msrp
* @param sku
* @param productDescription
* @param categoryID
*/
public Products(Integer productID, String sku, String productName, String productDescription, Integer supplierID, Integer categoryID, Integer quantityPerUnit, String unitSize, Float unitPrice, Float msrp, Float unitWeight, Integer unitsInStock, String picture) {
super();
this.productID = productID;
this.sku = sku;
this.productName = productName;
this.productDescription = productDescription;
this.supplierID = supplierID;
this.categoryID = categoryID;
this.quantityPerUnit = quantityPerUnit;
this.unitSize = unitSize;
this.unitPrice = unitPrice;
this.msrp = msrp;
this.unitWeight = unitWeight;
this.unitsInStock = unitsInStock;
this.picture = picture;
}

@JsonProperty("ProductID")
public Integer getProductID() {
return productID;
}

@JsonProperty("ProductID")
public void setProductID(Integer productID) {
this.productID = productID;
}

@JsonProperty("SKU")
public String getSku() {
return sku;
}

@JsonProperty("SKU")
public void setSku(String sku) {
this.sku = sku;
}

@JsonProperty("ProductName")
public String getProductName() {
return productName;
}

@JsonProperty("ProductName")
public void setProductName(String productName) {
this.productName = productName;
}

@JsonProperty("ProductDescription")
public String getProductDescription() {
return productDescription;
}

@JsonProperty("ProductDescription")
public void setProductDescription(String productDescription) {
this.productDescription = productDescription;
}

@JsonProperty("SupplierID")
public Integer getSupplierID() {
return supplierID;
}

@JsonProperty("SupplierID")
public void setSupplierID(Integer supplierID) {
this.supplierID = supplierID;
}

@JsonProperty("CategoryID")
public Integer getCategoryID() {
return categoryID;
}

@JsonProperty("CategoryID")
public void setCategoryID(Integer categoryID) {
this.categoryID = categoryID;
}

@JsonProperty("QuantityPerUnit")
public Integer getQuantityPerUnit() {
return quantityPerUnit;
}

@JsonProperty("QuantityPerUnit")
public void setQuantityPerUnit(Integer quantityPerUnit) {
this.quantityPerUnit = quantityPerUnit;
}

@JsonProperty("UnitSize")
public String getUnitSize() {
return unitSize;
}

@JsonProperty("UnitSize")
public void setUnitSize(String unitSize) {
this.unitSize = unitSize;
}

@JsonProperty("UnitPrice")
public Float getUnitPrice() {
return unitPrice;
}

@JsonProperty("UnitPrice")
public void setUnitPrice(Float unitPrice) {
this.unitPrice = unitPrice;
}

@JsonProperty("MSRP")
public Float getMsrp() {
return msrp;
}

@JsonProperty("MSRP")
public void setMsrp(Float msrp) {
this.msrp = msrp;
}

@JsonProperty("UnitWeight")
public Float getUnitWeight() {
return unitWeight;
}

@JsonProperty("UnitWeight")
public void setUnitWeight(Float unitWeight) {
this.unitWeight = unitWeight;
}

@JsonProperty("UnitsInStock")
public Integer getUnitsInStock() {
return unitsInStock;
}

@JsonProperty("UnitsInStock")
public void setUnitsInStock(Integer unitsInStock) {
this.unitsInStock = unitsInStock;
}

@JsonProperty("Picture")
public String getPicture() {
return picture;
}

@JsonProperty("Picture")
public void setPicture(String picture) {
this.picture = picture;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}