package com.huangstomach.springalchemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.huangstomach.springalchemy.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
@EnableAsync
public class SpringAlchemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAlchemyApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		// 全局CORS配置
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
