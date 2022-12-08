package com.tp3.billingservice;

import com.tp3.billingservice.entities.Bill;
import com.tp3.billingservice.entities.ProductItem;
import com.tp3.billingservice.feign.CustomerServiceClient;
import com.tp3.billingservice.feign.InventoryServiceClient;
import com.tp3.billingservice.model.Customer;
import com.tp3.billingservice.model.Product;
import com.tp3.billingservice.repositories.BillRepository;
import com.tp3.billingservice.repositories.ProductItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableFeignClients @Slf4j
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient){
		return args -> {

			Customer customer1=customerServiceClient.findCustomerById(1L);
			Customer customer2=customerServiceClient.findCustomerById(2L);
			Customer customer3=customerServiceClient.findCustomerById(3L);
			Customer customer4=customerServiceClient.findCustomerById(4L);

			Bill bill1=billRepository.save(new Bill(null,new Date(),null,customer1.getId(),null));
			Bill bill2=billRepository.save(new Bill(null,new Date(),null,customer2.getId(),null));
			Bill bill3=billRepository.save(new Bill(null,new Date(),null,customer3.getId(),null));
			Bill bill4=billRepository.save(new Bill(null,new Date(),null,customer4.getId(),null));
			PagedModel<Product> productPagedModel=inventoryServiceClient.findAll();
			AtomicInteger i= new AtomicInteger();
			productPagedModel.forEach(product -> {

				if(i.get() <=3) {
					ProductItem productItem = new ProductItem();
					productItem.setPrice(product.getPrice());
					productItem.setQuantity(1 + new Random().nextInt(100));
					productItem.setProductID(product.getId());
					productItem.setBill(bill1);
					productItemRepository.save(productItem);
				} else if (i.get() >3 && i.get() <=6) {
					ProductItem productItem = new ProductItem();
					productItem.setPrice(product.getPrice());
					productItem.setQuantity(1 + new Random().nextInt(100));
					productItem.setProductID(product.getId());
					productItem.setBill(bill2);
					productItemRepository.save(productItem);
				}else if (i.get() >6 && i.get() <=9) {
					ProductItem productItem = new ProductItem();
					productItem.setPrice(product.getPrice());
					productItem.setQuantity(1 + new Random().nextInt(100));
					productItem.setProductID(product.getId());
					productItem.setBill(bill3);
					productItemRepository.save(productItem);
				}else {
					ProductItem productItem = new ProductItem();
					productItem.setPrice(product.getPrice());
					productItem.setQuantity(1 + new Random().nextInt(100));
					productItem.setProductID(product.getId());
					productItem.setBill(bill4);
					productItemRepository.save(productItem);
				}
				i.set(i.get() + 1);
			});
		};
	};

}
