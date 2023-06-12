package org.TastyTiffin.model.response;

import java.util.List;
import java.util.Optional;

public class GetOrderResponse {
    private Optional<String> userId;
    private Optional<String> providerId;
    private Optional<ProviderResponse> provider;
    private Optional<List<String>> itemIds;
    private Optional<List<GetFoodItemResponse>> items;
    private Optional<String> orderId;
    private Optional<String> totalPrice;
    private Optional<String> orderStatus;

    public GetOrderResponse() {
        userId=Optional.empty();
        providerId=Optional.empty();
        itemIds=Optional.empty();
        orderId=Optional.empty();
        totalPrice=Optional.empty();
        orderStatus=Optional.empty();
        provider = Optional.empty();
    }

    public GetOrderResponse(Optional<String> userId,
                            Optional<String> providerId,
                            Optional<List<String>> itemIds,
                            Optional<List<GetFoodItemResponse>> items,
                            Optional<String> orderId,
                            Optional<String> totalPrice,
                            Optional<String> orderStatus,
                            Optional<ProviderResponse> provider) {
        this.userId = userId;
        this.providerId = providerId;
        this.itemIds = itemIds;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.items = items;
        this.provider = provider;
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

    public Optional<List<GetFoodItemResponse>> getItems() {
        return items;
    }
    public Optional<List<String>> getItemIds() {
        return itemIds;
    }

    public void setItems(Optional<List<GetFoodItemResponse>> items) {
        this.items = items;
    }
    public void setItemIds(Optional<List<String>> itemIds) {
        this.itemIds = itemIds;
    }

    public Optional<String> getOrderId() {
        return orderId;
    }

    public void setOrderId(Optional<String> orderId) {
        this.orderId = orderId;
    }

    public Optional<String> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Optional<String> totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Optional<String> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Optional<String> orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Optional<ProviderResponse> getProvider() {
        return provider;
    }

    public void setProvider(Optional<ProviderResponse> provider) {
        this.provider = provider;
    }
}
