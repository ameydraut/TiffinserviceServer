package org.TastyTiffin.model.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBDocument()
public class FoodItem {
    private String itemId;
    private String itemName;
    private String itemImageBucket;
    private String itemImageKey;

    private Double itemRating;
    private String itemPrice;
    private String itemCurrency;


    public FoodItem() {
    }

    public FoodItem( String itemId,String itemName, Double itemRating, String itemPrice, String itemCurrency,String itemImageBucket,String itemImageKey) {
        this.itemImageKey=itemImageKey;
        this.itemName = itemName;
        this.itemImageBucket = itemImageBucket;
        this.itemRating = itemRating;
        this.itemPrice = itemPrice;
        this.itemCurrency = itemCurrency;
        this.itemId=itemId;
    }


    @DynamoDBAttribute(attributeName = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    @DynamoDBAttribute(attributeName = "itemImageBucket")
    public String getItemImageBucket() {
        return itemImageBucket;
    }

    public void setItemImageBucket(String itemImageBucket) {
        this.itemImageBucket = itemImageBucket;
    }
    @DynamoDBAttribute(attributeName = "itemRating")
    public Double getItemRating() {
        return itemRating;
    }

    public void setItemRating(Double itemRating) {
        this.itemRating = itemRating;
    }
    @DynamoDBAttribute(attributeName = "itemPrice")
    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    @DynamoDBAttribute(attributeName = "itemCurrency")
    public String getItemCurrency() {
        return itemCurrency;
    }

    public void setItemCurrency(String itemCurrency) {
        this.itemCurrency = itemCurrency;
    }
    @DynamoDBAttribute(attributeName = "itemImageKey")
    public String getItemImageKey() {
        return itemImageKey;
    }

    public void setItemImageKey(String itemImageKey) {
        this.itemImageKey = itemImageKey;
    }
    @DynamoDBAttribute(attributeName="itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
