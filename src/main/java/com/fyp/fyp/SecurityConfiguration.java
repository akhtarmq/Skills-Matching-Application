/*
package com.fyp.fyp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/login*").permitAll()
	        .antMatchers("/addUser*").permitAll()
	        .antMatchers("/registerUser*").permitAll()
	        .antMatchers("/checkUsername*").permitAll()
            .antMatchers("/h2-console/**").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()
	        .loginPage("/login")
    	    .defaultSuccessUrl("/loggedin", true)
    	    .successForwardUrl("/loggedin")
    	    .and();

    	http.headers().frameOptions().sameOrigin();
    }
}*/

package com.fyp.fyp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.expressionHandler(webSecurityExpressionHandler())
				.antMatchers("/login*").permitAll()
				.antMatchers("/addUser*").permitAll()
				.antMatchers("/registerUser*").permitAll()
				.antMatchers("/checkUsername*").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				//.loginPage("/login")

				.defaultSuccessUrl("/loggedin", true)
				.successForwardUrl("/loggedin")
				.and()
				//.userDetailsService(apiUserDetailsService())
				.authenticationProvider(authProvider())
		;

		http.headers().frameOptions().sameOrigin();
	}

	// register ApiUserDetailsService as a bean
	@Bean
	public UserDetailsService apiUserDetailsService() {
		return new UserRoleDetailService();
	}

	//Encrypts Password
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(apiUserDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

//	@Autowired
//	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		// get the autowired bean from Spring
//		auth.jdbcAuthentication()
//			.passwordEncoder(new BCryptPasswordEncoder())
//			.and()
//			.userDetailsService(apiUserDetailsService());
//	}

	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "admin > hr \n admin > hm";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy());
		return expressionHandler;
	}
}
