package SJTU.SE.courseAffair.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


/*@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "SJTU")
@EnableScheduling
@EnableCaching
public class ServletInitializer extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootApplication.class);
    }
}*/
