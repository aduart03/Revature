/*
To test everything you've learned, you are now the lead database architect for GadgetGalaxy,
a fast-growing online tech store. You will build and manage a new database called gadgetStore.

REQUIREMENTS:
Create a collection named products with a JSON Schema validator requiring:
name (must be a string)
price (must be an integer or double)
inStock (must be a boolean)
Use insertMany() to add at least three different gadgets (e.g., Wireless Mouse, Mechanical Keyboard, Gaming Monitor).
Make sure they match your schema, and include a nested specs sub-document with a brand field (e.g., specs: { brand: "Logitech" }).
Test your validation by trying to insertOne() a product that violates your rules (like missing the price field or giving it the wrong data type).

REQUIREMENTS CONTINUED:

Choose one product and use updateOne() with $set to add a new top-level field called category with the value "Accessories".
Use $inc to increase its price by 15 dollars.
Add an array field called tags to that product using $push to add "wireless". Then, use $push again to add "bestseller".
Decide you don't want "wireless" after all, and use $pull to remove it from the tags array.

Find all products priced greater than or equal to a certain amount using $gte.

Find all products made by a specific brand using dot notation (e.g., "specs.brand").

Find products whose category matches one in a list using $in.


Create a second collection named orders.
Insert a document into orders that links a product's _id to an order (e.g., { productId: <ObjectId_from_product>, quantity: 2 }).

Write an aggregation pipeline on the orders collection using $lookup and $unwind to join orders with products.
Use $project to output a clean customer receipt showing:
The product name ($product.name)
The ordered quantity (quantity)
Hiding the _id field.


*/

use ('gadgetStore');

// Drop first so you don't get the already exist error
db.products.drop();

// 1. Create the collection with the validator
// by default mongo assigns an id automatically to every record inserted to the db
db.createCollection('products', {
  validator: {
    $jsonSchema: {
      bsonType: 'object',
      required: ['name', 'price', 'inStock'],
      properties: {
        name: {
          bsonType: 'string',
          description: 'name must be a string and is required'
        },
        price: {
          bsonType: ['int', 'double'],
          description: 'price must be an integer or double and is required'
        },
        inStock: {
          bsonType: 'bool',
          description: 'inStock must be a boolean and is required'
        },
        specs: {
          bsonType: 'object',
          properties: {
            brand: { bsonType: 'string' }
          }
        }
      }
    }
  }
});

// 2. Insert three valid gadgets
db.products.insertMany([
  { name: 'Wireless Mouse', price: 29.99, inStock: true, specs: { brand: 'Logitech' } },
  { name: 'Mechanical Keyboard', price: 89.99, inStock: true, specs: { brand: 'Keychron' } },
  { name: 'Gaming Monitor', price: 249, inStock: false, specs: { brand: 'ASUS' } }
]);

// 3. Test validation with a bad insert (price is a string)
try {
  db.products.insertOne({ name: 'USB Hub', price: 'twenty', inStock: true });
} catch (e) {
  console.log('Validation failed as expected:', e.message);
}

db.products.find();

db.products.updateOne(
    {name : 'Wireless Mouse'}, // Which product to modify, get it by a field
    {
        $set: {category : 'Accessories'},   // Update: what to change
        $inc: { price: 15}
    }
);

db.products.updateOne(
    {name : 'Wireless Mouse' },
    {$push : {tags : 'wireless'} }
);

db.products.updateOne(
    {name : 'Wireless Mouse'},
    {$push: {tags: 'bestseller'} }
);

db.products.updateOne(
    {name : 'Wireless Mouse'},
    {$pull: {tags: ['bestseller']}}
);

db.products.find();

// Find all products priced greater than or equal to a certain amount using $gte.
db.products.find(
    {price : {$gte: 50}}
);

// Find all products made by a specific brand using dot notation (e.g., "specs.brand").
db.products.find({"specs.brand" : "Logitech"});

// Find products whose category matches one in a list using $in.
db.products.updateOne(

    {name: 'Mechanical Keyboard'},    // get specific item by the name -> what is going to be updated
    {
        $set: {category : 'Peripherals'} // Update -> what to change
    }
);
db.products.find({category : { $in : ['Accessories', 'Peripherals'] } });


// drop to avoid 'already exist' error
db.orders.drop();

// Create a second Collection named orders 
db.createCollection('orders', {
    validator:{
        $jsonSchema:{
            bsonType: 'object', //josnb type: object type
            // columns that must have data
            required: ['product_id', 'quantity'], 
            // properties/ constraints / value types   
            properties:{
                product_id:{
                    bsonType : 'objectId',
                    description: 'Must be object will be matched with id from product collection'
                },
                quantity:{
                    bsonType: 'int',
                    description : 'Must have a quantity of product, and must be int'
                }

            }  

        }
    }

});


// Insert a document into orders that links a product's _id to an order (e.g., { productId: <ObjectId_from_product>, quantity: 2 }).
const mouse = db.products.findOne({name : 'Wireless Mouse' });

db.orders.insertOne({ product_id : mouse._id, quantity : 2});

db.orders.find();

/*
Write an aggregation pipeline on the orders collection using $lookup and
$unwind to join orders with products.

Use $project to output a clean customer receipt showing:
The product name ($product.name)
The ordered quantity (quantity)
Hiding the _id field.

db.collection.aggregate([
  {
    $lookup: {
      from: "foreignCollection",    // The collection you want to join with
      localField: "localKeyField",  // Field from the input (left) collection
      foreignField: "foreignKey",   // Field from the target (right) collection
      as: "joinedArrayName"         // The name of the new array field to output
    }
  }
])
*/

db.orders.aggregate([
    { $lookup:{
        from: 'products',
        localField: 'product_id',
        foreignField: '_id',
        as: 'ordersAndProducts'
        }
    },
    { $unwind : '$ordersAndProducts' },
    { $project : {
        _id : 0,
        product_name : '$ordersAndProducts.name',
        order_quantity : '$quantity'

        }

    }

]);
