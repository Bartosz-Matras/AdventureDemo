package pl.matrasbartosz.adventuredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdventureDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdventureDemoApplication.class, args);
    }

}
