package org.TastyTiffin.model.response;

import java.util.Optional;

public class GetFoodItemResponse {
    private Optional<String> itemId;
    private Optional<String> itemName;
    private Optional<String> itemImageBucket;
    private Optional<String> itemImageKey;

    private Optional<Double> itemRating;
    private Optional<String> itemPrice;
    private Optional<String> itemCurrency;

    public GetFoodItemResponse() {
        itemId=Optional.empty();
        itemName= Optional.empty();
        itemCurrency=Optional.empty();
        itemRating=Optional.empty();
        itemImageBucket=Optional.empty();
        itemPrice=Optional.empty();
        itemImageKey=Optional.empty();
    }

    public GetFoodItemResponse(Optional<String>itemId,Optional<String> itemName, Optional<String> itemImageBucket, Optional<String> itemImageKey, Optional<Double> itemRating, Optional<String> itemPrice, Optional<String> itemCurrency) {
        this.itemName = itemName;
        this.itemImageBucket = itemImageBucket;
        this.itemImageKey = itemImageKey;
        this.itemRating = itemRating;
        this.itemPrice = itemPrice;
        this.itemCurrency = itemCurrency;
        this.itemId=itemId;
    }

    public Optional<String> getItemName() {
        return itemName;
    }

    public void setItemName(Optional<String> itemName) {
        this.itemName = itemName;
    }

    public Optional<String> getItemImageBucket() {
        return itemImageBucket;
    }

    public void setItemImageBucket(Optional<String> itemImageBucket) {
        this.itemImageBucket = itemImageBucket;
    }

    public Optional<String> getItemImageKey() {
        return itemImageKey;
    }

    public void setItemImageKey(Optional<String> itemImageKey) {
        this.itemImageKey = itemImageKey;
    }

    public Optional<Double> getItemRating() {
        return itemRating;
    }

    public void setItemRating(Optional<Double> itemRating) {
        this.itemRating = itemRating;
    }

    public Optional<String> getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Optional<String> itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Optional<String> getItemCurrency() {
        return itemCurrency;
    }

    public void setItemCurrency(Optional<String> itemCurrency) {
        this.itemCurrency = itemCurrency;
    }

    public Optional<String> getItemId() {
        return itemId;
    }

    public void setItemId(Optional<String> itemId) {
        this.itemId = itemId;
    }
}

   