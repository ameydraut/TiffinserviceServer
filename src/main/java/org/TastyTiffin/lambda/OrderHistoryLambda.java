package org.TastyTiffin.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.TastyTiffin.model.response.GetOrderHistoryResponse;
import org.TastyTiffin.service.TastyTiffinService;

import java.util.HashMap;
import java.util.Map;

public class OrderHistoryLambda implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
        APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse=new APIGatewayV2HTTPResponse();
        String body= apiGatewayV2HTTPEvent.getBody();
        TastyTiffinService tastyTiffinService=new TastyTiffinService();
        Map<String,String> parameters = apiGatewayV2HTTPEvent.getQueryStringParameters();
        String id = parameters.get("id");
        String userType=parameters.get("userType");
        System.out.println("User Type is"+ userType);
        GetOrderHistoryResponse getOrderHistoryResponse;
        if(userType.equals("PROVIDER")){
            getOrderHistoryResponse=tastyTiffinService.getProviderOrderHistory(id);
        }
        else {
             getOrderHistoryResponse=tastyTiffinService.getUserOrderHistory(id);

        }

        try {

            apiGatewayV2HTTPResponse.setBody(new ObjectMapper().registerModule(new Jdk8Module()).writeValueAsString(getOrderHistoryResponse));
            apiGatewayV2HTTPResponse.setStatusCode(200);
            apiGatewayV2HTTPResponse.setHeaders(new HashMap<>());
            apiGatewayV2HTTPResponse.setIsBase64Encoded(false);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return apiGatewayV2HTTPResponse;


    }
}
