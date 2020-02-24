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

    /**
     * Get field value for a given jsonFormattedString and fieldName
     * @param jsonFormattedString, the string that needs to be parsed
     * @param fieldName, the fieldName that need to be used to get the value
     * @return String, the field value
     */
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
    
    /**
     * Converts to list of objects for a given jsonFile and classType
     * @param jsonFile, the file that needs to be used
     * @param classType, the class type that need to be used to get java type
     * @return List<T>, list of objects for the specified classType 
     */
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
