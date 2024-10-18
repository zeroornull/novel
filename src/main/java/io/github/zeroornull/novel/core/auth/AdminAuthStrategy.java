package io.github.zeroornull.novel.core.auth;

import io.github.zeroornull.novel.core.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 平台后台管理系统 认证授权策略
 *
 * @author pax
 * @since 2024-10-13 13:19:54
 */
@Component
@RequiredArgsConstructor
public class AdminAuthStrategy implements AuthStrategy{

    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // TODO 平台后台 token 校验
    }
}
