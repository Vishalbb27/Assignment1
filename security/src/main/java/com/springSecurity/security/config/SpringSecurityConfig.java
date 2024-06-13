package com.springSecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springSecurity.security.security.JwtAuthenticationEntryPoint;
import com.springSecurity.security.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
	
	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter authenticationFilter;
	
	
	public SpringSecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Used for Basic authentication -> Basic authentication allows to open a pop up window and then send the login details in the form of base64 in the http header
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) -> {
//			authorize.requestMatchers(HttpMethod.GET,"/employee").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.GET,"/api/task").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.POST,"/api/task").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.GET,"/api/task/{id}").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.PUT,"/api/task/{id}").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.DELETE,"/api/task/{id}").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.GET,"/api/task/user/{id}").hasAnyRole("ADMIN","USER");
			authorize.requestMatchers(HttpMethod.GET,"/api/auth/user/{username}").hasAnyRole("ADMIN","USER");
			authorize.requestMatchers(HttpMethod.POST, "/api/task/updateStatus/{id}").hasAnyRole("ADMIN","USER");
			authorize.requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.GET,"/").permitAll();
			//authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll(); //All the users have access to the api and no need for authorization
			authorize.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll();
//			authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		
		
		
		http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
	} 
	
	//Database authentication
	//NO need to provide userDetailsService to authentication manager it automatically loads the the loaduser method from the userdetailsService it will automatically inject
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	//Multiple user using basic authentication -> this is used for in memory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails vishal = User.builder()
//				.username("vishal")
//				.password(passwordEncoder().encode("password"))
//				.roles("USER")
//				.build();
//		
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("password"))
//				.roles("ADMIN")
//				.build();
//		
//		//Store the details in the spring provided in memory database
//		return new InMemoryUserDetailsManager(vishal,admin);
//	}
}
