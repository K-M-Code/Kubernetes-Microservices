const express = require('express');
const mongoose = require('mongoose');
const app = express();
const port = process.env.PORT || 3000;

// MongoDB connection
const uri = 'mongodb://admin:productServiceMongoPassword@localhost:10000/productsDatabase?authSource=admin';
mongoose.connect(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', () => {
  console.log('Connected to MongoDB!');
});

// Schema and Model for 'products' collection
const productSchema = new mongoose.Schema({
    ProductID: {
      type: Number,
      required: true,
      unique: true,
    },
    SKU: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      minlength: 3,
      maxlength: 255,
    },
    ProductName: {
      type: String,
      required: true,
      trim: true,
      minlength: 3,
      maxlength: 255,
    },
    ProductDescription: {
      type: String,
      required: false,
      trim: true,
      maxlength: 1000,
    },
    SupplierID: {
      type: Number,
      required: true,
    },
    CategoryID: {
      type: Number,
      required: true,
    },
    QuantityPerUnit: {
      type: Number,
      required: true,
    },
    UnitSize: {
      type: String,
      required: true,
      trim: true,
    },
    UnitPrice: {
      type: Number,
      required: true,
      min: 0,
    },
    MSRP: {
      type: Number,
      required: true,
      min: 0,
    },
    UnitWeight: {
      type: Number,
      required: true,
      min: 0,
    },
    UnitsInStock: {
      type: Number,
      required: true,
      min: 0,
    },
    Picture: {
      type: String,
      required: false,
      trim: true,
    },
  });
  
  

const Product = mongoose.model('Product', productSchema);

// Middleware
app.use(express.json());

// API routes
app.get('/products', async (req, res) => {
  try {
    const products = await Product.find();
    res.status(200).json(products);
  } catch (error) {
    res.status(500).json({ message: 'Error retrieving products.' });
  }
});

app.post('/products', async (req, res) => {
  try {
    const product = new Product(req.body);
    await product.save();
    res.status(201).json(product);
  } catch (error) {
    res.status(500).json({ message: 'Error saving product.' });
  }
});

app.put('/products/:ProductID', async (req, res) => {
    try {
      const { ProductID } = req.params;
      const product = await Product.findOneAndUpdate(
        { ProductID },
        { $set: { [Object.keys(req.body)[0]]: Object.values(req.body)[0] } },
        { new: true }
      );
      if (!product) {
        return res.status(404).json({ message: 'Product not found.' });
      }
      res.status(200).json(product);
    } catch (error) {
      res.status(500).json({ message: 'Error updating product.' });
    }
  });
  
  
  
  
  app.delete('/products/:ProductID', async (req, res) => {
    try {
      const { ProductID } = req.params;
      const product = await Product.findOneAndDelete({ ProductID });
      if (!product) {
        return res.status(404).json({ message: 'Product not found.' });
      }
      res.status(200).json({ message: 'Product deleted successfully.' });
    } catch (error) {
      res.status(500).json({ message: 'Error deleting product.' });
    }
  });

// Start server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});


process.on('unhandledRejection', (reason, promise) => {
    console.error('Unhandled Rejection:', reason);
  });