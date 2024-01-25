package com.application.psm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/registration", "/js/**", "/css/**", "/img/**").permitAll()
                .requestMatchers("/user/**").authenticated()
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login").permitAll()
        )
        .logout(logout ->
            logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
        );
		return http.build();
	}

}

	  
	 
	  
	/*  
	 * @Autowired
	private UserService userService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Bean
	 public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    
	    http.csrf().disable()
	      .authorizeHttpRequests().requestMatchers("/registration**", "/js/**", "/css/**", "/img/**").permitAll()
	       .anyRequest().authenticated()
	       .and().formLogin().loginPage("/login").permitAll();
	      
	    return http.build();
	}
	
	
	@Override
	 public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    
	    http
	      .authorizeHttpRequests((authz) -> {
	        authz
	          .requestMatchers("/registration**", "/js/**", "/css/**", "/img/**").permitAll() 
	          .anyRequest().authenticated();
	      })
	      .formLogin((form) -> form
	        .loginPage("/login")
	        .defaultSuccessUrl("/index",true)
	        .permitAll()
	      )
	      .logout((logout) -> logout
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/login?logout")  
	      );

	    return http.build();
	}*/

