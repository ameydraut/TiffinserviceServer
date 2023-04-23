package org.TastyTiffin.model.request;

import java.util.List;
import java.util.Optional;

public class AddProviderRequest {
    private Optional<String> provideId;
    private Optional<String> providerName;
    private Optional<String> provideAddress;
    private Optional<String> geoCoordinates;
    private Optional<String> imageUrl;
    private Optional<String> isFavorite;


    public AddProviderRequest() {
        provideId= Optional.empty();
        providerName= Optional.empty();
        provideAddress= Optional.empty();
        geoCoordinates= Optional.empty();
        imageUrl= Optional.empty();
        isFavorite= Optional.empty();
    }

    public AddProviderRequest(Optional<String> provideId, Optional<String> providerName, Optional<String> provideAddress, Optional<String> geoCoordinates, Optional<String> imageUrl, Optional<String> isFavorite) {
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
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

    public Optional<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Optional<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Optional<String> getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Optional<String> isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "AddProviderRequest{" +
                "provideId=" + provideId +
                ", providerName=" + providerName +
                ", provideAddress=" + provideAddress +
                ", geoCoordinates=" + geoCoordinates +
                ", imageUrl=" + imageUrl +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
