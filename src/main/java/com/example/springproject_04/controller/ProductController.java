package com.example.springproject_04.controller;

import com.example.springproject_04.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsList(HttpServletRequest request)
    {
        try {
            String uri="http://localhost:8080/api/products?";
            Set<String> parameterNames=request.getParameterMap().keySet();
            String parameterName = parameterNames.stream().findFirst().orElse(null);
            if(parameterName == null)
            {
                //no change to uri
            }
            else
            {
                uri=uri+parameterName+"="+request.getParameter(parameterName);
            }
            RestTemplate restTemplate=new RestTemplate();
            ResponseEntity<Product[]> productValue=restTemplate.getForEntity(uri,Product[].class);
            List<Product> products= Arrays.asList(productValue.getBody());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception occured when retrieving list of products");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId)
    {
        String uri="http://localhost:8080/api/products/"+productId;
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Product> productValue=restTemplate.getForEntity(uri,Product.class);
        Product product= productValue.getBody();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
