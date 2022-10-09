package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/design","/orders").hasRole("ROLE_USER")
			.antMatchers("/","/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/design")
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.csrf();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
		/*
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(new NoEncodingPasswordEncoder());
		*/
	}
	
	/*
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("user1")
			.password("{noop}password1")
			.authorities("ROLE_USER")
			.and()
			.withUser("user2")
			.password("{noop}password2")
			.authorities("ROLE_USER");
	}
	*/
	
}
