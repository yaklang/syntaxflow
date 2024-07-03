package net.demo.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"net.demo.spring"})
@MapperScan(basePackages={"**.dao","com.abc.**.mapper"})
@ServletComponentScan(basePackages = {"net.demo.spring"})
public class MSApplication {
	public static void main(String[] args) {
		SpringApplication.run(MSApplication.class, args);
	}
}
