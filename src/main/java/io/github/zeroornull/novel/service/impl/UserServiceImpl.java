package io.github.zeroornull.novel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.zeroornull.novel.core.common.constant.ErrorCodeEnum;
import io.github.zeroornull.novel.core.common.exception.BusinessException;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.core.constant.DatabaseConsts;
import io.github.zeroornull.novel.core.constant.SystemConfigConsts;
import io.github.zeroornull.novel.core.util.JwtUtils;
import io.github.zeroornull.novel.dao.entity.UserInfo;
import io.github.zeroornull.novel.dao.mapper.UserInfoMapper;
import io.github.zeroornull.novel.dto.req.UserLoginReqDto;
import io.github.zeroornull.novel.dto.req.UserRegisterReqDto;
import io.github.zeroornull.novel.dto.resp.UserLoginRespDto;
import io.github.zeroornull.novel.dto.resp.UserRegisterRespDto;
import io.github.zeroornull.novel.manager.redis.VerifyCodeManager;
import io.github.zeroornull.novel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.cli.Digest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 会员模块 服务实现类
 *
 * @author xiongxiaoyang
 * @date 2022/5/17
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final VerifyCodeManager verifyCodeManager;
    private final UserInfoMapper userInfoMapper;
    private final JwtUtils jwtUtils;

    @Override
    public RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto) {
        // 校验图形验证码是否正确
        if (!verifyCodeManager.imgVerifyCodeOk(dto.getSessionId(), dto.getVelCode())) {
            // 图形验证码校验失败
            throw new BusinessException(ErrorCodeEnum.USER_VERIFY_CODE_ERROR);
        }
        // 校验手机号是否已注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        if (userInfoMapper.selectCount(queryWrapper) > 0) {
            // 手机号已经注册
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }

        // 注册成功，保存用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        userInfo.setUsername(dto.getUsername());
        userInfo.setNickName(dto.getUsername());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setSalt("0");
        userInfoMapper.insert(userInfo);

        // 删除验证码
        verifyCodeManager.removeImgVerifyCode(dto.getSessionId());

        // 生成JWT 并返回
        return RestResp.ok(
                UserRegisterRespDto.builder().token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY)).uid(userInfo.getId()).build()
        );
    }

    @Override
    public RestResp<UserLoginRespDto> login(UserLoginReqDto dto) {
        // 查询用户信息
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(userInfo)) {
            // 用户不存在
            throw new BusinessException(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }

        // 校验密码
        if (!Objects.equals(userInfo.getPassword(), DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)))) {
            // 密码错误
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }

        // 登录成功，生成JWT 并返回
        return RestResp.ok(
                UserLoginRespDto.builder().token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY)).uid(userInfo.getId()).nickName(userInfo.getNickName()).build()
        );
    }
}
