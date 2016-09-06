package br.com.resumotrade.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableTransactionManagement
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
	
	@Bean
	ObjectMapper jacksonObjectMapper() {
		return new CustomJacksonObjectMapper();
	}

	@Bean
	SerializationConfig serializationConfig() {
		return jacksonObjectMapper().getSerializationConfig();
	}
	
    public static void main(String[] args) throws ParseException {
    	SpringApplication.run(Application.class, args);
    }

}
