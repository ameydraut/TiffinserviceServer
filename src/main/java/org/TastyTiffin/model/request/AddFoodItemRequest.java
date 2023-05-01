package org.TastyTiffin.model.request;

import java.util.Optional;

public class AddFoodItemRequest {
    private Optional<String> providerId;
    private Optional<String> itemName;
    private Optional<String> itemImage;

    private Optional<String> itemImageType;

    private Optional<String> itemPrice;
    private Optional<String> itemCurrency;

    private Optional<String> proiderPartitionkey;

    public AddFoodItemRequest() {
        providerId =Optional.empty();
        itemName=Optional.empty();
        itemImage=Optional.empty();
        proiderPartitionkey=Optional.empty();
        itemPrice=Optional.empty();
        itemCurrency=Optional.empty();
        itemImageType= Optional.empty();
    }

    public AddFoodItemRequest(Optional<String> providerId, Optional<String> itemName, Optional<String> itemImageUrl, Optional<String> itemRating, Optional<String> itemPrice, Optional<String> itemCurrency, Optional<String> proiderPartitionkey, Optional<String> itemImageType) {
        this.providerId = providerId;
        this.itemName = itemName;
        this.itemImage = itemImageUrl;
        this.proiderPartitionkey= proiderPartitionkey;
        this.itemPrice = itemPrice;
        this.itemCurrency = itemCurrency;
        this.itemImageType = itemImageType;
    }

    public Optional<String> getItemImageType() {
        return itemImageType;
    }

    public void setItemImageType(Optional<String> itemImageType) {
        this.itemImageType = itemImageType;
    }

    public Optional<String> getProviderId() {
        return providerId;
    }

    public void setProviderId(Optional<String> providerId) {
        this.providerId = providerId;
    }

    public Optional<String> getItemName() {
        return itemName;
    }

    public void setItemName(Optional<String> itemName) {
        this.itemName = itemName;
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

    @Override
    public String toString() {
        return "AddFoodItemRequest{" +
                "itemId=" + providerId +
                ", itemName=" + itemName +
                ", itemPrice=" + itemPrice +
                ", itemCurrency=" + itemCurrency +
                ", proiderPartitionkey=" + proiderPartitionkey +
                '}';
    }

    public Optional<String> getItemImage() {
        return itemImage;
    }

    public void setItemImage(Optional<String> itemImage) {
        this.itemImage = itemImage;
    }

    public Optional<String> getProiderPartitionkey() {
        return proiderPartitionkey;
    }

    public void setProiderPartitionkey(Optional<String> proiderPartitionkey) {
        this.proiderPartitionkey = proiderPartitionkey;
    }
}
