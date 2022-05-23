package com.GNKBC.GNKBC.config;

import com.GNKBC.GNKBC.config.auth.CustomOAuth2User;
import com.GNKBC.GNKBC.config.auth.CustomOAuth2UserService;
import com.GNKBC.GNKBC.domain.Role;
import com.GNKBC.GNKBC.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final AdminService adminService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/","/css/**","/images/**","/js/**"
                            ,"/h2-console/**","/static/**","/oauth/**","/admin/login").permitAll()
//                    .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
//                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/admin/login")
                    .userInfoEndpoint()
//                    .userService(customOAuth2UserService)
                .and()
                .successHandler((request, response, authentication) -> {

                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                    if(adminService.processOAuthPostAdminLogin(oauthUser.getEmail())){
                        HttpSession session = request.getSession(); //세션에 로그인 회원 정보 보관
                        session.setAttribute("admin-email",oauthUser.getEmail());
                        response.sendRedirect("/admin/home");
                    }else
                        response.sendRedirect("/admin/loginfail");
                });
    }
}
