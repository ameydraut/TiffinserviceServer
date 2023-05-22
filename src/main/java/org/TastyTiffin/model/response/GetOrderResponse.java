package org.TastyTiffin.model.response;

import java.util.List;
import java.util.Optional;

public class GetOrderResponse {
    private Optional<String> userId;
    private Optional<String> providerId;
    private Optional<List<String>> itemIds;
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
    }

    public GetOrderResponse(Optional<String> userId, Optional<String> providerId, Optional<List<String>> itemIds, Optional<String> orderId, Optional<String> totalPrice, Optional<String> orderStatus) {
        this.userId = userId;
        this.providerId = providerId;
        this.itemIds = itemIds;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
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
}
