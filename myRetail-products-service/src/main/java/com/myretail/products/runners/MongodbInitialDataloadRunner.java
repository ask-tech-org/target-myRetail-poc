package com.myretail.products.runners;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;
import com.myretail.products.data.model.PriceDocument;
import com.myretail.products.repositories.PriceDocumentRepositories;
import com.myretail.products.services.utilities.JsonService;

@Component
/*
 * preloading this only for dev environment just for POC
 * should be pointer to a MongoDB datastore for upper environment eg. test, stage and prod
 */
@ActiveProfiles("dev")
public class MongodbInitialDataloadRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongodbInitialDataloadRunner.class);
    
    //expecting initialLoadFile to be loaded from classpath
    @Value("${product.price.initial.load.file}")
    private String initialLoadFile;
    
    @Autowired
    private PriceDocumentRepositories priceDocumentRepositories;
    
    @Autowired
    private JsonService<PriceDocument> jsonService;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("preloading test data {}", initialLoadFile);
        String initialLoadFileAbsolutePath = ResourceUtils.getFile(initialLoadFile)
                    .getAbsolutePath();
        List<PriceDocument> priceDocuments = jsonService
                .convertToObjects(initialLoadFileAbsolutePath, PriceDocument.class);
        priceDocumentRepositories.saveAll(priceDocuments)
            .forEach(action -> 
                LOGGER.info("preloaded productid: {}", action));
    }
}
