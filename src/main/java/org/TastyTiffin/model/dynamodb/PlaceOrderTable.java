package org.TastyTiffin.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "PlaceOrderTable")
public class PlaceOrderTable {
    private String key;
    private String userId;
    private String providerId;
    private List<String> itemIds;
    private String orderId;
    private String totalPrice;
    private String orderStatus;

    public PlaceOrderTable() {
    }

    public PlaceOrderTable(String key,String userId, String providerId, List<String> itemIds, String totalPrice, String orderId , String orderStatus) {
        this.key = key;
        this.userId = userId;
        this.providerId = providerId;
        this.itemIds = itemIds;
        this.totalPrice = totalPrice;
        this.orderId=orderId;
        this.orderStatus=orderStatus;
    }
    @DynamoDBHashKey(attributeName = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    @DynamoDBRangeKey(attributeName = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @DynamoDBAttribute(attributeName = "providerId")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    @DynamoDBAttribute(attributeName = "itemIds")
    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }
    @DynamoDBAttribute(attributeName = "totalPrice")
    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @DynamoDBAttribute(attributeName = "orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
