package com.myretail.products.services.utilities;

import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.products.services.exception.InvalidInputServiceException;

@Service
public class JacksonJsonService<T> implements JsonService<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonJsonService.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getFieldValue(String jsonFormattedString, String fieldName) {
        Assert.notNull(jsonFormattedString, "jsonFormattedString can't be null");
        Assert.notNull(fieldName, "fieldName can't be null");
        
        String fieldValue = "";
        JsonNode actualObj = null;
        try {
            actualObj = objectMapper.readTree(jsonFormattedString);
            fieldValue = actualObj.findValue(fieldName).asText();
        } catch (Exception e) {
            throw generateError(e);
        }
        return fieldValue;
    }
    
    @Override
    public List<T> convertToObjects(String jsonFile, Class<T> classType) {
        Assert.notNull(jsonFile, "jsonFile can't be null");
        Assert.notNull(classType, "classType can't be null");
        
        try {
            JavaType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, classType);
            return objectMapper.readValue(new File(jsonFile), javaType);
        } catch (Exception e) {
            throw generateError(e);
        }
    }
    

    private InvalidInputServiceException generateError(Exception e) {
        String message = "unable to parse JSON input";
        LOGGER.error(message, e);
        return new InvalidInputServiceException(message, e);
    }
}
