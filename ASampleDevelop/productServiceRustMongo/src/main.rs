mod api;
mod models;
mod repository;

use actix_web::{web::Data, App, HttpServer};
use api::product_api::{create_product, get_product, get_all_products, update_product, delete_product}; 
use repository::mongodb_repo::MongoRepo;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    let db = MongoRepo::init().await;
    let db_data = Data::new(db);
    HttpServer::new(move || {
        App::new()
            .app_data(db_data.clone())
            .service(create_product)
            .service(get_product)
            .service(get_all_products)
            .service(update_product)
            .service(delete_product)
    })
    .bind(("127.0.0.1", 8080))?
    .run()
    .await
}