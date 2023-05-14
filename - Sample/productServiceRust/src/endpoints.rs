use rocket::{get, post, put, delete, response::status, serde::json::Json};
use rocket_contrib::json::Json;
use serde::{Deserialize, Serialize};
use mongodb::{Client, Collection};
use bson::{doc, Bson};

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

fn get_collection(client: &rocket::State<'_, mongodb::Client>) -> Collection {
    client.database("productsDatabase").collection("products")
}

#[get("/products")]
fn get_products(client: &rocket::State<'_, mongodb::Client>) -> Json<Vec<Product>> {
    let collection = get_collection(client);
    let cursor = collection.find(None, None).unwrap();
    let products: Vec<Product> = cursor
        .map(|result| {
            let product: Product = bson::from_bson(Bson::Document(result.unwrap())).unwrap();
            product
        })
        .collect();
    Json(products)
}

#[get("/products/<id>")]
fn get_product(id: String, client: &rocket::State<'_, mongodb::Client>) -> Option<Json<Product>> {
    let collection = get_collection(client);
    let filter = doc! {"ProductID": id.parse::<i32>().ok()?};
    let result = collection.find_one(filter, None).unwrap()?;
    let product: Product = bson::from_bson(Bson::Document(result)).unwrap();
    Some(Json(product))
}

#[post("/products", data = "<product>")]
fn add_product(product: Json<Product>, client: &rocket::State<'_, mongodb::Client>) -> status::Created<Json<Product>> {
    let collection = get_collection(client);
    let product = product.into_inner();
    let result = collection.insert_one(bson::to_document(&product).unwrap(), None).unwrap();
    let product = Product {
        id: result.inserted_id.as_object_id().map(|oid| oid.clone()),
        ..product
    };
    status::Created(format!("/products/{}", product.ProductID).to_string(), Some(Json(product)))
}

#[put("/products/<id>", data = "<product>")]
fn update_product(id: String, product: Json<Product>, client: &rocket::State<'_, mongodb::Client>) -> Option<Json<Product>> {
    let collection = get_collection(client);
    let filter = doc! {"ProductID": id.parse::<i32>().ok()?};
    let product = product.into_inner();
    let result = collection.find_one_and_replace(filter, bson::to_document(&product).unwrap(), None).unwrap()?;
    let product: Product = bson::from_bson(Bson::Document(result)).unwrap();
    Some(Json(product))
}

#[delete("/products/<id>")]
fn delete_product(id: String, client: &rocket::State<'_, mongodb::Client>) -> Option<Json<Product>> {
    let collection = get_collection(client);
    let filter = doc! {"ProductID": id.parse::<i32>().ok()?};
    let result = collection.find_one_and_delete(filter, None).unwrap()?;
    let product: Product = bson::from_bson(Bson::Document(result)).unwrap();
    Some(Json(product))
}

