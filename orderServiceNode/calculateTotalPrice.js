const axios = require('axios');
// const Order = require('./models/Order');

// Define an async function to calculate the total price for a single order
async function calculateTotalPrice(order) {
    let totalPrice = 0;
    for (const item of order.OrderedItems) {
      const { ProductID, Quantity, Price } = item;
      const result = await axios.post('https://www.dataaccess.com/webservicesserver/NumberConversion.wso?op=NumberToDollars', `<dNum>${Price}</dNum>`);
      const itemTotalPrice = result.data.querySelector('AddResult').textContent;
      totalPrice += itemTotalPrice;
    }
    return totalPrice;
  }

module.exports = calculateTotalPrice;
