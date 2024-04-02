package dev.kapkekes.serdej.core.wrappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;

@RegisterSerde
public class ByteSerde implements ObjectSerde<Byte> {
    @Override
    public JsonGenerator serialize(Byte value, JsonGenerator generator) throws IOException {
        generator.writeNumber(value);
        return generator;
    }

    @Override
    public Byte deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getByteValue();
        parser.nextToken();
        return value;
    }
}
