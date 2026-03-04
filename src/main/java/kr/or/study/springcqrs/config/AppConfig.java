package kr.or.study.springcqrs.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "kr.or.study.springcqrs", annotationClass = Mapper.class)
public class AppConfig {
}
