package ftn.uns.ac.rs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMicroServiceApplication.class, args);
	}

}
