package tn.esprit.servicereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ServiceReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceReservationApplication.class, args);
    }

}
