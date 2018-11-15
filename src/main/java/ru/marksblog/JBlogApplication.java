package ru.marksblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//Проверка работаспособности
@SpringBootApplication
@ComponentScan("ru.marksblog")
public class JBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBlogApplication.class, args);
	}
}

