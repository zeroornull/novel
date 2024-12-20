package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.req.UserInfoUptReqDto;
import io.github.zeroornull.novel.dto.req.UserLoginReqDto;
import io.github.zeroornull.novel.dto.req.UserRegisterReqDto;
import io.github.zeroornull.novel.dto.resp.UserInfoRespDto;
import io.github.zeroornull.novel.dto.resp.UserLoginRespDto;
import io.github.zeroornull.novel.dto.resp.UserRegisterRespDto;
import jakarta.validation.Valid;

/**
 * 会员模块 服务类
 *
 * @author pax
 * @since 2024-10-12 18:00:16
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param dto 注册参数
     * @return JWT
     */
    RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto);


    /**
     * 用户登录
     *
     * @param dto 登录参数
     * @return JWT + 昵称
     */
    RestResp<UserLoginRespDto> login(UserLoginReqDto dto);

    RestResp<UserInfoRespDto> getUserInfo(Long userId);

    RestResp<Void> updateUserInfo(@Valid UserInfoUptReqDto dto);

    RestResp<Void> deleteFeedback(Long userId, Long id);

    RestResp<Void> saveFeedback(Long userId, String content);

    RestResp<Integer> getBookshelfStatus(Long userId, String bookId);
}
