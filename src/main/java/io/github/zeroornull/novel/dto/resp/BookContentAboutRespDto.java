package io.github.zeroornull.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 小说内容相关 响应DTO
 *
 * @author pax
 * @since 2024-10-23 20:43:12
 */
@Data
@Builder
public class BookContentAboutRespDto {
    /**
     * 小说信息
     */
    @Schema(description = "小说信息")
    private BookInfoRespDto bookInfo;

    /**
     * 章节信息
     */
    @Schema(description = "章节信息")
    private BookChapterRespDto chapterInfo;

    /**
     * 章节内容
     */
    @Schema(description = "章节内容")
    private String bookContent;
}
