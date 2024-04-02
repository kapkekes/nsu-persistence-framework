package dev.kapkekes.serdej.core.primitives;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.PrimitiveSerde;

import java.io.IOException;

@RegisterSerde
public class CharSerde implements PrimitiveSerde {
    public JsonGenerator serialize(char value, JsonGenerator generator) throws IOException {
        generator.writeString(String.valueOf(value));
        return generator;
    }

    public char deserialize(JsonParser parser) throws IOException {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var value = parser.getValueAsString();

        if (value.length() == 1) {
            parser.nextToken();
            return value.charAt(0);
        }

        throw new InputCoercionException(parser, String.format("String value (%s) cannot be interpreted as a Character", parser.getText()), JsonToken.VALUE_STRING, Character.TYPE);
    }
}
