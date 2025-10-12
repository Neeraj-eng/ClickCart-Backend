package com.example.E_com.Product.services;

import com.example.E_com.Product.repositry.prorepo;
import com.example.E_com.Product.view.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class proservices {
    @Autowired
    private prorepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {

        product.setImagename(image.getOriginalFilename());
        product.setImagetype(image.getContentType());
        product.setImagedata(image.getBytes());

        return productRepo.save(product);

    }


    public Product deleteProduct(int id) {
        productRepo.deleteById(id);
        return null;
    }

    public Product updateprod(Product product, int id, MultipartFile imageFile) throws IOException {
//        product1.get().setBrand(product.getBrand());
//        product1.get().setCategory(product.getCategory());
//        product1.get().setDescription(product.getDescription());
//        product1.get().setName(product.getName());
//        product1.get().setPrice(product.getPrice());
//        product1.get().setQuantity(product.getQuantity());
//        product1.get().setRelease_date(product.getRelease_date());

        product.setImagename(imageFile.getOriginalFilename());
        product.setImagedata(imageFile.getBytes());
        product.setImagetype(imageFile.getContentType());
        return productRepo.save(product);
    }


    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
