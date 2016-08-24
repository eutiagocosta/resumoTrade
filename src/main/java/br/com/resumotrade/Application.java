package br.com.resumotrade;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "br.com.resumotrade")
@EnableJpaRepositories(basePackages = "br.com.resumotrade")
@EntityScan(basePackages = "br.com.resumotrade")
public class Application {
    
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.local")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Teste hora: " + LocalDate.parse("2016-08-15"));
    }

}
