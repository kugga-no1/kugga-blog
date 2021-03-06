package com.li.config;


import com.li.handler.auth.MyAccessDeniedHandler;
import com.li.handler.auth.MyAuthenticationEntryPoint;
import com.li.handler.auth.MyAuthenticationFailureHandler;
import com.li.handler.auth.MyAuthenticationSuccessHandler;
import com.li.config.springsecurityConfig.auth.MyUsernamePasswordAuthenticationFilter;
import com.li.config.springsecurityConfig.authority.MyAccessDecisionManager;
import com.li.config.springsecurityConfig.authority.MyFilterInvocationSecurityMetadataSource;
import com.li.config.springsecurityConfig.auth.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;  //????????????

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //????????????url???"/login"
        http.formLogin().loginProcessingUrl("/login");
        //?????????????????????cors ??????????????????,???Spring security ????????????preflight request???cors ???????????????
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        //???????????????iframe??????
        http.headers().frameOptions().disable();

        //http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);   ????????????

        //??????csrf
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myAccessDecisionManager);
                        object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                });                                                            //????????????

        http.authorizeRequests().anyRequest().permitAll();                //????????????????????????
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myAuthenticationEntryPoint);  //????????????
    }

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter=new MyUsernamePasswordAuthenticationFilter();
    myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
    myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return myUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //???????????????????????? ?????????????????????userdetailsservice
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
