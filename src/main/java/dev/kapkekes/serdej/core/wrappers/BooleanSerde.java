package dev.kapkekes.serdej.core.wrappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;

@RegisterSerde
public class BooleanSerde implements ObjectSerde<Boolean> {
    @Override
    public JsonGenerator serialize(Boolean value, JsonGenerator generator) throws IOException {
        generator.writeBoolean(value);
        return generator;
    }

    @Override
    public Boolean deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getBooleanValue();
        parser.nextToken();
        return value;
    }
}
