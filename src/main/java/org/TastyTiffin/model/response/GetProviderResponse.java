package org.TastyTiffin.model.response;

import org.TastyTiffin.model.dynamodb.FoodItem;

import java.util.List;
import java.util.Optional;

public class GetProviderResponse {
    private Optional <List<GetFoodItemResponse>> itemList;
    private Optional<String> key;
    private Optional<String> provideId;
    private Optional<String> providerName;
    private Optional<String> provideAddress;
    private Optional<String> geoCoordinates;
    private Optional<String> imageUrl;
    private Optional<String> isFavorite;

    public GetProviderResponse() {
        itemList =Optional.empty();
        key=Optional.empty();
        provideId=Optional.empty();
        provideAddress=Optional.empty();
        providerName=Optional.empty();
        geoCoordinates=Optional.empty();
        imageUrl=Optional.empty();
        isFavorite=Optional.empty();
    }

    public GetProviderResponse(Optional<List<GetFoodItemResponse>> itemList, Optional<String> key, Optional<String> provideId, Optional<String> providerName, Optional<String> provideAddress, Optional<String> geoCoordinates, Optional<String> imageUrl, Optional<String> isFavorite) {
        this.itemList = itemList;
        this.key = key;
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }

    public Optional<List<GetFoodItemResponse>> getItemList() {
        return itemList;
    }

    public void setItemList(Optional<List<GetFoodItemResponse>> itemList) {
        this.itemList = itemList;
    }

    public Optional<String> getKey() {
        return key;
    }

    public void setKey(Optional<String> key) {
        this.key = key;
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
}
