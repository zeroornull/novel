package io.github.zeroornull.novel.service;

import io.github.zeroornull.novel.core.common.req.PageReqDto;
import io.github.zeroornull.novel.core.common.resp.PageRespDto;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import io.github.zeroornull.novel.dto.req.BookAddReqDto;
import io.github.zeroornull.novel.dto.req.ChapterAddReqDto;
import io.github.zeroornull.novel.dto.req.ChapterUpdateReqDto;
import io.github.zeroornull.novel.dto.req.UserCommentReqDto;
import io.github.zeroornull.novel.dto.resp.*;
import jakarta.validation.Valid;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 小说模块 服务类
 *
 * @author pax
 * @since 2024-10-12 20:50:41
 */
public interface BookService {


    /**
     * 发表评论
     *
     * @param dto 评论相关 DTO
     * @return void
     */
    RestResp<Void> saveComment(UserCommentReqDto dto);

    /**
     * 修改评论
     *
     * @param userId  用户ID
     * @param id      评论ID
     * @param content 修改后的评论内容
     * @return void
     */
    RestResp<Void> updateComment(Long userId, Long id, String content);

    /**
     * 删除评论
     *
     * @param userId    评论用户ID
     * @param commentId 评论ID
     * @return void
     */
    RestResp<Void> deleteComment(Long userId, Long commentId);

    /**
     * 小说最新评论查询
     *
     * @param bookId 小说ID
     * @return 小说最新评论数据
     */
    RestResp<BookCommentRespDto> listNewestComments(Long bookId);


    /**
     * 小说章节信息保存
     *
     * @param dto 章节信息
     * @return void
     */
    RestResp<Void> saveBookChapter(ChapterAddReqDto dto);

    RestResp<Void> saveBook(@Valid BookAddReqDto dto);

    RestResp<PageRespDto<BookInfoRespDto>> listAuthorBooks(PageReqDto dto);

    RestResp<Void> deleteBookChapter(Long chapterId);

    RestResp<ChapterContentRespDto> getBookChapter(Long chapterId);

    /**
     * 小说章节更新
     *
     * @param chapterId 章节ID
     * @param dto       更新内容
     * @return void
     */
    RestResp<Void> updateBookChapter(Long chapterId, ChapterUpdateReqDto dto);

    RestResp<PageRespDto<BookChapterRespDto>> listBookChapters(Long bookId, PageReqDto dto);

    RestResp<List<BookCategoryRespDto>> listCategory(Integer workDirection);

    RestResp<BookInfoRespDto> getBookById(Long bookId);

    RestResp<Void> addVisitCount(Long bookId);

    RestResp<BookChapterAboutRespDto> getLastChapterAbout(Long bookId);

    RestResp<List<BookInfoRespDto>> listRecBooks(Long bookId) throws NoSuchAlgorithmException;

    RestResp<List<BookChapterRespDto>> listChapters(Long bookId);

    RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId);

    RestResp<Long> getPreChapterId(Long chapterId);

    RestResp<Long> getNextChapterId(Long chapterId);

    RestResp<List<BookRankRespDto>> listVisitRankBooks();
}
