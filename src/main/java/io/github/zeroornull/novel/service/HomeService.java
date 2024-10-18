package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dao.entity.HomeBook;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.zeroornull.novel.dto.resp.HomeBookRespDto;

import java.util.List;

/**
 * <p>
 * 小说推荐 服务类
 * </p>
 *
 * @author ${author}
 * @date 2024/10/12
 */
public interface HomeService {
    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     * */
    RestResp<List<HomeBookRespDto>> listHomeBooks();
}
