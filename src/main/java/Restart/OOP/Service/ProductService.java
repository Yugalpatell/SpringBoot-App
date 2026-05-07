package Restart.OOP.Service;

import Restart.OOP.Exception.ProductNotFoundException;
import Restart.OOP.Model.Product;
import Restart.OOP.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService{

    @Autowired
            private ProductRepository productRepository;

    HashMap<Integer, Product> productMap = new HashMap<>();



    public Product addProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id) {

        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));

    }

    public Product updateProduct(int id, Product updateProduct){

        Product exist = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        exist.setName(updateProduct.getName());
        exist.setPrice(updateProduct.getPrice());

        return productRepository.save(exist);
    }

    public void deleteProduct(int id){

        Product exist = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));

       productRepository.delete(exist);
    }

}
