package org.TastyTiffin.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "ProviderTable")
public class ProviderTable {
    private String key;
    private String provideId;
    private String providerName;
    private String provideAddress;
    private String geoCoordinates;
    private String imageUrl;
    private String isFavorite;


    public ProviderTable() {
    }

    public ProviderTable(String key, String provideId, String providerName, String provideAddress, String geoCoordinates, String imageUrl, String isFavorite) {
        key = key;
        this.provideId = provideId;
        this.providerName = providerName;
        this.provideAddress = provideAddress;
        this.geoCoordinates = geoCoordinates;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
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
    @DynamoDBAttribute(attributeName = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @DynamoDBAttribute(attributeName = "isFavorite")
    public String getFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }
}
