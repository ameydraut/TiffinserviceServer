package org.TastyTiffin.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.TastyTiffin.model.dynamodb.UserTable;
import org.TastyTiffin.model.request.AddUserRequest;

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
}
