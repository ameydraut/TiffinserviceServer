package org.TastyTiffin.model.response;

import java.util.Optional;

public class ProviderResponse {
    private Optional<String> providerName;
    private Optional<String> imageProviderBucket;
    private Optional<String> imageProviderKey;

    private Optional<String> providerId;

    public ProviderResponse() {
        providerName=Optional.empty();
        imageProviderBucket=Optional.empty();
        imageProviderKey=Optional.empty();
        providerId=Optional.empty();

    }

    public ProviderResponse(Optional<String> providerName, Optional<String> imageProviderBucket, Optional<String> imageProviderKey, Optional<String> providerId) {
        this.providerName = providerName;
        this.imageProviderBucket = imageProviderBucket;
        this.imageProviderKey = imageProviderKey;
        this.providerId= providerId;
    }

    public Optional<String> getProviderName() {
        return providerName;
    }

    public void setProviderName(Optional<String> providerName) {
        this.providerName = providerName;
    }

    public Optional<String> getImageProviderBucket() {
        return imageProviderBucket;
    }

    public void setImageProviderBucket(Optional<String> imageProviderBucket) {
        this.imageProviderBucket = imageProviderBucket;
    }

    public Optional<String> getImageProviderKey() {
        return imageProviderKey;
    }

    public void setImageProviderKey(Optional<String> imageProviderKey) {
        this.imageProviderKey = imageProviderKey;
    }

    public Optional<String> getProviderId() {
        return providerId;
    }

    public void setProviderId(Optional<String> providerId) {
        this.providerId = providerId;
    }
}
