package com.example.springbootfirst.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.
                csrf((CsrfConfigurer<HttpSecurity> csrf)-> csrf.disable())
                .authorizeHttpRequests(auth->{
//              auth.requestMatchers(HttpMethod.POST,"/employee").hasRole("ADMIN");
//              auth.requestMatchers(HttpMethod.PUT,"/employee").hasRole("ADMIN");
//              auth.requestMatchers(HttpMethod.DELETE,"/employee").hasRole("ADMIN");
//              auth.requestMatchers(HttpMethod.GET,"/**").hasAnyRole("ADMIN","USER");
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());
//        .httpBasic(Customizer.withDefaults())
//          -      - is what hides the default HTML login form and shows the browser pop-up login window instead.

//        .formLogin(Customizer.withDefaults()); -- > for form
        return http.build();
    }


//    we are commenting this , when we add CustomUserDeatilsService since when we add that we don't need this
//    This is no longer needed since you’re using database-based authentication.
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}