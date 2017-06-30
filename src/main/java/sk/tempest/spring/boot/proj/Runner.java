package sk.tempest.spring.boot.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("sk.tempest.spring.boot.proj.service")
@EntityScan("sk.tempest.spring.boot.proj.entity")
@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
