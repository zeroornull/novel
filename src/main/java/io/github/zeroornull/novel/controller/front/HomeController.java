package io.github.zeroornull.novel.controller.front;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.core.constant.ApiRouterConsts;
import io.github.zeroornull.novel.dto.resp.HomeBookRespDto;
import io.github.zeroornull.novel.service.HomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台门户-首页模块 API 控制器
 *
 * @author pax
 * @since 2024-10-12 14:55:09
 */
@Tag(name = "HomeController", description = "前台门户-首页模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return homeService.listHomeBooks();
    }
}
