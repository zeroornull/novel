package io.github.zeroornull.novel.manager.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.zeroornull.novel.core.constant.CacheConsts;
import io.github.zeroornull.novel.core.constant.DatabaseConsts;
import io.github.zeroornull.novel.dao.entity.BookContent;
import io.github.zeroornull.novel.dao.mapper.BookContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 小说内容 缓存管理类
 *
 * @author pax
 * @since 2024-10-23 16:12:25
 */
@Component
@RequiredArgsConstructor
public class BookContentCacheManager {
    private final BookContentMapper bookContentMapper;

    /**
     * 查询小说内容，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> bookContentQueryWrapper = new QueryWrapper<>();
        bookContentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID,chapterId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(bookContentQueryWrapper);
        return bookContent.getContent();
    }

    @CacheEvict(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
            value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public void evictBookContentCache(Long chapterId) {
        // 调用此方法自动清除小说内容信息的缓存
    }
    
}
