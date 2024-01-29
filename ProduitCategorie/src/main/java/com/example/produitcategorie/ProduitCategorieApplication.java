package com.example.produitcategorie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProduitCategorieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitCategorieApplication.class, args);
    }

}
