import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class MainTests {

    public static void main(String[] args) throws Exception {
        MainTests t = new MainTests();
        t.TestGsonWithByteArray();
    }


    public void TestGsonWithByteArray() throws Exception {
        MainTests tests = new MainTests();

        String file = tests.readFile("input2.json");


        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(byte[].class, (JsonSerializer<byte[]>) (src, typeOfSrc, context) -> new JsonPrimitive(Base64.getEncoder().encodeToString(src)));
        //builder.registerTypeAdapter(byte[].class, (JsonDeserializer<byte[]>) (json, typeOfT, context) -> Base64.getDecoder().decode(json.getAsString()));

        /*builder.registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverterUtil.Deserializer());*/
        builder.registerTypeAdapter(byte[].class, new JsonByteArrayAdapter.Serializer());
        builder.registerTypeAdapter(byte[].class, new JsonByteArrayAdapter.Deserealize());

        Gson gson = builder.create();

        InvoicePdfFile[] myTypes = gson.fromJson(file, InvoicePdfFile[].class);
        System.out.println(gson.toJson(myTypes));
    }

    public String readFile(String fileName) throws IOException
    {
        FileInputStream inputStream=null;
        StringBuilder builder = new StringBuilder();
        try {
            // Getting ClassLoader obj
            ClassLoader classLoader = this.getClass().getClassLoader();
            // Getting resource(File) from class loader
            File configFile=new File(classLoader.getResource(fileName).getFile());

            inputStream = new FileInputStream(configFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);

                builder.append(line);
            }

            reader.toString();
            reader.close();


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            inputStream.close();
        }

        return builder.toString();

    }




}

class TypeDTO
{
    int id;
    String name;
    ArrayList<ItemDTO> items[];
}

class ItemDTO
{
    int id;
    String name;
    Boolean valid;
}

/*class InvoicePdfFileMain{
    int id;
    ArrayList<InvoicePdfFile> items[];
}*/

class InvoicePdfFile{
    private Long id;

    private String invoiceNumber;

    private byte[] invoicePdfFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public byte[] getInvoicePdfFile() {
        return invoicePdfFile;
    }

    public void setInvoicePdfFile(byte[] invoicePdfFile) {
        this.invoicePdfFile = invoicePdfFile;
    }
}
