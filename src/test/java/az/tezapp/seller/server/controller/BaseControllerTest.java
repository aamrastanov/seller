package az.tezapp.seller.server.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseControllerTest {

    @Value("${local.server.port}")
    protected int port;

    protected MediaType TEXT_PLAIN_UTF8 = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));

    protected MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected Filter springSecurityFilterChain;

    protected MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    protected void initMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
    }

    protected String objectToJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    protected <T> T jsonStringToObject(String content, Class<T> valueType) throws IOException {
        return objectMapper.readValue(content, valueType);
    }
}
