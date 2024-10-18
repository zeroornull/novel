package io.github.zeroornull.novel.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.zeroornull.novel.dao.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.zeroornull.novel.dto.req.BookSearchReqDto;
import io.github.zeroornull.novel.dto.resp.BookInfoRespDto;

import java.util.List;

/**
 * <p>
 * 小说信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @date 2024/10/12
 */
public interface BookInfoMapper extends BaseMapper<BookInfo> {


    /**
     * 小说搜索
     *
     * @param page      mybatis-plus 分页对象
     * @param condition 搜索条件
     * @return 返回结果
     */
    List<BookInfo> searchBooks(IPage<BookInfoRespDto> page, BookSearchReqDto condition);
}
