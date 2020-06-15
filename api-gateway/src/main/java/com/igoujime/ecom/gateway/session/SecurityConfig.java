package com.igoujime.ecom.gateway.session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.igoujime.ecom.gateway.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          //.withUser("default");
          .withUser("admin")
          .password(passwordEncoder().encode("password"))
          .roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
          .httpBasic().and()
          .authorizeRequests()
          .antMatchers("/")          
          .hasRole("ADMIN")
          //.anyRequest().authenticated();
         // .and()
          //.formLogin()
          //.loginPage("/login")
          //.permitAll()
          .and()
          .logout()
         // .logoutUrl("/perform_logout")
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID");
          //.anyRequest().authenticated();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   /* @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                //.hasRole("USER")
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll();
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  /*  @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
        
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userAccountInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
 
    }*/
}