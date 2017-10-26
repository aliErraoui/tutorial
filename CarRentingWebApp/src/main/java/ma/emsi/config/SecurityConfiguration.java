package ma.emsi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Value("${spring.queries.responsables-query}")
	private String responsablesQuery;

	@Value("${spring.queries.roless-query}")
	private String rolessQuery;

	@Autowired
	SuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		auth.jdbcAuthentication().usersByUsernameQuery(responsablesQuery).authoritiesByUsernameQuery(rolessQuery)
		.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/user/**").access("hasRole('USER')")
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/responsable/**").access("hasRole('RESPONSABLE')")
		.and().csrf().disable().formLogin()
		.loginPage("/login").failureUrl("/login?error=true").successHandler(successHandler)
		.usernameParameter("email").passwordParameter("password").and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
		.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}