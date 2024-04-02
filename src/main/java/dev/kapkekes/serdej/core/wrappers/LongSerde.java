package dev.kapkekes.serdej.core.wrappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;

@RegisterSerde
public class LongSerde implements ObjectSerde<Long> {
    @Override
    public JsonGenerator serialize(Long value, JsonGenerator generator) throws IOException {
        generator.writeNumber(value);
        return generator;
    }

    @Override
    public Long deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getLongValue();
        parser.nextToken();
        return value;
    }
}
