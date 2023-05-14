const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const axios = require('axios');
const calculateTotalPrice = require('./calculateTotalPrice');

const app = express();
const port = process.env.PORT || 3001;

app.use(bodyParser.json());

// MongoDB connection
const uri = 'mongodb://admin:orderServiceMongoPassword@localhost:10001/ordersDatabase?authSource=admin';
mongoose.connect(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', () => {
  console.log('Connected to MongoDB!');
});

// Schema and Model for 'orders' collection
const orderSchema = new mongoose.Schema({
    OrderID: {
      type: String,
      required: true,
      unique: true,
    },
    CustomerID: {
      type: String,
      required: true,
    },
    OrderedItems: [{
      ProductID: {
        type: String,
        required: true,
      },
      Quantity: {
        type: Number,
        required: true,
        min: 1,
      },
      Price: {
        type: Number,
        required: true,
        min: 0,
      },
    }],
    OrderTotal: {
      type: Number,
      required: true,
      min: 0,
    },
    OrderDate: {
      type: Date,
      required: true,
    },
    OrderStatus: {
      type: String,
      enum: ['Pending', 'Processing', 'Shipped', 'Delivered'],
      required: true,
    },
  });
  
  const Order = mongoose.model('Order', orderSchema);
  
  // API routes
  app.get('/orders', async (req, res) => {
    try {
      const orders = await Order.find();
      res.status(200).json(orders);
    } catch (error) {
      res.status(500).json({ message: 'Error retrieving orders.' });
    }
  });

  app.post('/orders', async (req, res) => {
    console.log(req.body); // Log the request payload to the console
    try {
      const order = new Order(req.body);
      await order.save();
      res.status(201).json(order);
    } catch (error) {
      res.status(500).json({ message: 'Error saving order.' });
    }
  });


app.put('/orders/:OrderID', async (req, res) => {
    try {
      const { OrderID } = req.params;
      const updatedOrder = await Order.findOneAndUpdate(
        { OrderID },
        { $set: req.body },
        { new: true }
      );
      if (!updatedOrder) {
        return res.status(404).json({ message: 'Order not found.' });
      }
      res.status(200).json(updatedOrder);
    } catch (error) {
      res.status(500).json({ message: 'Error updating order.' });
    }
  });
  
  
  
  app.delete('/orders/:OrderID', async (req, res) => {
    try {
      const { OrderID } = req.params;
      const order = await Order.findOneAndDelete({ OrderID });
      if (!order) {
        return res.status(404).json({ message: 'Order not found.' });
      }
      res.status(200).json({ message: 'Order deleted successfully.' });
    } catch (error) {
      res.status(500).json({ message: 'Error deleting order.' });
    }
  });
  
  app.get('/orders/totalPrice', async (req, res) => {
    try {
      const orderTotalPrice = await calculateTotalPrice();
      res.status(200).json({ totalPrice: orderTotalPrice });
    } catch (error) {
      res.status(500).json({ message: 'Error retrieving total price.' });
    }
  });

  // Multiply endpoint
  // app.get('/orders/totalPrice', async (req, res) => {
  //   try {
  //     const orders = await Order.find();
  //     let orderTotalPrice = 0;
  //     for (const order of orders) {
  //       for (const item of order.OrderedItems) {
  //         const { ProductID, Quantity, Price } = item;
  //         const result = await axios.post('http://www.dneonline.com/calculator.asmx?op=Multiply', `<intA>${Price}</intA><intB>${Quantity}</intB>`);
  //         const totalPrice = result.data.querySelector('AddResult').textContent;
  //         orderTotalPrice += parseFloat(totalPrice);
  //       }
  //     }
  //     res.status(200).json({ totalPrice: orderTotalPrice });
  //   } catch (error) {
  //     console.error(error);
  //     res.status(500).json({ message: 'Error retrieving total price.' });
  //   }
  // });
  
  
// Start server
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
  });
  
  
  process.on('unhandledRejection', (reason, promise) => {
      console.error('Unhandled Rejection:', reason);
    });