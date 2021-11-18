package com.ssafy.cafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import com.ssafy.cafe.model.dao.OrderDao;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackageClasses = OrderDao.class)
public class MobileThrowProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileThrowProjectApplication.class, args);
	}
	
	   @Bean
	    public Docket postsApi() {
	       final ApiInfo apiInfo = new ApiInfoBuilder()
	               .title("SSAFY Cafe Rest API")
	               .description("<h3>SSAFY Cafe에서 제공되는 Rest api의 문서 제공</h3>")
	               .contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
	               .license("MIT License")
	               .version("1.0")
	               .build();
	       
	        Docket docket = new Docket(DocumentationType.SWAGGER_2)
	                .groupName("ssafyVueBookWS")
	                .apiInfo(apiInfo)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.ssafy.cafe.controller.rest"))
//	              .paths(PathSelectors.ant("/book/**"))
	                .build();
	        return docket;
	    }


}
