package io.github.zeroornull.novel.core.common.exception;

import io.github.zeroornull.novel.core.common.constant.ErrorCodeEnum;
import io.github.zeroornull.novel.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 通用的异常处理器
 *
 * @author pax
 * @since 2024-10-10 22:52:10
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    /**
     * 处理数据校验异常
     */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAM_ERROR);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResp.error();
    }

}
