package org.TastyTiffin.model.request;

import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.Optional;

public class AddProviderRequest {
    private Optional<String> provideId;
    private Optional<String> providerName;
    private Optional<String> provideAddress;
    private Optional<String> geoCoordinates;
    private Optional<String> providerImage;

    private Optional<String> providerImageType;
    private Optional<String> isFavorite;
    private Optional<String> providerToken;


    public AddProviderRequest() {
        provideId= Optional.empty();
        providerName= Optional.empty();
        provideAddress= Optional.empty();
        geoCoordinates= Optional.empty();
        providerImage = Optional.empty();
        isFavorite= Optional.empty();
        providerImageType=Optional.empty();
        providerToken=Optional.empty();

    }

    public AddProviderRequest(Optional<String> provideId,
                              Optional<String> providerName,
                              Optional<String> provideAddress,
                              Optional<String> geoCoordinates,
                              Optional<String> providerImage,
                              Optional<String> providerImageType,
                              Optional<String> isFavorite,
                              Optional<String> providerToken
                              ) {
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.providerImage = providerImage;
        this.isFavorite = isFavorite;
        this.providerImageType=providerImageType;
        this.providerToken=providerToken;

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
    public Optional<String> getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(Optional<String> providerToken) {
        this.providerToken = providerToken;
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
                ", providerToken=" + providerToken +
                '}';
    }


}
