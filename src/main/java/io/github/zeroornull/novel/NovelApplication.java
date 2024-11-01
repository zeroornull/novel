package io.github.zeroornull.novel;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@MapperScan("io.github.zeroornull.novel.dao.mapper")
@SpringBootApplication
@EnableCaching
@Slf4j
public class NovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelApplication.class, args);
    }

    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            Map<String, CacheManager> beans = context.getBeansOfType(CacheManager.class);
            log.info("加载了如下缓存管理器：");
            beans.forEach((k, v) -> {
                log.info("{}:{}", k, v.getClass().getName());
                log.info("缓存：{}", v.getCacheNames());
            });
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.csrf().disable()
//                .securityMatcher(EndpointRequest.toAnyEndpoint())
//                .authorizeHttpRequests(requests -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
//        http.httpBasic();

        // 新版本方法变更，暂不确定这样使用是否合适
        http.csrf(CsrfConfigurer::disable)
                .securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeHttpRequests(requests -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
