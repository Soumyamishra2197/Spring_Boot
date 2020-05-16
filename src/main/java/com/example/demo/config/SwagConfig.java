package com.example.demo.config;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagConfig {

	@Bean
	public Docket swaggerConfig() {
		
		return new Docket(DocumentationType.SWAGGER_2)
		        .select()
		        .paths(PathSelectors.any())
		        .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
		        .build().apiInfo(getApinInfo())
		        .directModelSubstitute(LocalDate.class, Date.class)
		        .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", In.HEADER.name())))
		        .securityContexts(Collections.singletonList(SecurityContext.builder()
		        .securityReferences(Collections.singletonList(SecurityReference.builder().reference("JWT")
		                                .scopes(new AuthorizationScope[0])
		                                .build()))
		        .build()))
		        .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();}
	
	private ApiInfo getApinInfo() {
        return  new ApiInfoBuilder()
                .title("Assignment project")
                .description("Documentation Demo")
                .contact(new Contact("Soumya Mishra","url@xyz","Mishrasoumya2197@gmail.com"))
                .license("License xxxxxx")
                .licenseUrl("License Url xxxxxxx")
                .version("Version v x.xx")
                .build();
    }

	
}
