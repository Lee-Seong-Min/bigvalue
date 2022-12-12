package kr.co.bigvalue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:global-${spring.profiles.active:default}.properties")
public class BigvalueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigvalueApplication.class, args);
    }

}
