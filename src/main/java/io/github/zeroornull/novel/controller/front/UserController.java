package io.github.zeroornull.novel.controller.front;

import io.github.zeroornull.novel.core.auth.UserHolder;
import io.github.zeroornull.novel.core.common.req.PageReqDto;
import io.github.zeroornull.novel.core.common.resp.PageRespDto;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.core.constant.ApiRouterConsts;
import io.github.zeroornull.novel.core.constant.SystemConfigConsts;
import io.github.zeroornull.novel.dto.req.UserCommentReqDto;
import io.github.zeroornull.novel.dto.req.UserInfoUptReqDto;
import io.github.zeroornull.novel.dto.req.UserLoginReqDto;
import io.github.zeroornull.novel.dto.req.UserRegisterReqDto;
import io.github.zeroornull.novel.dto.resp.UserCommentRespDto;
import io.github.zeroornull.novel.dto.resp.UserInfoRespDto;
import io.github.zeroornull.novel.dto.resp.UserLoginRespDto;
import io.github.zeroornull.novel.dto.resp.UserRegisterRespDto;
import io.github.zeroornull.novel.service.BookService;
import io.github.zeroornull.novel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 前台门户-会员模块 API 控制器
 *
 * @author pax
 * @since 2024-10-12 19:54:38
 */
@Tag(name = "UserController", description = "前台门户-会员模块")
@SecurityRequirement(name = SystemConfigConsts.HTTP_AUTH_HEADER_NAME)
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_USER_URL_PREFIX)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BookService bookService;

    @Operation(summary = "用户注册接口")
    @PostMapping("register")
    public RestResp<UserRegisterRespDto> register(@Valid @RequestBody UserRegisterReqDto dto) {
        return userService.register(dto);
    }

    /**
     * 用户登录接口
     */
    @Operation(summary = "用户登录接口")
    @PostMapping("login")
    public RestResp<UserLoginRespDto> login(@Valid @RequestBody UserLoginReqDto dto) {
        return userService.login(dto);
    }

    /**
     * 用户信息查询接口
     */
    @Operation(summary = "用户信息查询接口")
    @GetMapping
    public RestResp<UserInfoRespDto> getUserInfo() {
        return userService.getUserInfo(UserHolder.getUserId());
    }

    /**
     * 用户信息修改接口
     */
    @Operation(summary = "用户信息修改接口")
    @PutMapping
    public RestResp<Void> updateUserInfo(@Valid @RequestBody UserInfoUptReqDto dto) {
        dto.setUserId(UserHolder.getUserId());
        return userService.updateUserInfo(dto);
    }
    
    /**
     * 用户反馈提交接口
     */
    @Operation(summary = "用户反馈提交接口")
    @PostMapping("feedback")
    public RestResp<Void> submitFeedback(@RequestBody String content) {
        return userService.saveFeedback(UserHolder.getUserId(), content);
    }
    
    /**
     * 用户反馈删除接口
     */
    @Operation(summary = "用户反馈删除接口")
    @DeleteMapping("feedback/{id}")
    public RestResp<Void> deleteFeedback(@Parameter(description = "反馈ID") @PathVariable Long id) {
        return userService.deleteFeedback(UserHolder.getUserId(), id);
    }

    /**
     * 发表评论接口
     */
    @Operation(summary = "发表评论接口")
    @PostMapping("comment")
    public RestResp<Void> comment(@Valid @RequestBody UserCommentReqDto dto) {
        dto.setUserId(UserHolder.getUserId());
        return bookService.saveComment(dto);
    }

    /**
     * 修改评论接口
     */
    @Operation(summary = "修改评论接口")
    @PutMapping("comment/{id}")
    public RestResp<Void> updateComment(@Parameter(description = "评论ID") @PathVariable Long id, String comment) {
        return bookService.updateComment(UserHolder.getUserId(), id, comment);
    }

    /**
     * 删除评论接口
     */
    @Operation(summary = "删除评论接口")
    @DeleteMapping("comment/{id}")
    public RestResp<Void> deleteComment(@Parameter(description = "评论ID") @PathVariable Long id) {
        return bookService.deleteComment(UserHolder.getUserId(), id);
    }

    /**
     * 查询书架状态接口 0-不在书架 1-已在书架
     */
    @Operation(summary = "查询书架状态接口")
    @GetMapping("bookshelf_status")
    public RestResp<Integer> getBookshelfStatus(@Parameter(description = "小说ID") String bookId) {
        return userService.getBookshelfStatus(UserHolder.getUserId(), bookId);
    }

    /**
     * 分页查询评论
     */
    @Operation(summary = "查询会员评论列表接口")
    @GetMapping("comments")
    public RestResp<PageRespDto<UserCommentRespDto>> listComments(PageReqDto pageReqDto) {
        return bookService.listComments(UserHolder.getUserId(), pageReqDto);
    }
    
}
