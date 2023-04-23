package org.TastyTiffin.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.TastyTiffin.model.dynamodb.ProviderTable;
import org.TastyTiffin.model.dynamodb.UserTable;
import org.TastyTiffin.model.request.AddProviderRequest;
import org.TastyTiffin.model.request.AddUserRequest;

import javax.security.auth.callback.CallbackHandler;

public class TastyTiffinService {

    final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    final DynamoDBMapper mapper = new DynamoDBMapper(client);

    public String addUser(AddUserRequest addUserRequest){
        UserTable userTable =new UserTable();
        userTable.setKey( Character.toString(addUserRequest.getName().get().charAt(0)).toUpperCase());
        userTable.setId(addUserRequest.getId().get());
        addUserRequest.getName().map(name->{
           userTable.setName(name);
           return name;
        });
        addUserRequest.getAddress().map(address->{
            userTable.setAddress(address);
            return address;
        });
        addUserRequest.getPhoneNum().map(phoneNum->{
            userTable.setPhoneNum(phoneNum);
            return phoneNum;
        });
        addUserRequest.getEmail().map(email->{
        userTable.setEmail(email);
        return email;
        });
        mapper.save(userTable,DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());

    return addUserRequest.getId().get();
    }

    public String addProvider(AddProviderRequest addProviderRequest){
        ProviderTable providerTable=new ProviderTable();
        providerTable.setKey(Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toUpperCase());
        providerTable.setProvideId(addProviderRequest.getProvideId().get());
        System.out.println(providerTable.getProvideId());
        System.out.println(providerTable.getKey());
        System.out.println(Character.toString(addProviderRequest.getProviderName().get().charAt(0)).toUpperCase());

        addProviderRequest.getProviderName().map(providerName->{
            providerTable.setProviderName(providerName);
            return providerName;
        });
        addProviderRequest.getProvideAddress().map(provideAddress->{
            providerTable.setProvideAddress(provideAddress);
            return provideAddress;
        });
        addProviderRequest.getGeoCoordinates().map(geoCoordinates->{
            providerTable.setGeoCoordinates(geoCoordinates);
            return geoCoordinates;
        });
        addProviderRequest.getImageUrl().map(imageUrl->{
            providerTable.setImageUrl(imageUrl);
            return imageUrl;
        });
        addProviderRequest.getIsFavorite().map(isFavorite->{
            providerTable.setFavorite(isFavorite);
            return isFavorite;
        });


        mapper.save(providerTable,DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES).build());



        return addProviderRequest.getProvideId().get();
    }

}
