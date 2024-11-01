package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.resp.NewsInfoRespDto;

import java.util.List;

/**
 * 新闻模块 服务类
 *
 * @author pax
 * @since 2024-11-01 15:39:09
 */
public interface NewsService {
    /**
     * 最新新闻列表查询
     *
     * @return 新闻列表
     */
    RestResp<List<NewsInfoRespDto>> listLatestNews();

    RestResp<NewsInfoRespDto> getNews(Long id);
}
