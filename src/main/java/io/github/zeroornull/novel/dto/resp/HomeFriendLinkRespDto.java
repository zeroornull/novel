package io.github.zeroornull.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
/**
 * 首页友情链接 响应DTO
 *
 * @author pax
 * @since 2024-11-01 15:30:58
 */
@Data
public class HomeFriendLinkRespDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 链接名
     */
    @Schema(description = "链接名")
    private String linkName;

    /**
     * 链接url
     */
    @Schema(description = "链接url")
    private String linkUrl;
}
