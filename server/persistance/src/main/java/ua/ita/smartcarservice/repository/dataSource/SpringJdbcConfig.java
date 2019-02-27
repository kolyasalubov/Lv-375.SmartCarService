package ua.ita.smartcarservice.repository.dataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;




public class SpringJdbcConfig {

//    @Value("${spring.datasource.username}")
//    private String userName;
//    @Value("{spring.datasource.password}")
//    private String password;
//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/carservice");
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//
//        return dataSource;
//    }
}