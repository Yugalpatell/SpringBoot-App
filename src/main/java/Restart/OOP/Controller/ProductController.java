package Restart.OOP.Controller;

import Restart.OOP.DTOs.ProductDTO;
import Restart.OOP.Model.Product;
import Restart.OOP.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    List<Product> productList = new ArrayList<>();


    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@Valid  @RequestBody ProductDTO productDTO){

        Product product1 = new Product();

        product1.setName(productDTO.getName());
        product1.setPrice(productDTO.getPrice());

       return ResponseEntity.ok(productService.addProduct(product1));
    }


    @GetMapping("/products/{id}")
    public Product getProductByID(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {

        return ResponseEntity.ok(productService.updateProduct(id, product));

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        productService.deleteProduct(id);

        return ResponseEntity.ok("Deleted!");
    }


}
