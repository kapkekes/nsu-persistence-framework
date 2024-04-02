package dev.kapkekes.serdej.core.primitives;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.PrimitiveSerde;

import java.io.IOException;

@RegisterSerde
public class LongSerde implements PrimitiveSerde {
    public JsonGenerator serialize(long value, JsonGenerator generator) throws IOException {
        generator.writeNumber(value);
        return generator;
    }

    public long deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getLongValue();
        parser.nextToken();
        return value;
    }
}
