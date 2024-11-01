package io.github.zeroornull.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 小说内容 响应DTO
 *
 * @author pax
 * @since 2024-10-23 17:18:51
 */
@Data
@Builder
public class ChapterContentRespDto {
    /**
     * 章节标题
     */
    @Schema(description = "章节名")
    private String chapterName;

    /**
     * 章节内容
     */
    @Schema(description = "章节内容")
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    @Schema(description = "是否收费;1-收费 0-免费")
    private Integer isVip;
}
