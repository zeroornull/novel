package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.core.common.util.ImgVerifyCodeUtils;
import io.github.zeroornull.novel.dto.resp.ImgVerifyCodeRespDto;

import java.io.IOException;

/**
 * 资源（图片/视频/文档）相关服务类
 *
 * @author pax
 * @since 2024-10-12 15:36:39
 */
public interface ResourceService {
    /**
     * 获取图片验证码
     *
     * @throws IOException 验证码图片生成失败
     * @return Base64编码的图片
     */
    RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException;
}
