from flask import Flask, jsonify, request
from pymongo import MongoClient
from bson.objectid import ObjectId
from bson.json_util import dumps

app = Flask(__name__)
client = MongoClient("mongodb://admin:customerServiceMongoPassword@localhost:10002/productsDatabase?authSource=admin")
db = client["customersDatabase"]
collection = db["customers"]

# Create a new customer
@app.route("/customers", methods=["POST"])
def create_customer():
    customer = request.json
    result = collection.insert_one(customer)
    return jsonify({"message": "Customer created", "id": str(result.inserted_id)})

# Get all customers
@app.route("/customers", methods=["GET"])
def get_customers():
    customers = collection.find()
    return dumps(customers)

# Get a single customer by CustomerID
@app.route("/customers/<string:customer_id>", methods=["GET"])
def get_customer_by_id(customer_id):
    customer = collection.find_one({"CustomerID": customer_id})
    return dumps(customer)

# Update an existing customer
@app.route("/customers/<string:customer_id>", methods=["PUT"])
def update_customer_by_id(customer_id):
    filter = {"CustomerID": customer_id}
    new_values = {"$set": request.json}
    result = collection.update_one(filter, new_values)
    if result.modified_count > 0:
        return jsonify({"message": "Customer updated"})
    else:
        return jsonify({"message": "Customer not found"})

# Delete an existing customer
@app.route("/customers/<string:customer_id>", methods=["DELETE"])
def delete_customer_by_id(customer_id):
    filter = {"CustomerID": customer_id}
    result = collection.delete_one(filter)
    if result.deleted_count > 0:
        return jsonify({"message": "Customer deleted"})
    else:
        return jsonify({"message": "Customer not found"})

if __name__ == "__main__":
    app.run(debug=True, port=3002)