package dev.kapkekes.serdej.core.wrappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;

@RegisterSerde
public class DoubleSerde implements ObjectSerde<Double> {
    @Override
    public JsonGenerator serialize(Double value, JsonGenerator generator) throws IOException {
        generator.writeNumber(value);
        return generator;
    }

    @Override
    public Double deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getDoubleValue();
        parser.nextToken();
        return value;
    }
}
