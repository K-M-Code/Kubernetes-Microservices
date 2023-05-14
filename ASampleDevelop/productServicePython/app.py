from flask import Flask, jsonify, request
from pymongo import MongoClient
from bson.objectid import ObjectId
from bson.json_util import dumps

app = Flask(__name__)
client = MongoClient("mongodb://admin:productServiceMongoPassword@localhost:10000/productsDatabase?authSource=admin")
db = client["productsDatabase"]
collection = db["products"]

# Create a new product
@app.route("/products", methods=["POST"])
def create_product():
    product = request.json
    result = collection.insert_one(product)
    return jsonify({"message": "Product created", "id": str(result.inserted_id)})

# Get all products
@app.route("/products", methods=["GET"])
def get_products():
    products = collection.find()
    return dumps(products)

# Get a single product by SKU
@app.route("/products/<int:product_id>", methods=["GET"])
def get_product_by_product_id(product_id):
    product = collection.find_one({"ProductID": product_id})
    return dumps(product)

# Update an existing product
@app.route("/products/<int:product_id>", methods=["PUT"])
def update_product_by_id(product_id):
    filter = {"ProductID": product_id}
    new_values = {"$set": request.json}
    result = collection.update_one(filter, new_values)
    if result.modified_count > 0:
        return jsonify({"message": "Product updated"})
    else:
        return jsonify({"message": "Product not found"})

# Delete an existing product
@app.route("/products/<int:product_id>", methods=["DELETE"])
def delete_product_by_id(product_id):
    filter = {"ProductID": product_id}
    result = collection.delete_one(filter)
    if result.deleted_count > 0:
        return jsonify({"message": "Product deleted"})
    else:
        return jsonify({"message": "Product not found"})

if __name__ == "__main__":
    app.run(debug=True)
