package org.TastyTiffin.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.TastyTiffin.model.dynamodb.FoodItem;
import org.TastyTiffin.model.dynamodb.ProviderTable;
import org.TastyTiffin.model.dynamodb.UserTable;
import org.TastyTiffin.model.request.AddFoodItemRequest;
import org.TastyTiffin.model.request.AddProviderRequest;
import org.TastyTiffin.model.request.AddUserRequest;
import org.TastyTiffin.model.response.GetFoodItemResponse;
import org.TastyTiffin.model.response.GetProviderResponse;
import org.TastyTiffin.model.response.GetUserResopnse;
import org.TastyTiffin.s3.S3Operations;

import java.util.*;

public class TastyTiffinService {
    S3Operations s3Operations = new S3Operations();
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDBMapper mapper = new DynamoDBMapper(client);

    public String addUser(AddUserRequest addUserRequest) {
        UserTable userTable = new UserTable();
        userTable.setKey(Character.toString(addUserRequest.getName().get().charAt(0)).toUpperCase());
        userTable.setId(addUserRequest.getId().get());
        addUserRequest.getName().map(name -> {
            userTable.setName(name);
            return name;
        });
        addUserRequest.getAddress().map(address -> {
            userTable.setAddress(address);
            return address;
        });
        addUserRequest.getPhoneNum().map(phoneNum -> {
            userTable.setPhoneNum(phoneNum);
            return phoneNum;
        });
        addUserRequest.getEmail().map(email -> {
            userTable.setEmail(email);
            return email;
        });
        mapper.save(userTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());

        return addUserRequest.getId().get();
    }

    public String addProvider(AddProviderRequest addProviderRequest) {
        ProviderTable providerTable = new ProviderTable();
        providerTable.setKey(Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toUpperCase());
        providerTable.setProvideId(addProviderRequest.getProvideId().get());
        System.out.println(providerTable.getProvideId());
        System.out.println(providerTable.getKey());
        System.out.println(Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toUpperCase());

        addProviderRequest.getProviderName().map(providerName -> {
            providerTable.setProviderName(providerName);
            return providerName;
        });
        addProviderRequest.getProvideAddress().map(provideAddress -> {
            providerTable.setProvideAddress(provideAddress);
            return provideAddress;
        });
        addProviderRequest.getGeoCoordinates().map(geoCoordinates -> {
            providerTable.setGeoCoordinates(geoCoordinates);
            return geoCoordinates;
        });
        addProviderRequest.getImageUrl().map(imageUrl -> {
            providerTable.setImageUrl(imageUrl);
            return imageUrl;
        });
        addProviderRequest.getIsFavorite().map(isFavorite -> {
            providerTable.setFavorite(isFavorite);
            return isFavorite;
        });


        mapper.save(providerTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());


        return addProviderRequest.getProvideId().get();
    }


    public String addFoodItem(AddFoodItemRequest addFoodItemRequest) {
        String providerId = addFoodItemRequest.getProviderId().get();
        String providerHashkey = addFoodItemRequest.getProiderPartitionkey().get();
        ProviderTable providerTable = mapper.load(ProviderTable.class, providerHashkey, providerId);

        String providerName = providerTable.getProviderName();
        UUID uuid = UUID.randomUUID();
        UUID fooditemId=UUID.randomUUID();
        String bucketName = Character.toString(providerName.charAt(0)).toLowerCase();
        bucketName = bucketName + bucketName + bucketName;
        byte[] image = Base64.getDecoder().decode(addFoodItemRequest.getItemImage().get());
        s3Operations.UploadImage(uuid.toString() + "." + addFoodItemRequest.getItemImageType().get(), image, bucketName, new HashMap<>());


        FoodItem foodItemTable = new FoodItem();

        foodItemTable.setItemRating(0.0);
        foodItemTable.setItemCurrency(addFoodItemRequest.getItemCurrency().get());
        foodItemTable.setItemPrice(addFoodItemRequest.getItemPrice().get());
        foodItemTable.setItemImageBucket(bucketName);
        foodItemTable.setItemId(fooditemId.toString());
        foodItemTable.setItemImageKey(uuid.toString() + "." + addFoodItemRequest.getItemImageType());
        foodItemTable.setItemName(addFoodItemRequest.getItemName().get());
        if (providerTable.getItemList() == null) {
            providerTable.setItemList(new ArrayList<FoodItem>());
        }
        providerTable.getItemList().add(foodItemTable);
        mapper.save(providerTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());


        return providerId;
    }

    public GetProviderResponse getProvider(String providerId, String providerHashkey) {
        ProviderTable providerTable = mapper.load(ProviderTable.class, providerHashkey, providerId);
        List<GetFoodItemResponse> getFoodItemResponses = new ArrayList<>();
        if (providerTable.getItemList()!= null){
            for (int i = 0; i < providerTable.getItemList().size(); i++) {
                FoodItem foodItem = providerTable.getItemList().get(i);
                GetFoodItemResponse getFoodItemResponse = new GetFoodItemResponse();
                getFoodItemResponse.setItemName(Optional.of(foodItem.getItemName()));
                getFoodItemResponse.setItemPrice(Optional.of(foodItem.getItemPrice()));
                getFoodItemResponse.setItemRating(Optional.of(foodItem.getItemRating()));
                getFoodItemResponse.setItemImageBucket(Optional.of(foodItem.getItemImageBucket()));
                getFoodItemResponse.setItemImageKey(Optional.of(foodItem.getItemImageKey()));
                getFoodItemResponse.setItemCurrency(Optional.of(foodItem.getItemCurrency()));
                getFoodItemResponses.add(getFoodItemResponse);
                getFoodItemResponse.setItemId(Optional.of(foodItem.getItemId()));
            }
        }

        GetProviderResponse getProviderResponse = new GetProviderResponse();
        getProviderResponse.setProvideId(Optional.of(providerTable.getProvideId()));
        getProviderResponse.setProviderName(Optional.of(providerTable.getProviderName()));
        getProviderResponse.setProvideAddress(Optional.of(providerTable.getProvideAddress()));
        getProviderResponse.setKey(Optional.of(providerTable.getKey()));
        getProviderResponse.setGeoCoordinates(Optional.of(providerTable.getGeoCoordinates()));
        getProviderResponse.setImageUrl(Optional.of(providerTable.getImageUrl()));
        getProviderResponse.setIsFavorite(Optional.of(providerTable.getFavorite()));
        getProviderResponse.setItemList(Optional.of(getFoodItemResponses));

        return getProviderResponse;
    }
    public GetUserResopnse getUser(String userId , String userHashkey){
        System.out.println(userId);
        System.out.println(userHashkey);

        UserTable userTable=mapper.load(UserTable.class, userHashkey , userId);
        GetUserResopnse getUserResopnse=new GetUserResopnse();
        getUserResopnse.setName(Optional.of(userTable.getName()));
        getUserResopnse.setAddress(Optional.of(userTable.getAddress()));
        getUserResopnse.setId(Optional.of(userTable.getId()));
        getUserResopnse.setKey(Optional.of(userTable.getKey()));
        getUserResopnse.setPhoneNum(Optional.of(userTable.getPhoneNum()));
        getUserResopnse.setEmail(Optional.of(userTable.getEmail()));

        return getUserResopnse;
    }
}


