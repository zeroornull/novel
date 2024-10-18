package io.github.zeroornull.novel.core.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 用户名序列化器（敏感信息，不应该在页面上完全显示）
 *
 * @author pax
 * @since 2024-10-12 22:50:07
 */
public class UsernameSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(s.substring(0, 4) + "****" + s.substring(8));
    }
}