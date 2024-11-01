package io.github.zeroornull.novel.service.impl;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dao.entity.AuthorInfo;
import io.github.zeroornull.novel.dao.mapper.AuthorInfoMapper;
import io.github.zeroornull.novel.dto.AuthorInfoDto;
import io.github.zeroornull.novel.dto.req.AuthorRegisterReqDto;
import io.github.zeroornull.novel.manager.cache.AuthorInfoCacheManager;
import io.github.zeroornull.novel.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 作家模块 服务实现类
 *
 * @author pax
 * @since 2024-10-21 02:30:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorInfoCacheManager authorInfoCacheManager;
    private final AuthorInfoMapper authorInfoMapper;

    @Override
    public RestResp<Void> register(AuthorRegisterReqDto dto) {
        // 校验该用户是否已注册为作家
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(dto.getUserId());
        if (Objects.nonNull(author)) {
            // 该用户已经是作家，直接返回
            return RestResp.ok();
        }
        // 保存作家注册信息
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setUserId(dto.getUserId());
        authorInfo.setChatAccount(dto.getChatAccount());
        authorInfo.setEmail(dto.getEmail());
        authorInfo.setInviteCode("0");
        authorInfo.setTelPhone(dto.getTelPhone());
        authorInfo.setPenName(dto.getPenName());
        authorInfo.setWorkDirection(dto.getWorkDirection());
        authorInfo.setCreateTime(LocalDateTime.now());
        authorInfo.setUpdateTime(LocalDateTime.now());
        authorInfoMapper.insert(authorInfo);
        // 清除作家缓存
        authorInfoCacheManager.evictAuthorCache();
        return RestResp.ok();
    }

    @Override
    public RestResp<Integer> getStatus(Long userId) {
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(userId);
        
        return Objects.isNull(author)?RestResp.ok(null):RestResp.ok(author.getStatus());
    }
}
