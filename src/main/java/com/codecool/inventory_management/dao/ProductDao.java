package com.codecool.inventory_management.dao;

import com.codecool.inventory_management.model.Product;
import com.codecool.inventory_management.model.ProductCategory;
import com.codecool.inventory_management.util.ConnectionHandler;
import com.codecool.inventory_management.util.MongoCollectionExtractor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;

public class ProductDao {

    private static ProductDao instance = null;
    public static final String COLLECTION_NAME = "Product";
    private MongoDatabase connection;
    private MongoCollection<Product> collection;


    private ProductDao() {
        this.connection = ConnectionHandler.getInstance().getDatabase();
        this.collection = connection.getCollection(COLLECTION_NAME, Product.class);
    }

    public static ProductDao getInstance() {
        if (instance == null)
            instance = new ProductDao();

        return instance;
    }

    public void add(Product product) {
        collection.insertOne(product);
    }

    public Product getProductBy(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    public void remove(ObjectId id) {
        collection.deleteOne(Filters.eq("_id", id));
    }

    public List<Product> getAllProducts() {
        FindIterable<Product> products = collection.find();
        return MongoCollectionExtractor.extract(products);
    }

    public List<Product> getProductsBy(ProductCategory productCategory) {
        FindIterable<Product> products = collection.find(Filters.eq("product category", productCategory));
        return MongoCollectionExtractor.extract(products);
    }

}
