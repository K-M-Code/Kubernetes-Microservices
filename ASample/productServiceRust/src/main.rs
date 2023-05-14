#[macro_use] extern crate rocket;
mod endpoints;
use rocket::fairing::AdHoc;
use rocket::config::Environment;


#[launch]
fn rocket() -> _ {
    rocket::build()
        .attach(AdHoc::on_ignite("MongoDB Config", |rocket| async {
            let client = mongodb::Client::with_uri_str("mongodb://admin:productServiceMongoPassword@localhost:10000/productsDatabase?authSource=admin").await.unwrap();
            rocket.manage(client)
        }))
        .mount("/", routes![endpoints::get_products, endpoints::get_product, endpoints::add_product, endpoints::update_product, endpoints::delete_product])
        .configure(rocket::config::Environment::development().port(8080))
}
