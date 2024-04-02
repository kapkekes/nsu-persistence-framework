package dev.kapkekes.serdej.core.primitives.arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.PrimitiveSerde;
import dev.kapkekes.serdej.core.primitives.DoubleSerde;

import java.io.IOException;
import java.util.ArrayList;

@RegisterSerde
public class DoubleArraySerde implements PrimitiveSerde {
    private final DoubleSerde serde = new DoubleSerde();

    public JsonGenerator serialize(double[] value, JsonGenerator generator) throws IOException {
        generator.writeStartArray();
        for (final var element : value) {
            serde.serialize(element, generator);
        }
        generator.writeEndArray();
        return generator;
    }

    public double[] deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        if (!parser.isExpectedStartArrayToken()) {
            throw new JsonParseException(parser, String.format("First token expected to be JsonToken.START_ARRAY for double[] (JsonToken.%s provided)", parser.currentToken()), parser.currentLocation());
        }

        final var arrayList = new ArrayList<Double>();

        parser.nextToken();
        while (parser.currentToken() != JsonToken.END_ARRAY) {
            arrayList.add(serde.deserialize(parser));
        }

        final var array = new double[arrayList.size()];
        for (var index = 0; index < array.length; index += 1) {
            array[index] = arrayList.get(index);
        }

        return array;
    }
}
