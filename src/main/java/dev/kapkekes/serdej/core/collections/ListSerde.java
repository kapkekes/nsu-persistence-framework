package dev.kapkekes.serdej.core.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RegisterSerde
public class ListSerde<T> implements ObjectSerde<List<T>> {
    private final ObjectSerde<T> elementSerde;

    public ListSerde(ObjectSerde<T> elementSerde) {
        this.elementSerde = elementSerde;
    }

    @Override
    public JsonGenerator serialize(List<T> value, JsonGenerator generator) throws IOException {
        generator.writeStartArray();
        for (final var element : value) {
            elementSerde.serialize(element, generator);
        }
        generator.writeEndArray();
        return generator;
    }

    @Override
    public List<T> deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        if (!parser.isExpectedStartArrayToken()) {
            throw new JsonParseException(parser, String.format("First token expected to be JsonToken.START_ARRAY for List<T> (JsonToken.%s provided)", parser.currentToken()), parser.currentLocation());
        }

        final var arrayList = new ArrayList<T>();

        parser.nextToken();
        while (parser.currentToken() != JsonToken.END_ARRAY) {
            arrayList.add(elementSerde.deserialize(parser));
        }

        return arrayList;
    }
}
