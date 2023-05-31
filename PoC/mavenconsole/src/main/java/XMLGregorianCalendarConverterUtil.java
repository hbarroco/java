import com.google.gson.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to treat the XMLGregorianCalendar when deserialize objects
 */
public class XMLGregorianCalendarConverterUtil {
    /**
     * Method to Serializer
     */
    public static class Serializer implements JsonSerializer<XMLGregorianCalendar> {
        @Override
        public JsonElement serialize(XMLGregorianCalendar xmlGregorianCalendar, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(xmlGregorianCalendar.toXMLFormat());
        }
    }

    /**
     * Method to Deserializer.
     * Here is the treat XMLGregorianCalendar
     */
    public static class Deserializer implements JsonDeserializer<XMLGregorianCalendar> {
        @Override
        public XMLGregorianCalendar deserialize(JsonElement jsonElement, Type type,
                                                JsonDeserializationContext jsonDeserializationContext) {
            try {
                Date currentDate = new Date(Long.valueOf(jsonElement.getAsString()));

                GregorianCalendar gregory = new GregorianCalendar();
                gregory.setTime(currentDate);
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
