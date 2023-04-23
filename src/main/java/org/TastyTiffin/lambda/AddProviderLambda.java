package org.TastyTiffin.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.TastyTiffin.model.request.AddProviderRequest;
import org.TastyTiffin.service.TastyTiffinService;

import java.util.HashMap;

public class AddProviderLambda implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
        APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse=new APIGatewayV2HTTPResponse();
        String body= apiGatewayV2HTTPEvent.getBody();
        TastyTiffinService tastyTiffinService=new TastyTiffinService();

        try {
            AddProviderRequest addProviderRequest =new ObjectMapper().registerModule(new Jdk8Module()).readValue(body,AddProviderRequest.class);
            String providerId=tastyTiffinService.addProvider(addProviderRequest);

            apiGatewayV2HTTPResponse.setBody(providerId);
            apiGatewayV2HTTPResponse.setStatusCode(200);
            apiGatewayV2HTTPResponse.setHeaders(new HashMap<>());
            apiGatewayV2HTTPResponse.setIsBase64Encoded(false);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return apiGatewayV2HTTPResponse;
    }
}
