package org.TastyTiffin.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import java.util.HashMap;

public class TestLambda implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {


    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
       APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse=new APIGatewayV2HTTPResponse();
       String messageBody=" Server id healthy";
       apiGatewayV2HTTPResponse.setBody(messageBody);
       apiGatewayV2HTTPResponse.setStatusCode(200);
       apiGatewayV2HTTPResponse.setHeaders(new HashMap<>());
       apiGatewayV2HTTPResponse.setIsBase64Encoded(false);

       System.out.println("server is healthy");

        return apiGatewayV2HTTPResponse;

    }
}
