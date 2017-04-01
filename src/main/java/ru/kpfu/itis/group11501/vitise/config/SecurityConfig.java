package ru.kpfu.itis.group11501.vitise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.kpfu.itis.group11501.vitise.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kpfu.itis.group11501.vitise.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider, AccessDeniedHandler accessDeniedHandler) {
        this.authProvider = authProvider;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign_in", "/sign_up/**").anonymous()
                .antMatchers("/event_create", "/events/**", "/colleagues/**",
                        "/user/**", "/messages/**", "/news/**", "/profile/**")
                .hasAnyRole("STUDENT", "ADMIN", "WORKER", "DEANERY")
                .antMatchers("/sign_up_request/**")
                .hasAnyRole("DEANERY", "ADMIN");


        http.csrf().disable()
                .formLogin()
                .loginPage("/sign_in")
                .loginProcessingUrl("/login/process")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/sign_in?error=true")
                .and()
                .logout().logoutSuccessUrl("/sign_in")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}