package org.TastyTiffin.model.request;

import org.json.simple.ItemList;

import java.util.List;
import java.util.Optional;

public class PlaceOrderRequest {
    private Optional<String> userId;
    private Optional<String> providerId;
    private Optional<List<String>> itemIds;
    private Optional<String> totalPrice;


    public PlaceOrderRequest() {
        userId=Optional.empty();
        providerId=Optional.empty();
        itemIds=Optional.empty();
        totalPrice=Optional.empty();

    }

    public PlaceOrderRequest(Optional<String> userId, Optional<String> providerId, Optional<List<String>> itemIds, Optional<String> totalPrice) {
        this.userId = userId;
        this.providerId = providerId;
        this.itemIds = itemIds;
        this.totalPrice = totalPrice;
    }

    public Optional<String> getUserId() {
        return userId;
    }

    public void setUserId(Optional<String> userId) {
        this.userId = userId;
    }

    public Optional<String> getProviderId() {
        return providerId;
    }

    public void setProviderId(Optional<String> providerId) {
        this.providerId = providerId;
    }

    public Optional<List<String>> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Optional<List<String>> itemIds) {
        this.itemIds = itemIds;
    }

    public Optional<String> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Optional<String> totalPrice) {
        this.totalPrice = totalPrice;
    }
}
