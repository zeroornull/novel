package io.github.zeroornull.novel.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户信息 DTO
 *
 * @author pax
 * @since 2024-10-13 01:46:20
 */
@Data
@Builder
public class UserInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    private Integer status;
}
