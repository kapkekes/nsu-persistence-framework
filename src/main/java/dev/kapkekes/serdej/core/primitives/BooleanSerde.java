package dev.kapkekes.serdej.core.primitives;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.PrimitiveSerde;

import java.io.IOException;

@RegisterSerde
public class BooleanSerde implements PrimitiveSerde {
    public JsonGenerator serialize(boolean value, JsonGenerator generator) throws IOException {
        generator.writeBoolean(value);
        return generator;
    }

    public boolean deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getBooleanValue();
        parser.nextToken();
        return value;
    }
}
