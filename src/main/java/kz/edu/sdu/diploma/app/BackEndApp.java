package kz.edu.sdu.diploma.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(
        scanBasePackages = {"kz"}
)
public class BackEndApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BackEndApp.class, args);
    }
}
