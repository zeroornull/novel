package io.github.zeroornull.novel.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.resp.ImgVerifyCodeRespDto;
import io.github.zeroornull.novel.manager.redis.VerifyCodeManager;
import io.github.zeroornull.novel.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 资源（图片/视频/文档）相关服务实现类
 *
 * @author pax
 * @since 2024-10-12 15:39:48
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final VerifyCodeManager verifyCodeManager;

    @Override
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException {
        String sessionId = IdWorker.get32UUID();
        return RestResp.ok(ImgVerifyCodeRespDto.builder().sessionId(sessionId).img(verifyCodeManager.genImgVerifyCode(sessionId)).build());
    }
}
