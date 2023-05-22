package org.TastyTiffin.model.request;

import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.Optional;

public class OrderStatusRequest {
    private Optional<String> orderId;
    private Optional<String> orderStatus;
    private Optional<String> partitionKey;

    public OrderStatusRequest() {
        orderId=Optional.empty();
        partitionKey=Optional.empty();
        orderStatus=Optional.empty();
    }

    public OrderStatusRequest(Optional<String> orderId, Optional<String> orderStatus, Optional<String> partitionKey) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.partitionKey= partitionKey;
    }

    public Optional<String> getOrderId() {
        return orderId;
    }

    public void setOrderId(Optional<String> orderId) {
        this.orderId = orderId;
    }

    public Optional<String> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Optional<String> orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Optional<String> getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(Optional<String> partitionKey) {
        this.partitionKey = partitionKey;
    }
}
