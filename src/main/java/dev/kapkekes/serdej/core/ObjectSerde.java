package dev.kapkekes.serdej.core;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;

public interface ObjectSerde<T> extends Serde {
    JsonGenerator serialize(T value, JsonGenerator generator) throws IOException;
    T deserialize(JsonParser parser) throws IOException;
}
