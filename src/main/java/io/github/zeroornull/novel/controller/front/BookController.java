package io.github.zeroornull.novel.controller.front;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.core.constant.ApiRouterConsts;
import io.github.zeroornull.novel.dto.resp.BookCommentRespDto;
import io.github.zeroornull.novel.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台门户-小说模块 API 控制器
 *
 * @author pax
 * @since  2024-10-13 00:37:44
 */
@Tag(name = "BookController", description = "前台门户-小说模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 小说最新评论查询接口
     */
    @Operation(summary = "小说最新评论查询接口")
    @GetMapping("comment/newest_list")
    public RestResp<BookCommentRespDto> listNewestComments(
            @Parameter(description = "小说ID") Long bookId) {
        return bookService.listNewestComments(bookId);
    }
    
}
