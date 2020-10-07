package com.meni.server;

import com.meni.server.repo.Ad;
import com.meni.server.repo.AdsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(AdsRepository repo){
//        return args -> {
//          repo.save( new Ad() );
//        };
//    }

}
