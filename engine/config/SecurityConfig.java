package engine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // pour les tests on enlève contrôle CSRF and X-Frame-Options

        http.csrf().disable().headers().frameOptions().disable();

         /*
         MVC Matcher est mieux que AntMatcher !!! Car avec AntMatcher il y a des risques de laisser
         des URL sans protection :
         Spring Security also provides Ant matchers that are similar to MVC matchers mentioned above.
   The difference is that Ant matchers match only exact URLs. For example, antMachers("/secured")
   will match only /secured, while mvcMatchers("/secured") will match /secured, as well as /secured/ and /secured.html.
   It is recommended to use MVC matchers to avoid situations where some paths are mistakenly left unprotected.*/

     /*  A developer who is unaware of this could use Ant matchers and leave
       a path unprotected without noticing it, which can create a major security breach in an application. It doesn't mean we should never use Ant matches, though. We can still secure two paths using
       Ant matchers like this: antMatchers("/admin", "/admin/").*/

        // seul api/register ne doit pas être controler par formLogin
        http.authorizeRequests()
                .antMatchers("/api/register").permitAll() // Pas besoin de login
                .antMatchers("/actuator/shutdown").permitAll()
                .anyRequest().authenticated() // besoin d'un login
                .and()
                .formLogin() // (2)
                .and()
                .httpBasic();
    }


}
