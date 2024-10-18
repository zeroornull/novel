package io.github.zeroornull.novel.core.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.zeroornull.novel.core.constant.EsConsts;
import io.github.zeroornull.novel.dao.entity.BookInfo;
import io.github.zeroornull.novel.dao.mapper.BookInfoMapper;
import io.github.zeroornull.novel.dto.es.EsBookDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Rabbit 队列监听器
 *
 * @author xiongxiaoyang
 * @date 2022/5/25
 */
@Component
@ConditionalOnProperty(prefix = "spring", name = {"elasticsearch.enabled",
        "amqp.enabled"}, havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class RabbitQueueListener {

    private final BookInfoMapper bookInfoMapper;
    private final ElasticsearchClient esClient;

    @SneakyThrows
    public void updateEsBook(Long bookId) {
        BookInfo bookInfo = bookInfoMapper.selectById(bookId);
        IndexResponse response = esClient.index(i -> i
                .index(EsConsts.BookIndex.INDEX_NAME)
                .id(bookInfo.getId().toString())
                .document(EsBookDto.build(bookInfo))
        );
        log.info("Indexed with version " + response.version());

    }

}
