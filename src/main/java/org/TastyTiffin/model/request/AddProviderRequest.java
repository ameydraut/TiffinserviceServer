package org.TastyTiffin.model.request;

import java.util.Optional;

public class AddProviderRequest {
    private Optional<String> provideId;
    private Optional<String> providerName;
    private Optional<String> provideAddress;
    private Optional<String> geoCoordinates;
    private Optional<String> providerImage;

    private Optional<String> providerImageType;
    private Optional<String> isFavorite;


    public AddProviderRequest() {
        provideId= Optional.empty();
        providerName= Optional.empty();
        provideAddress= Optional.empty();
        geoCoordinates= Optional.empty();
        providerImage = Optional.empty();
        isFavorite= Optional.empty();
        providerImageType=Optional.empty();

    }

    public AddProviderRequest(Optional<String> provideId,
                              Optional<String> providerName,
                              Optional<String> provideAddress,
                              Optional<String> geoCoordinates,
                              Optional<String> providerImage,
                              Optional<String> providerImageType,
                              Optional<String> isFavorite) {
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.providerImage = providerImage;
        this.isFavorite = isFavorite;
        this.providerImageType=providerImageType;

    }

    public Optional<String> getProvideId() {
        return provideId;
    }

    public void setProvideId(Optional<String> provideId) {
        this.provideId = provideId;
    }

    public Optional<String> getProviderName() {
        return providerName;
    }

    public void setProviderName(Optional<String> providerName) {
        this.providerName = providerName;
    }

    public Optional<String> getProvideAddress() {
        return provideAddress;
    }

    public void setProvideAddress(Optional<String> provideAddress) {
        this.provideAddress = provideAddress;
    }

    public Optional<String> getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(Optional<String> geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }

    public Optional<String> getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(Optional<String> providerImage) {
        this.providerImage = providerImage;
    }

    public Optional<String> getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Optional<String> isFavorite) {
        this.isFavorite = isFavorite;
    }
    public Optional<String> getProviderImageType() {
        return providerImageType;
    }

    public void setProviderImageType(Optional<String> providerImageType) {
        this.providerImageType = providerImageType;
    }

    @Override
    public String toString() {
        return "AddProviderRequest{" +
                "provideId=" + provideId +
                ", providerName=" + providerName +
                ", provideAddress=" + provideAddress +
                ", geoCoordinates=" + geoCoordinates +
                ", providerImage=" + providerImage +
                ", providerImageType=" + providerImageType +
                ", isFavorite=" + isFavorite +

                '}';
    }
}
