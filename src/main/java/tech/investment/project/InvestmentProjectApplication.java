package tech.investment.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestmentProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentProjectApplication.class, args);
	}

}
