package dev.kapkekes.serdej.core.wrappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import dev.kapkekes.serdej.annotations.RegisterSerde;
import dev.kapkekes.serdej.core.ObjectSerde;

import java.io.IOException;

@RegisterSerde
public class CharacterSerde implements ObjectSerde<Character> {
    @Override
    public JsonGenerator serialize(Character value, JsonGenerator generator) throws IOException {
        generator.writeString(String.valueOf(value));
        return generator;
    }

    @Override
    public Character deserialize(JsonParser parser) throws IOException {
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
