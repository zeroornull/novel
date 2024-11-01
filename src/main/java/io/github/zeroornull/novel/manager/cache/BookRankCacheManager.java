package io.github.zeroornull.novel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.zeroornull.novel.core.constant.DatabaseConsts;
import io.github.zeroornull.novel.dao.entity.BookInfo;
import io.github.zeroornull.novel.dao.mapper.BookInfoMapper;
import io.github.zeroornull.novel.dto.resp.BookRankRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 小说排行榜 缓存管理类
 *
 * @author pax
 * @since 2024-10-23 20:53:29
 */
@Component
@RequiredArgsConstructor
public class BookRankCacheManager {
    private final BookInfoMapper bookInfoMapper;

    /**
     * 查询小说点击榜列表，并放入缓存中
     */
    public List<BookRankRespDto > listVisitRankBooks() {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
        bookInfoQueryWrapper.orderByDesc(DatabaseConsts.BookTable.COLUMN_VISIT_COUNT);
        
        return listRankBooks(bookInfoQueryWrapper);
    }

    private List<BookRankRespDto> listRankBooks(QueryWrapper<BookInfo> bookInfoQueryWrapper) {
        bookInfoQueryWrapper
                .gt(DatabaseConsts.BookTable.COLUMN_WORD_COUNT,0)
                .last(DatabaseConsts.SqlEnum.LIMIT_30.getSql());
        return bookInfoMapper.selectList(bookInfoQueryWrapper).stream().map(v -> {
            BookRankRespDto respDto = new BookRankRespDto();
            respDto.setId(v.getId());
            respDto.setCategoryId(v.getCategoryId());
            respDto.setCategoryName(v.getCategoryName());
            respDto.setBookName(v.getBookName());
            respDto.setAuthorName(v.getAuthorName());
            respDto.setPicUrl(v.getPicUrl());
            respDto.setBookDesc(v.getBookDesc());
            respDto.setLastChapterName(v.getLastChapterName());
            respDto.setLastChapterUpdateTime(v.getLastChapterUpdateTime());
            respDto.setWordCount(v.getWordCount());
            return respDto;
        }).toList();
    }
}