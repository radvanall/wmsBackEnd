package com.warehousemanagement.wms.googleConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage googleCloudStorage() {
        // Load Google Cloud Storage credentials and create a Storage object
        Storage storage = StorageOptions.getDefaultInstance().getService();
        return storage;
    }
}