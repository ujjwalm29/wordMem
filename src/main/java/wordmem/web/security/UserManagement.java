package wordmem.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class UserManagement extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return NoOpPasswordEncoder.getInstance();
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder())
                .usersByUsernameQuery("select username,password,enabled from users where username = ?");
        // auth.inMemoryAuthentication().withUser("buzz").password("{noop}infinity").authorities("ROLE_USER").and()
        // .withUser("woody").password("{noop}bullseye").authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/**/*.js", "/**/*.css", "/register", "/confirm")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/userhome", true).permitAll().and().logout().permitAll();
        ;
    }

}