use mongodb::bson::oid::ObjectId;
use serde::{Serialize, Deserialize};

#[derive(Debug, Serialize, Deserialize)]
pub struct Product {
    #[serde(rename = "_id")]
    id: Option<bson::oid::ObjectId>,
    ProductID: i32,
    SKU: String,
    ProductName: String,
    ProductDescription: String,
    SupplierID: i32,
    CategoryID: i32,
    QuantityPerUnit: i32,
    UnitSize: String,
    UnitPrice: f64,
    MSRP: f64,
    UnitWeight: f64,
    UnitsInStock: i32,
    Picture: String,
}