package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.req.AuthorRegisterReqDto;

/**
 * 作家模块 业务服务类
 *
 * @author pax
 * @since 2024-10-21 02:29:39
 */
public interface AuthorService {

    /**
     * 作家注册
     *
     * @param dto 注册参数
     * @return void
     */
    RestResp<Void> register(AuthorRegisterReqDto dto);

    RestResp<Integer> getStatus(Long userId);
}
