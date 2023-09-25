package com.warehousemanagement.wms.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    NewUserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
        .csrf().disable().authorizeRequests()
                .antMatchers("/authenticate").permitAll()
//                .antMatchers("/api/administrator/create",
//                        "/api/administrator/read/{id}",
//                        "/api/administrator/readAll",
//                        "/api/administrator/delete/{id}",
//                        "/api/administrator/setWorkedHours",
//                        "/api/administrator/update/{id}",
//                        "/api/administrator/updateAdmin/{id}",
//                        "/api/invoiceReception/**").hasRole("MAIN")
//                .antMatchers("/api/administrator/read/{id}",
//                        "/api/administrator/update/{id}",
//                        "/api/administrator/updateAdmin/{id}",
//                        "/api/invoiceReception/**"
//                        ).hasRole("ADMIN")
                .antMatchers("/api/administrator/create",
                        "/api/administrator/readAll",
                        "/api/administrator/delete/{id}",
                        "/api/administrator/setWorkedHours"
                        ).hasRole("MAIN")
                .antMatchers("/api/administrator/read/{id}",
                        "/api/administrator/update/{id}",
                        "/api/administrator/updateAdmin/{id}",
                        "/api/invoiceReception/**",
                        "/api/position/uploadPosition"
                ).hasAnyRole("ADMIN","MAIN")
                .antMatchers(
                        "/api/invoice/create",
                        "/api/invoice/addOrders",
                        "/api/invoice/validateInvoice"
                ).hasRole("OPERATOR")
                .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public PasswordEncoder getPasswordEncoder() { return new BCryptPasswordEncoder();
   }
}