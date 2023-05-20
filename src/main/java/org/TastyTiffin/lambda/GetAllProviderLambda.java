package org.TastyTiffin.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.TastyTiffin.model.response.GetAllProviderResponse;
import org.TastyTiffin.model.response.GetProviderResponse;
import org.TastyTiffin.service.TastyTiffinService;

import java.util.HashMap;
import java.util.Map;

public class GetAllProviderLambda implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    TastyTiffinService tastyTiffinService=new TastyTiffinService();
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
        APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse=new APIGatewayV2HTTPResponse();
        Map<String,String> parameters = apiGatewayV2HTTPEvent.getQueryStringParameters();

        System.out.println("hello");
        GetAllProviderResponse getAllProviderResponse= tastyTiffinService.getAllProviders();
        try {
            apiGatewayV2HTTPResponse.setBody(new ObjectMapper().registerModule(new Jdk8Module()).writeValueAsString(getAllProviderResponse));
            apiGatewayV2HTTPResponse.setStatusCode(200);
            apiGatewayV2HTTPResponse.setHeaders(new HashMap<>());
            apiGatewayV2HTTPResponse.setIsBase64Encoded(false);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return apiGatewayV2HTTPResponse;
    }
}
