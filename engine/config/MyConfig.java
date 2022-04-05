package engine.config;

import engine.logic.Quiz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

  /*  @Bean
    public List<Quiz> listQuiz() {

        System.out.println("kljlkjlkjlk");

        return new ArrayList<Quiz>();
    }

    @Bean
    public String address() {

        System.out.print("gjhgjhgjhgjh");

        return "Green Street, 102";
    }*/

    @Bean
    public PasswordEncoder getEncoder() {
        // Bcrypt est une implémentaion de d'un PasswordEncoder.
        return new BCryptPasswordEncoder();
    }


}
