use std::env;
extern crate dotenv;
use dotenv::dotenv;

use mongodb::{
    bson::{extjson::de::Error},
    results::{ InsertOneResult},
    Client, Collection,
};
use crate::models::product_model::Product;

pub struct MongoRepo {
    col: Collection<Product>,
}

impl MongoRepo {
    pub async fn init() -> Self {
        dotenv().ok();
        let uri = match env::var("MONGOURI") {
            Ok(v) => v.to_string(),
            Err(_) => format!("Error loading env variable"),
        };
        let client = Client::with_uri_str(uri).unwrap();
        let db = client.database("productsDatabase");
        let col: Collection<User> = db.collection("products");
        MongoRepo { col }
    }

    pub async fn create_product(&self, product: Product) -> Result<InsertOneResult, Error> {
        let result = self.col.insert_one(product, None).await;
        result
    };
    let product = self
        .col
        .insert_one(result, None)
        .await
        .ok()
        .expect("Error creating Product");
    Ok(user)
}

    pub async fn get_product(&self, id: String) -> Result<Product, Error> {
        let result = self
            .col
            .find_one(doc! {"_id": id}, None)
            .await
            .ok()
            .expect("Error getting Product");
        Ok(result)
    }

    pub async fn get_all_products(&self) -> Result<Vec<Product>, Error> {
        let result = self
            .col
            .find(None, None)
            .await
            .ok()
            .expect("Error getting Products");
        Ok(result)
    }

    pub async fn update_product(&self, id: String, product: Product) -> Result<Product, Error> {
        let result = self
            .col
            .find_one_and_replace(doc! {"_id": id}, product, None)
            .await
            .ok()
            .expect("Error updating Product");
        Ok(result)
    }

    pub async fn delete_product(&self, id: String) -> Result<Product, Error> {
        let result = self
            .col
            .find_one_and_delete(doc! {"_id": id}, None)
            .await
            .ok()
            .expect("Error deleting Product");
        Ok(result)
    }
