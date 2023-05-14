use crate::{models::product_model::Product, repository::mongodb_repo::MongoRepo};
use actix_web::{
    post, get, //modify here
    web::{Data, Json, Path}, //modify here
    HttpResponse,
};

#[post("/products")]
pub async fn create_product(db: Data<MongoRepo>, new_product: Json<Product>) -> HttpResponse {
    let product = db.create_product(new_product.into_inner()).await;
    match product {
        Ok(product) => HttpResponse::Ok().json(product),
        Err(err) => HttpResponse::InternalServerError().body(err.to_string()),
    }
}

#[get("/products/{id}")]
pub async fn get_product(db: Data<MongoRepo>, id: Path<String>) -> HttpResponse {
    let product = db.get_product(id.into_inner()).await;
    match product {
        Ok(product) => HttpResponse::Ok().json(product),
        Err(err) => HttpResponse::InternalServerError().body(err.to_string()),
    }
}

#[get("/products/")]
pub async fn get_all_products(db: Data<MongoRepo>) -> HttpResponse {
    let products = db.get_all_products().await;
    match products {
        Ok(products) => HttpResponse::Ok().json(products),
        Err(err) => HttpResponse::InternalServerError().body(err.to_string()),
    }
}

#[update("/products/{id}")]
pub async fn update_product(db: Data<MongoRepo>, id: Path<String>, product: Json<Product>) -> HttpResponse {
    let product = db.update_product(id.into_inner(), product.into_inner()).await;
    match product {
        Ok(product) => HttpResponse::Ok().json(product),
        Err(err) => HttpResponse::InternalServerError().body(err.to_string()),
    }
}

#[delete("/products/{id}")]
pub async fn delete_product(db: Data<MongoRepo>, id: Path<String>) -> HttpResponse {
    let product = db.delete_product(id.into_inner()).await;
    match product {
        Ok(product) => HttpResponse::Ok().json(product),
        Err(err) => HttpResponse::InternalServerError().body(err.to_string()),
    }
}