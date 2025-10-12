package com.example.E_com.Product.controller;

import com.example.E_com.Product.services.proservices;
import com.example.E_com.Product.view.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.E_com.Product.services.proservices;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class procontroller {

    @Autowired
    private proservices service;

    @GetMapping("/products")
    public List<Product> getproducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getprod(@PathVariable int id){
        return Optional.ofNullable(service.getProductById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<?> addproduct(
            @RequestPart("product") Product product,
            @RequestPart("imagefile") MultipartFile imagefile) throws IOException {

        try {
            Product saved = service.addOrUpdateProduct(product, imagefile);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getimage(@PathVariable int id){
        Product product = service.getProductById(id);
        byte[] imagefile = product.getImagedata();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImagetype()))
                .body(imagefile);
    }

    @PutMapping(value = "/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {

        service.updateprod(product, id, imageFile);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteprod(@PathVariable int id){
        Product product = service.deleteProduct(id);
        return new ResponseEntity<>("deleted sucessfully",HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
