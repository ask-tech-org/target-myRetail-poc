package com.myretail.products.services.utilities;

import java.util.List;

public interface JsonService<T> {

    String getFieldValue(String jsonFormattedString, String fieldName);

    List<T> convertToObjects(String jsonFile, Class<T> classType);
}
