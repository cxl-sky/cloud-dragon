//package com.dragon.authserver.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * @author cxl
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
////    @Autowired
////    private AuthApiProperties authApiProperties;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private TokenStore tokenStore;
//
////    @Autowired
////    private ResourceAccessDeniedHandler resourceAccessDeniedHandler;
//
//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler() {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId("test-resource-id").stateless(true);
//        resources.tokenStore(tokenStore);
////        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
////        resources.accessDeniedHandler(resourceAccessDeniedHandler);
//        resources.expressionHandler(oAuth2WebSecurityExpressionHandler());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").access("@tokenKeyAccessService.hasPermission(request,authentication)");
//
//        http.headers().frameOptions().disable();
//    }
//}
