package com.myretail.products.utilities;

import java.util.UUID;

/*
 * This utility should be used as shared library
 * TODO use me as shared lib across projects
 */
public final class UuidUtility {
    
    private UuidUtility() { }
    
    public static String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
