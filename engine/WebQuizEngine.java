package engine;

import engine.config.MyConfig;
import engine.controller.Controller;
import engine.logic.Converter;
import engine.logic.Quiz;
import engine.repository.QuizRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

// Je ne comprends pas j'ai été obligé de forcer la lecture de la class myConfig ???
// le projet est pourtant bien structuré ????
//@ComponentScan(basePackageClasses = {MyConfig.class, Controller.class, Converter.class, QuizRepository.class})
//??????????????????????????????
//@ComponentScan(basePackageClasses = MyConfig.class)


// IMPOSSIBLE DE COMPRENDRE J'AI VOULU FAIRE UN BEAN AVE ARRAYLIST UTILISE DANS
// LA CLASS REPOSITORY. BEAN DECLARER DANS LA CLASS MYCONFIG AVEC @CONFIGURATION
// ==> 1) PLANTAGE CAR QUAND REPOSITORY EST CREE le bean n'existe pas ???
// ok voir la règles comment les bean sont créer et dans quel ordre
// 2) je force la lecture de myconfig par @ComponentScan ... ça passe
// mais après les URL ne fonctionne pas ???? Aucun message d'erreur RIEN !!!!!!!!!!!!
// 3) j'ai fini pas me passer du bean et déclarer l'Arraylist directe dans REPOSITORy ???

public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }


}
