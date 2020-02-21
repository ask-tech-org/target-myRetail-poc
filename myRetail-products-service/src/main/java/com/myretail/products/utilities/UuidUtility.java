package com.myretail.products.utilities;

import java.util.UUID;

//This utility should be used as shared library
//Future TODO use me as shared
public class UuidUtility {
    
    private UuidUtility() { }
    
    public static String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
