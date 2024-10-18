package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.PageRespDto;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.req.BookSearchReqDto;
import io.github.zeroornull.novel.dto.resp.BookInfoRespDto;

/**
 * 搜索 服务类
 *
 * @author pax
 * @since 2024-10-13 22:21:29
 */
public interface SearchService {
    /**
     * 小说搜索
     *
     * @param condition 搜索条件
     * @return 搜索结果
     */
    RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition);
}
