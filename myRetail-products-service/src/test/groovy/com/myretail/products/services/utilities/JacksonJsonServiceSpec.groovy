package com.myretail.products.services.utilities

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.TextNode
import com.fasterxml.jackson.databind.type.TypeFactory
import com.myretail.products.services.exception.InvalidInputServiceException
import groovy.transform.Canonical
import spock.lang.Specification

class JacksonJsonServiceSpec extends Specification {
    
    @Canonical
    private static class Sample {
        BigDecimal bigDecimal
        String string
    }

    private static final String TEST_JSON_FILE = "testJsonFile"
    private static final String TEST_JSON_FIELD_NAME= "testJsonFieldName"
    private Sample sampleObject = new Sample(bigDecimal:123.4, string:"ABCD")
    private String stringFormattedJson = '{  "bigDecimal" : 123.4,  "string" : "ABCD"}'
    private TypeFactory typeFactory = Mock()
    private JsonNode mockJsonNode = Mock()
    private ObjectMapper mockObjectMapper = Mock()
    private JacksonJsonService jacksonJsonService = new JacksonJsonService(
        objectMapper: mockObjectMapper
    )
    
    def "test_getFieldValue_success"() {
        given:
        def jsonNode = new TextNode("test") 
        when:
        def result = jacksonJsonService.getFieldValue(stringFormattedJson, TEST_JSON_FIELD_NAME)
        
        then:
        result == "test"
        
        and:
        1 * mockObjectMapper.readTree(stringFormattedJson) >> mockJsonNode
        1 * mockJsonNode.findValue(TEST_JSON_FIELD_NAME) >> jsonNode
    }
    
    def "test_getFieldValue_InvalidInputServiceException"() {
        when:
        jacksonJsonService.getFieldValue(stringFormattedJson, TEST_JSON_FIELD_NAME)
        
        then:
        def e = thrown(InvalidInputServiceException)
        e.message == "unable to parse JSON input"
    }
    
    def "test_convertJsonToObject_success"() {
        given:
        def sampleObjects = [sampleObject]
               
        when:
        def result = jacksonJsonService.convertToObjects(TEST_JSON_FILE, Sample)
        
        then:
        result == sampleObjects
        
        and:
        1 * mockObjectMapper.getTypeFactory() >> typeFactory
        1 * mockObjectMapper.readValue(_, null) >> sampleObjects
    }
    
    def "test_convertJsonToObject_IllegalArgumentException"() {
        when:
        jacksonJsonService.convertToObjects(jsonFile, classType)
        
        then:
        def e = thrown(IllegalArgumentException)
        e.message == message
        
        where:
        jsonFile        |    classType    |    message
        null            |      null       | "jsonFile can't be null"
        TEST_JSON_FILE  |      null       | "classType can't be null"
        null            |      Sample     | "jsonFile can't be null"
    }
    
    def "test_convertJsonToObject_InvalidInputServiceException"() {
        when:
        jacksonJsonService.convertToObjects(TEST_JSON_FILE, Sample)
        
        then:
        def e = thrown(InvalidInputServiceException)
        e.message == "unable to parse JSON input"
    }
}
