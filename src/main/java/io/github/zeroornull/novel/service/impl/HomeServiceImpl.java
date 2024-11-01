package io.github.zeroornull.novel.service.impl;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.resp.HomeBookRespDto;
import io.github.zeroornull.novel.dto.resp.HomeFriendLinkRespDto;
import io.github.zeroornull.novel.manager.cache.HomeBookCacheManager;
import io.github.zeroornull.novel.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小说推荐 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024/10/12
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    public final HomeBookCacheManager homeBookCacheManager;
    private final io.github.zeroornull.novel.manager.cache.friendLinkCacheManager friendLinkCacheManager;

    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }

    @Override
    public RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks() {
        return RestResp.ok(friendLinkCacheManager.listFriendLinks());
    }
}
