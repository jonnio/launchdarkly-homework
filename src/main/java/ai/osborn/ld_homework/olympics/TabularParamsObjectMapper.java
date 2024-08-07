package ai.osborn.ld_homework.olympics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TabularParamsObjectMapper implements Converter<String, TabulatorParamsModel> {

    private final ObjectMapper objectMapper;

    public TabularParamsObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public TabulatorParamsModel convert(String source) {
        try {
            return objectMapper.readValue(source, TabulatorParamsModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}