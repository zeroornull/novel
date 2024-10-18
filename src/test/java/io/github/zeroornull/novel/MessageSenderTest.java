package io.github.zeroornull.novel;

import io.github.zeroornull.novel.core.constant.MessageSenderTypeConsts;
import io.github.zeroornull.novel.manager.message.AbstractMessageSender;
import io.github.zeroornull.novel.manager.message.MessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NovelApplication.class)
public class MessageSenderTest {
    @Autowired
    private Map<String, AbstractMessageSender> messageSenders;

    @Test
    public void testMessageSenders() {
        MessageSender registerMailSender = messageSenders.get(
                MessageSenderTypeConsts.REGISTER_MAIL_SENDER);

        if (Objects.nonNull(registerMailSender)) {
            registerMailSender.sendMessage(11111L, "xxyopen@foxmail.com", "xxyopen");
        }

        MessageSender seckillSysNoticeSender = messageSenders.get(
                MessageSenderTypeConsts.SECKILL_SYS_NOTICE_SENDER);

        if (Objects.nonNull(seckillSysNoticeSender)) {
            seckillSysNoticeSender.sendMessage(11111L, "全场商品", "今晚 9 点", "www.xxyopen.com");
        }
    }
}
