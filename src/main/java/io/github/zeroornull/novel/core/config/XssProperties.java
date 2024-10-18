package io.github.zeroornull.novel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Xss 过滤配置属性
 *
 * @author pax
 * @since 2024-10-13 17:07:18
 */
@ConfigurationProperties(prefix = "novel.xss")
public record XssProperties (Boolean enabled, List<String> excludes){
    
}
