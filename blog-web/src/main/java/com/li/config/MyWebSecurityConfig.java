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
    private MyAccessDeniedHandler myAccessDeniedHandler;  //异常处理

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置登录url为"/login"
        http.formLogin().loginProcessingUrl("/login");
        //解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        //放行掉这个iframe加载
        http.headers().frameOptions().disable();

        //http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);   配置登出

        //禁用csrf
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
                });                                                            //权限处理

        http.authorizeRequests().anyRequest().permitAll();                //普通请求要求登录
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myAuthenticationEntryPoint);  //异常处理
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

    //用户密码加密验证 配置自己写好的userdetailsservice
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
