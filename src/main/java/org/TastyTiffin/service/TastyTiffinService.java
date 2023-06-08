package org.TastyTiffin.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.TastyTiffin.lambda.OrderStatus;
import org.TastyTiffin.model.dynamodb.FoodItem;
import org.TastyTiffin.model.dynamodb.PlaceOrderTable;
import org.TastyTiffin.model.dynamodb.ProviderTable;
import org.TastyTiffin.model.dynamodb.UserTable;
import org.TastyTiffin.model.request.*;
import org.TastyTiffin.model.response.*;
import org.TastyTiffin.s3.S3Operations;

import java.util.*;

public class TastyTiffinService {
    S3Operations s3Operations = new S3Operations();
    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDBMapper mapper = new DynamoDBMapper(client);
    AmazonSNS amazonSNSClient = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
            .build();

    String applicationArn="arn:aws:sns:ap-south-1:489821093454:app/GCM/TiffinService";

    public String addUser(AddUserRequest addUserRequest) {
        UserTable userTable = new UserTable();
        userTable.setKey("USER");
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

        userTable.setOrderHistory(new ArrayList<>());
        String arn=createEndpoint(addUserRequest.getUserToken().get());

        userTable.setUserToken(arn);
        mapper.save(userTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());

        return addUserRequest.getId().get();
    }

    public String addProvider(AddProviderRequest addProviderRequest) {
        ProviderTable providerTable = new ProviderTable();
        providerTable.setKey("PROVIDER");
        providerTable.setProvideId(addProviderRequest.getProvideId().get());
        UUID uuid = UUID.randomUUID();
        String bucketName = Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toLowerCase();
        bucketName = bucketName + bucketName + bucketName;
        byte[] image = Base64.getDecoder().decode(addProviderRequest.getProviderImage().get());
        s3Operations.UploadImage(uuid.toString() + "." + addProviderRequest.getProviderImageType().get(), image, bucketName, new HashMap<>());

        System.out.println(providerTable.getProvideId());
        System.out.println(providerTable.getKey());
        System.out.println(Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toUpperCase());
        providerTable.setProviderImageKey(uuid.toString() + "." + addProviderRequest.getProviderImageType().get());
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
        providerTable.setProviderImageBucket(bucketName);

        addProviderRequest.getIsFavorite().map(isFavorite -> {
            providerTable.setFavorite(isFavorite);
            return isFavorite;
        });
        providerTable.setOrderHistory(new ArrayList<>());
        String arn=createEndpoint(addProviderRequest.getProviderToken().get());
        providerTable.setProviderToken(arn);

        mapper.save(providerTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());


        return addProviderRequest.getProvideId().get();
    }


    public String addFoodItem(AddFoodItemRequest addFoodItemRequest) {
        String providerId = addFoodItemRequest.getProviderId().get();
        String providerHashkey = addFoodItemRequest.getProiderPartitionkey().get();
        ProviderTable providerTable = mapper.load(ProviderTable.class, providerHashkey, providerId);

        String providerName = providerTable.getProviderName();
        UUID uuid = UUID.randomUUID();
        UUID fooditemId = UUID.randomUUID();
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
        if (providerTable.getItemList() != null) {
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
        getProviderResponse.setImageUrl(Optional.of(providerTable.getProviderImageBucket()));
        getProviderResponse.setIsFavorite(Optional.of(providerTable.getFavorite()));
        getProviderResponse.setItemList(Optional.of(getFoodItemResponses));

        return getProviderResponse;
    }

    public GetUserResopnse getUser(String userId, String userHashkey) {
        System.out.println(userId);
        System.out.println(userHashkey);

        UserTable userTable = mapper.load(UserTable.class, userHashkey, userId);
        GetUserResopnse getUserResopnse = new GetUserResopnse();
        getUserResopnse.setName(Optional.of(userTable.getName()));
        getUserResopnse.setAddress(Optional.of(userTable.getAddress()));
        getUserResopnse.setId(Optional.of(userTable.getId()));
        getUserResopnse.setKey(Optional.of(userTable.getKey()));
        getUserResopnse.setPhoneNum(Optional.of(userTable.getPhoneNum()));
        getUserResopnse.setEmail(Optional.of(userTable.getEmail()));

        return getUserResopnse;
    }

    public GetAllProviderResponse getAllProviders() {

        PaginatedScanList<ProviderTable> getProviderResponseList = mapper.scan(ProviderTable.class, new DynamoDBScanExpression());
        List<ProviderResponse> providerResponseList = new ArrayList<>();
        GetAllProviderResponse getAllProviderResponse = new GetAllProviderResponse();
        getAllProviderResponse.setProviderResponseList(Optional.of(providerResponseList));
        getProviderResponseList.forEach(providerTable -> {
            ProviderResponse providerResponse = new ProviderResponse();
            providerResponse.setProviderName(Optional.of(providerTable.getProviderName()));
            providerResponse.setImageProviderBucket(Optional.of(providerTable.getProviderImageBucket()));
            providerResponse.setImageProviderKey(Optional.of(providerTable.getProviderImageKey()));
            providerResponse.setProviderId(Optional.of(providerTable.getProvideId()));
            providerResponseList.add(providerResponse);

        });
        return getAllProviderResponse;
    }

    public String placeOrderRequest(PlaceOrderRequest placeOrderRequest) throws JsonProcessingException {
        String providerId = placeOrderRequest.getProviderId().get();
        ProviderTable providerTable = mapper.load(ProviderTable.class, "PROVIDER", providerId);
        String userId = placeOrderRequest.getUserId().get();
        UserTable userTable = mapper.load(UserTable.class, "USER", userId);

        String providerToken= providerTable.getProviderToken();
        String userToken= userTable.getUserToken();

        PlaceOrderTable placeOrderTable = new PlaceOrderTable();
        placeOrderTable.setKey("ORDERS");
        UUID orderId = UUID.randomUUID();
        placeOrderTable.setOrderStatus("PLACED");
        placeOrderTable.setOrderId(orderId.toString());
        placeOrderRequest.getItemIds().map(itemIds -> {
            placeOrderTable.setItemIds(itemIds);
            return placeOrderTable;
        });
        placeOrderRequest.getUserId().map(uId -> {
            placeOrderTable.setUserId(uId);
            return placeOrderTable;
        });
        placeOrderRequest.getProviderId().map(pId -> {
            placeOrderTable.setProviderId(pId);
            return placeOrderTable;
        });
        placeOrderRequest.getTotalPrice().map(totalPrice -> {
            placeOrderTable.setTotalPrice(totalPrice);
            return placeOrderTable;
        });
        providerTable.getOrderHistory().add(orderId.toString());
        userTable.getOrderHistory().add(orderId.toString());
        mapper.save(placeOrderTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());
        mapper.save(providerTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());
        mapper.save(userTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());

        PlaceOrderTable placeOrderTable1 = mapper.load(PlaceOrderTable.class, "ORDERS", orderId.toString());
        GetOrderResponse getOrderResponse=new GetOrderResponse();
        getOrderResponse.setOrderId(Optional.of(placeOrderTable1.getOrderId()));
        getOrderResponse.setOrderStatus(Optional.of(placeOrderTable1.getOrderStatus()));
        getOrderResponse.setItemIds(Optional.of(placeOrderTable1.getItemIds()));
        getOrderResponse.setProviderId(Optional.of(placeOrderTable1.getProviderId()));
        getOrderResponse.setTotalPrice(Optional.of(placeOrderTable1.getTotalPrice()));
        getOrderResponse.setUserId(Optional.of(placeOrderTable1.getUserId()));
        String eventBody = new ObjectMapper().registerModule(new Jdk8Module()).writeValueAsString(getOrderResponse);
        amazonSNSClient.publish(providerToken,eventBody);
        amazonSNSClient.publish(userToken,eventBody);

        return orderId.toString();
    }

    public String orderStatus(OrderStatusRequest orderStatusRequest) {
        String orderId = orderStatusRequest.getOrderId().get();
        String orderHashkey = orderStatusRequest.getPartitionKey().get();
        PlaceOrderTable placeOrderTable = mapper.load(PlaceOrderTable.class, orderHashkey, orderId);
        placeOrderTable.setOrderStatus(orderStatusRequest.getOrderStatus().get());
        mapper.save(placeOrderTable, DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());
        return orderId;
    }

    public GetOrderHistoryResponse getUserOrderHistory(String id) {

        UserTable userTable = mapper.load(UserTable.class, "USER", id);
        List<String> orderIds = userTable.getOrderHistory();
        List<GetOrderResponse> responseList = new ArrayList<>();
        for(String orderId : orderIds) {
            responseList.add(getOrderDetails(orderId));
        }
        return new GetOrderHistoryResponse(Optional.of(responseList));


    }

    public GetOrderHistoryResponse getProviderOrderHistory(String id) {

        ProviderTable providerTable = mapper.load(ProviderTable.class, "PROVIDER", id);
        List<String> orderIds = providerTable.getOrderHistory();
        List<GetOrderResponse> responseList = new ArrayList<>();
        for(String orderId : orderIds) {
            responseList.add(getOrderDetails(orderId));
        }
        return new GetOrderHistoryResponse(Optional.of(responseList));

    }

    public GetOrderResponse getOrderDetails(String orderId){
        PlaceOrderTable placeOrderTable=mapper.load(PlaceOrderTable.class,"ORDERS",orderId);
        GetOrderResponse getOrderResponse=new GetOrderResponse();
        getOrderResponse.setOrderId(Optional.of(placeOrderTable.getOrderId()));
        getOrderResponse.setOrderStatus(Optional.of(placeOrderTable.getOrderStatus()));
        getOrderResponse.setItemIds(Optional.of(placeOrderTable.getItemIds()));
        getOrderResponse.setProviderId(Optional.of(placeOrderTable.getProviderId()));
        getOrderResponse.setTotalPrice(Optional.of(placeOrderTable.getTotalPrice()));
        getOrderResponse.setUserId(Optional.of(placeOrderTable.getUserId()));

        return getOrderResponse;

    }
    public String createEndpoint(String token){

        CreatePlatformEndpointRequest createPlatformEndpointRequest = new CreatePlatformEndpointRequest();
        createPlatformEndpointRequest.withPlatformApplicationArn(applicationArn);
        createPlatformEndpointRequest.withToken(token);
        CreatePlatformEndpointResult createPlatformEndpointResult= amazonSNSClient.createPlatformEndpoint(createPlatformEndpointRequest);

        return createPlatformEndpointResult.getEndpointArn();
    }

}
