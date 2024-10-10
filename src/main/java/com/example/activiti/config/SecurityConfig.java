package com.example.activiti.config;

import com.example.activiti.security.filter.JsonLoginFilter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public SecurityService userDetailService;

    @Autowired
    public PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz.requestMatchers("/**").authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public JsonLoginFilter myJsonLoginFilter(HttpSecurity http) throws Exception {
        JsonLoginFilter myJsonLoginFilter = new JsonLoginFilter();
        //自定义登录url
        myJsonLoginFilter.setFilterProcessesUrl("/login/**");
//        myJsonLoginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
//        myJsonLoginFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        myJsonLoginFilter.setAuthenticationManager(authenticationManager(http));
        return myJsonLoginFilter;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //设置用户信息处理器
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //设置密码处理器
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);//原来默认的

        return authenticationManagerBuilder.build();

    }
}
