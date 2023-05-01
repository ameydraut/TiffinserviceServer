package org.TastyTiffin.model.request;

import java.util.List;
import java.util.Optional;

public class PostOrderRequest {
    private Optional<String> providerId;
    private Optional<String> userId;
    private Optional<Double> totalPrice;

    public PostOrderRequest() {
        providerId=Optional.empty();
        userId=Optional.empty();
        totalPrice=Optional.empty();
    }

    public PostOrderRequest(Optional<String> providerId, Optional<String> userId, Optional<Double> totalPrice) {
        this.providerId = providerId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public Optional<String> getProviderId() {
        return providerId;
    }

    public void setProviderId(Optional<String> providerId) {
        this.providerId = providerId;
    }

    public Optional<String> getUserId() {
        return userId;
    }

    public void setUserId(Optional<String> userId) {
        this.userId = userId;
    }

    public Optional<Double> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Optional<Double> totalPrice) {
        this.totalPrice = totalPrice;
    }
}
