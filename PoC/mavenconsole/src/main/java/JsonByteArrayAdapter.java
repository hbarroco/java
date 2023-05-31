import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Base64;

/**
 * Class to treat the byte[] when serialize and deserialize objects
 */
public class JsonByteArrayAdapter {

    public static class Deserealize implements JsonDeserializer<byte[]> {

        @Override
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.getDecoder().decode(json.getAsString());
        }
    }

    public static class Serializer implements  JsonSerializer<byte[]>{

        @Override
        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
        }

    }
}
