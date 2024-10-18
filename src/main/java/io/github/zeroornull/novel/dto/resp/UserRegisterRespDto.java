package io.github.zeroornull.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 用户注册 响应DTO
 * @author pax
 * @since 2024-10-12 18:02:31
 */
@Data
@Builder
public class UserRegisterRespDto {
    @Schema(description = "用户ID")
    private Long uid;
    
    @Schema(description = "用户token")
    private String token;
}
