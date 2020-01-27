package net.javaguides.springboot.tutorial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;




@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index").hasAnyRole("USER")
				.antMatchers("/", "users/add").hasAnyRole("USER")
				.antMatchers("/", "users/list").hasAnyRole("USER")
				.antMatchers("/", "users/signup").hasAnyRole("USER")
				.antMatchers("/", "users/edit/**").hasAnyRole("USER")
				.antMatchers("/", "users/delete/**").hasAnyRole("USER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				;
		
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and().withUser("user").password("{noop}user").roles("USER");
    }

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
	 

/*
 * public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
 * 
 * @Autowired private AccessDeniedHandler accessDeniedHandler;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.csrf().disable() .authorizeRequests()
 * .antMatchers("/students/**").hasAnyRole("ADMIN")
 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login")
 * .permitAll(); }
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception { auth.inMemoryAuthentication()
 * .withUser("admin").password("password").roles("ADMIN"); } }
 */
