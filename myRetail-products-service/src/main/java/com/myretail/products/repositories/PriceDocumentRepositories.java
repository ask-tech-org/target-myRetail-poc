package com.myretail.products.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.myretail.products.data.model.PriceDocument;

@Repository
public interface PriceDocumentRepositories extends MongoRepository<PriceDocument, String> {

    PriceDocument findByProductId(String productId);

}
