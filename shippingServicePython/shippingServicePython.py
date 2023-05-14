from flask import Flask, jsonify, request
from pymongo import MongoClient
from bson.objectid import ObjectId
from bson.json_util import dumps

app = Flask(__name__)
client = MongoClient("mongodb://admin:shippingServiceMongoPassword@localhost:10003/shippingDatabase?authSource=admin")
db = client["shippingDatabase"]
collection = db["shipping"]

# Create a new shipping
@app.route("/shipping", methods=["POST"])
def create_shipping():
    shipping = request.json
    result = collection.insert_one(shipping)
    return jsonify({"message": "Shipping created", "id": str(result.inserted_id)})

# Get all shippings
@app.route("/shipping", methods=["GET"])
def get_shipping():
    shippings = collection.find()
    return dumps(shippings)

# Get a single shipping by ShippingID
@app.route("/shipping/<string:shipping_id>", methods=["GET"])
def get_shipping_by_id(shipping_id):
    shipping = collection.find_one({"ShippingID": shipping_id})
    return dumps(shipping)

# Update an existing shipping
@app.route("/shipping/<string:shipping_id>", methods=["PUT"])
def update_shipping_by_id(shipping_id):
    filter = {"ShippingID": shipping_id}
    new_values = {"$set": request.json}
    result = collection.update_one(filter, new_values)
    if result.modified_count > 0:
        return jsonify({"message": "Shipping updated"})
    else:
        return jsonify({"message": "Shipping not found"})

# Delete an existing shipping
@app.route("/shipping/<string:shipping_id>", methods=["DELETE"])
def delete_shipping_by_id(shipping_id):
    filter = {"ShippingID": shipping_id}
    result = collection.delete_one(filter)
    if result.deleted_count > 0:
        return jsonify({"message": "Shipping deleted"})
    else:
        return jsonify({"message": "Shipping not found"})

if __name__ == "__main__":
    app.run(debug=True, port=3003)
