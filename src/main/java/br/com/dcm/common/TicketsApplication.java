package br.com.dcm.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.dcm.entity"})
@EnableJpaRepositories(basePackages = {"br.com.dcm.dao"})
@ComponentScan(basePackages = {"br.com.dcm"})
@EnableRedisHttpSession
public class TicketsApplication{

    public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}
}
