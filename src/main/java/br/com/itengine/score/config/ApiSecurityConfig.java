package br.com.itengine.score.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.itengine.score.config.security.RESTAuthenticationEntryPoint;
import br.com.itengine.score.config.security.RESTAuthenticationFailureHandler;
import br.com.itengine.score.config.security.RESTAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_URL = "/auth";
	private static final String LOGOUT_URL = LOGIN_URL + "/logout";
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select username,password, 1 as enabled from user where username=?")
		.authoritiesByUsernameQuery(
				"select username, role from user where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").authenticated();
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().failureHandler(authenticationFailureHandler);
		http.formLogin().successHandler(authenticationSuccessHandler)
		.loginPage(LOGIN_URL)
		.loginProcessingUrl(LOGIN_URL)
		.usernameParameter("username")
		.passwordParameter("password")
		.permitAll()
		.and()
		.logout().logoutUrl(LOGOUT_URL).permitAll();
	}
	
}
