package co.com.pichincha.transactions.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class MockFilesReader {
    public static <T> T readObject(String path, Class<T> resultClass, ClassLoader classLoader)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = classLoader.getResource(String.format("mocks/%s", path));
        return objectMapper.readValue(url, resultClass);
    }
}
