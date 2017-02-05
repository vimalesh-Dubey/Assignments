package com.vimal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import com.vimal.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
	

	    private TokenStore tokenStore = new InMemoryTokenStore();

	    @Autowired
	    private AuthenticationManager authenticationManager;

	   /* @Autowired
	    private UserDetailsService userDetailsService;
	    
	    
*/
	    @Autowired
	    private DataSource dataSource;
	    
	    @Autowired
	    private MyDBAuthenticationService MyDBAuthenticationService;
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	            throws Exception {
	        // @formatter:off
	        endpoints
	            .tokenStore(this.tokenStore)
	            .authenticationManager(this.authenticationManager);
	            //.userDetailsService(userDetailsService);
	        // @formatter:on
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        // @formatter:off
	        clients
	            .inMemory()
	                .withClient("clientapp")
	                    .authorizedGrantTypes("password", "refresh_token")
	                    .authorities("USER")
	                    .scopes("read", "write")
	                    .secret("123456");
	        
	        //.resourceIds(RESOURCE_ID)
	        // @formatter:on
	    }

	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices tokenServices = new DefaultTokenServices();
	        tokenServices.setSupportRefreshToken(true);
	        tokenServices.setTokenStore(this.tokenStore);
	        return tokenServices;
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	        // Users in memory.
	 
	       // auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
	       // auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
	 
	        // For User in database.
	      //  auth.userDetailsService(MyDBAuthenticationService);
	   
	 
	    }
	    
	    
	    protected void configure(HttpSecurity http) throws Exception {
	 
	        http.csrf().disable();
	 
	        // The pages does not require login
	        http.authorizeRequests().antMatchers("/", "/welcome", "/Login", "/logout").authenticated();
	 
	        // /userInfo page requires login as USER or ADMIN.
	        // If no login, it will redirect to /login page.
	        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
	 
	        // For ADMIN only.
	        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	 
	        // When the user has logged in as XX.
	        // But access a page that requires role YY,
	        // AccessDeniedException will throw.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 
	        // Config for Login Form
	        http.authorizeRequests().and().formLogin()
	                // Submit URL of login page.
	                .loginProcessingUrl("/login") // Submit URL
	                .loginPage("/Login")//
	                .defaultSuccessUrl("/userInfo")//
	                .failureUrl("/login?error=true")//
	                .usernameParameter("username")//
	                .passwordParameter("password")
	                // Config for Logout Page
	                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	        
	        
	 
	    }
	

	public OAuth2Config() {
		// TODO Auto-generated constructor stub
	}

}
