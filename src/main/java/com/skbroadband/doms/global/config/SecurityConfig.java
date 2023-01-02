package com.skbroadband.doms.global.config;

import com.skbroadband.doms.global.component.security.CustomPermissionEvaluator;
import com.skbroadband.doms.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : SecurityConfig
 * @Program :
 * @Date : 2022-12-07
 * @Comment :
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
@RequiredArgsConstructor
public class SecurityConfig  extends GlobalMethodSecurityConfiguration {
    //private final CustomUserDetailService customUserDetailService;
    private final UserService userService;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {

        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(customPermissionEvaluator());

        return expressionHandler;
    }


    @Bean
    public CustomPermissionEvaluator customPermissionEvaluator() {
        return new CustomPermissionEvaluator();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/resources/static/**");
//    }

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        return http.requestMatchers(matchers -> matchers
                        .antMatchers("/resources/**", "/js/**", "/favicon.ico"))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .requestCache(RequestCacheConfigurer::disable)
                .securityContext(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authenticationManager(authenticationManager(httpSecurity))
                .csrf().disable() // h2 database login
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/sample/**").permitAll()
                .antMatchers("/h2-console/**").permitAll() // h2 database login
                .antMatchers("/", "/home", "/signUp").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
                .anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
                .and() // h2 database login
                .headers() // h2 database login
                .frameOptions().disable() // h2 database login
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login") // 기본 로그인 페이지
                .and()
                .logout()
                .permitAll()
                // .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
                // .logoutSuccessUrl("/login?logout") // 로그아웃 성공 URL (기본 값 : "/login?logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                .clearAuthentication(true); // 로그아웃 시 권한 제거
        return httpSecurity.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            //FIXME: user 조회
//        if (user != null) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("USER")); // USER 라는 역할을 넣어준다.
//            return new User(user.getId(), user.getPassword(), grantedAuthorities);
//        } else {
//            throw new UsernameNotFoundException("can not find User : " + id);
//        }
            return null;
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());

        return authenticationManagerBuilder.build();
    }
}
