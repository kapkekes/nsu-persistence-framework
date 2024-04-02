package dev.kapkekes.serdej.core.collections;

import com.fasterxml.jackson.core.JsonFactory;
import dev.kapkekes.serdej.core.wrappers.IntegerSerde;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

class TestListSerde {
    @Test
    void testIdempotence() throws IOException {
        final var list = List.of(1, 2, 3, 4);
        final var jsonFactory = new JsonFactory();
        final var serde = new ListSerde<>(new IntegerSerde());

        try (final var writer = new StringWriter()) {
            try (final var generator = jsonFactory.createGenerator(writer)) {
                serde.serialize(list, generator);
            }

            try (final var reader = new StringReader(writer.toString())) {
                try (final var parser = jsonFactory.createParser(reader)) {
                    assert serde.deserialize(parser).equals(list);
                }
            }
        }
    }
}
