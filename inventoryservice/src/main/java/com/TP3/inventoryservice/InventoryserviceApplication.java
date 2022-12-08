package com.TP3.inventoryservice;

import com.TP3.inventoryservice.entities.Product;
import com.TP3.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {

			productRepository.save(new Product(null,"Computer Desk Top HP",900,20));
			productRepository.save(new Product(null,"Printer Epson",80,50));
			productRepository.save(new Product(null,"Parliament Smart Wallet",20,47.99));
			productRepository.save(new Product(null,"Collapsible Umbrella",100,74.99));
			productRepository.save(new Product(null,"AirTag Tracker (4-Pack)",42,150));
			productRepository.save(new Product(null,"Link & Lock Carabiner for Apple AirTags",50,99.99));
			productRepository.save(new Product(null,"Shiatsu Back and Neck Massager",15,50));
			productRepository.save(new Product(null,"Echo Dot (5th Gen)",20,10));
			productRepository.save(new Product(null,"G502 X LIGHTSPEED",60,150));
			productRepository.save(new Product(null,"Nord N20 5G Android Smartphone",25,500));
			productRepository.save(new Product(null,"Elite 4 Active Wireless Earbuds",69,98.99));

			productRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});

		};

	}
}
