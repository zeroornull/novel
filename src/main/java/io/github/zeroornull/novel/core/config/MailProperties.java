package io.github.zeroornull.novel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * mail 配置属性
 *
 * @author pax
 * @since 2024-10-13 18:33:39
 */
@ConfigurationProperties(prefix = "spring.mail")
public record MailProperties(String nickname, String username) {
}
