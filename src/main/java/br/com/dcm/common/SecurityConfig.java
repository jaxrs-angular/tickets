package br.com.dcm.common;

import br.com.dcm.bo.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by dmartinez on 11/05/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;

    @Autowired
    private ApplicationContext appContext;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(
                "select username,password, enabled from user where username=?"
        ).authoritiesByUsernameQuery(
                "select username,authority, enabled from user where username=?"
        );

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().
                and().
                csrf().disable().
                authorizeRequests().antMatchers("/task/**").authenticated().
                anyRequest().permitAll().
                and().
                formLogin().loginPage("/index.html").loginProcessingUrl("/login").
                successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        String username = ((User) authentication.getPrincipal()).getUsername();
                        UserBo userbo = appContext.getBean(UserBo.class);
                        br.com.dcm.entity.User user = userbo.get(username);
                        SessionData data = new SessionData();
                        data.setCurrentUser(user);
                        httpServletRequest.getSession().setAttribute("sessionData",data);
                        httpServletResponse.sendRedirect("/task.html");
                    }
                })
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint("Fail"))
        ;
    }
}
