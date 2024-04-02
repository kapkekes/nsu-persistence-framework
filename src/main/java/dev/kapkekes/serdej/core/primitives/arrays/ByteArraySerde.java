package dev.kapkekes.serdej.core.primitives.arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.PrimitiveSerde;
import dev.kapkekes.serdej.core.primitives.ByteSerde;

import java.io.IOException;
import java.util.ArrayList;

@RegisterSerde
public class ByteArraySerde implements PrimitiveSerde {
    private final ByteSerde serde = new ByteSerde();

    public JsonGenerator serialize(byte[] value, JsonGenerator generator) throws IOException {
        generator.writeStartArray();
        for (final var element : value) {
            serde.serialize(element, generator);
        }
        generator.writeEndArray();
        return generator;
    }

    public byte[] deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        if (!parser.isExpectedStartArrayToken()) {
            throw new JsonParseException(parser, String.format("First token expected to be JsonToken.START_ARRAY for byte[] (JsonToken.%s provided)", parser.currentToken()), parser.currentLocation());
        }

        final var arrayList = new ArrayList<Byte>();

        parser.nextToken();
        while (parser.currentToken() != JsonToken.END_ARRAY) {
            arrayList.add(serde.deserialize(parser));
        }

        final var array = new byte[arrayList.size()];
        for (var index = 0; index < array.length; index += 1) {
            array[index] = arrayList.get(index);
        }

        return array;
    }
}
