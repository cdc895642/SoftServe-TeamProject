package com.softserve.teamproject.config;

import com.softserve.teamproject.controller.CustomSavedRequestAwareAuthenticationSuccessHandler;
import com.softserve.teamproject.controller.RestAuthenticationEntryPoint;
import com.softserve.teamproject.service.impl.UserDetailsServiceImpl;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * The class provides basic security configurations, configures log-in and log-out process as well
 * as permissions given to the users to access certain urls.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsServiceImpl userDetailsService;
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
  private CustomSavedRequestAwareAuthenticationSuccessHandler successHandler;

  @Autowired
  public void setUserDetailsService(
      UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Autowired
  public void setRestAuthenticationEntryPoint(
      RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
    this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
  }

  @Autowired
  public void setSuccessHandler(
      CustomSavedRequestAwareAuthenticationSuccessHandler successHandler) {
    this.successHandler = successHandler;
  }

  /**
   * Method provides configuration with request authorization and granting permissions to users.
   * @param http HttpSecurity
   * @throws Exception when configuration failed to be successfully executed.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
        .authorizeRequests()
            .antMatchers("/**").authenticated().and()
        .formLogin()
            .loginPage("/login").permitAll()
            .successHandler(successHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .failureHandler(new SimpleUrlAuthenticationFailureHandler()).and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()).and()
        .rememberMe().key("token").tokenValiditySeconds(3600);
        //@formatter:on
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws AuthenticationException {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider
        = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    return authProvider;
  }

  @Bean
  public CustomSavedRequestAwareAuthenticationSuccessHandler getSuccessHandler() {
    return new CustomSavedRequestAwareAuthenticationSuccessHandler();
  }
}
