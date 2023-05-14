use actix_web::{get, post, put, delete, web, App, HttpResponse, HttpServer, Responder};
use redis::{Commands, Connection};

struct Product {
    id: i32,
    sku: String,
    name: String,
    description: String,
    supplier_id: i32,
    category_id: i32,
    quantity_per_unit: i32,
    unit_size: String,
    unit_price: f32,
    msrp: f32,
    unit_weight: f32,
    units_in_stock: i32,
    picture: String,
}

impl Product {
    fn new(
        id: i32,
        sku: String,
        name: String,
        description: String,
        supplier_id: i32,
        category_id: i32,
        quantity_per_unit: i32,
        unit_size: String,
        unit_price: f32,
        msrp: f32,
        unit_weight: f32,
        units_in_stock: i32,
        picture: String,
    ) -> Self {
        Self {
            id,
            sku,
            name,
            description,
            supplier_id,
            category_id,
            quantity_per_unit,
            unit_size,
            unit_price,
            msrp,
            unit_weight,
            units_in_stock,
            picture,
        }
    }
}

fn get_redis_connection() -> Connection {
    let client = redis::Client::open("redis://127.0.0.1/").unwrap();
    client.get_connection().unwrap()
}

#[get("/products")]
async fn get_products() -> impl Responder {
    let conn = get_redis_connection();
    let product_ids: Vec<i32> = conn.smembers("product_ids").unwrap();
    let mut products: Vec<Product> = vec![];
    for id in product_ids {
        let product_key = format!("product:{}", id);
        let product_data: Vec<String> = conn.hvals(&product_key).unwrap();
        let mut product = Product::new(
            id,
            product_data[0].clone(),
            product_data[1].clone(),
            product_data[2].clone(),
            product_data[3].parse::<i32>().unwrap(),
            product_data[4].parse::<i32>().unwrap(),
            product_data[5].parse::<i32>().unwrap(),
            product_data[6].clone(),
            product_data[7].parse::<f32>().unwrap(),
            product_data[8].parse::<f32>().unwrap(),
            product_data[9].parse::<f32>().unwrap(),
            product_data[10].parse::<i32>().unwrap(),
            product_data[11].clone(),
        );
        products.push(product);
    }
    HttpResponse::Ok().json(products)
}
