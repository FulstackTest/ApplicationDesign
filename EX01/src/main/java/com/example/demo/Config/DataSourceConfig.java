package com.example.demo.Config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// TODO: DataSource 빈 2개를 작성하라.
//  필요한 import : javax.sql.DataSource, org.springframework.context.annotation.Bean,
//                 org.apache.commons.dbcp2.BasicDataSource, com.zaxxer.hikari.HikariDataSource
@Configuration
public class DataSourceConfig {

    // TODO 1) @Bean dataSource2() : BasicDataSource(commons-dbcp2)
    //        - setDriverClassName("com.mysql.cj.jdbc.Driver")
    //        - setUrl("jdbc:mysql://localhost:3306/testdb"), setUsername("root"), setPassword("1234")
    @Bean
    public DataSource dataSource2(){
        BasicDataSource dataSource = new BasicDataSource(); // commons-dbcp2 커넥션 풀 생성
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // My SQL 드라이버 설정
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb"); // DB접속 URL 설정
        dataSource.setUsername("root"); // DB 사용자명
        dataSource.setPassword("1234"); // DB 비밀번호
        return dataSource; // dataSource 반환

    }
    // TODO 2) @Bean dataSource3() : HikariDataSource
    //        - setDriverClassName("com.mysql.cj.jdbc.Driver")
    //        - setJdbcUrl("jdbc:mysql://localhost:3306/testdb"), setUsername("root"), setPassword("1234")
    //  ※ MemberDAO 는 dataSource3(HikariDataSource) 를 주입받아 사용한다.

}
