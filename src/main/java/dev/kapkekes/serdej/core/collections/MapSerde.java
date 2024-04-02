package dev.kapkekes.serdej.core.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;
import java.util.Map;

public class MapSerde<V> implements ObjectSerde<Map<String, V>> {
    @Override
    public JsonGenerator serialize(Map<String, V> value, JsonGenerator generator) throws IOException {
        return null;
    }

    @Override
    public Map<String, V> deserialize(JsonParser parser) throws IOException {
        return null;
    }
}
