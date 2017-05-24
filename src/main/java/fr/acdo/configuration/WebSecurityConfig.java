// package fr.acdo.configuration;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
// @Autowired
// private RESTAuthenticationEntryPoint authEntryPoint;
// @Autowired
// private RESTAuthenticationSuccessHandler authSuccessHandler;
// @Autowired
// private RESTAuthenticationFailureHandler authFailHandler;
// @Autowired
// private RESTLogoutSuccessHandler logoutSuccessHandler;
//
// @Override
// protected void configure(HttpSecurity http) throws Exception {
//
// http.authorizeRequests().antMatchers("/api/**").authenticated();
// http.csrf().disable();
// http.exceptionHandling().authenticationEntryPoint(authEntryPoint);
// http.formLogin().successHandler(authSuccessHandler);
// http.formLogin().failureHandler(authFailHandler);
// http.logout().logoutSuccessHandler(logoutSuccessHandler);
// }
// }