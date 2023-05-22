package org.TastyTiffin.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName = "ProviderTable")
public class ProviderTable {
    private List<FoodItem> itemList;
    private String key;
    private String provideId;
    private String providerName;
    private String provideAddress;
    private String geoCoordinates;
    private String providerImageBucket;
    private String providerImageKey;
    private String isFavorite;
    private List<String> orderHistory;


    public ProviderTable() {
    }

    public ProviderTable( String key,String provideId, String providerName, String provideAddress, String geoCoordinates, String imageUrl, String isFavorite , List<FoodItem> itemList,String providerImageKey, List<String> orderHistory) {
        this.key=key;
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.providerImageBucket = imageUrl;
        this.isFavorite = isFavorite;
        this.itemList=itemList;
        this.providerImageKey=providerImageKey;
        this.orderHistory=orderHistory;
    }
    @DynamoDBHashKey(attributeName = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
       this.key = key;
    }
    @DynamoDBRangeKey(attributeName = "provideId")
    public String getProvideId() {
        return provideId;
    }

    public void setProvideId(String provideId) {
        this.provideId = provideId;
    }
    @DynamoDBAttribute(attributeName = "providerName")
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    @DynamoDBAttribute(attributeName = "provideAddress")
    public String getProvideAddress() {
        return provideAddress;
    }

    public void setProvideAddress(String provideAddress) {
        this.provideAddress = provideAddress;
    }
    @DynamoDBAttribute(attributeName = "geoCoordinates")
    public String getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(String geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }
    @DynamoDBAttribute(attributeName = "providerImageBucket")
    public String getProviderImageBucket() {
        return providerImageBucket;
    }

    public void setProviderImageBucket(String providerImageBucket) {
        this.providerImageBucket = providerImageBucket;
    }
    @DynamoDBAttribute(attributeName = "isFavorite")
    public String getFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }

    public List<FoodItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItem> itemList) {
        this.itemList = itemList;
    }

    @DynamoDBAttribute(attributeName = "providerImageKey")
    public String getProviderImageKey() {
        return providerImageKey;
    }

    public void setProviderImageKey(String providerImageKey) {
        this.providerImageKey = providerImageKey;
    }

    @DynamoDBAttribute(attributeName = "orderHistory")
    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<String> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
