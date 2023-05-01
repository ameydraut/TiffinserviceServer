package org.TastyTiffin.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.TastyTiffin.s3.S3Operations;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DownloadFileLamda implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    S3Operations s3Operations = new S3Operations();
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent apiGatewayV2HTTPEvent, Context context) {
        Map<String, String> parameters = apiGatewayV2HTTPEvent.getQueryStringParameters();
        String bucketName = parameters.get("bucket");
        String key = parameters.get("key");
        APIGatewayV2HTTPResponse apiGatewayV2HTTPResponse = new APIGatewayV2HTTPResponse();
        try {
            byte[] file = s3Operations.downloadImage(key, bucketName);
            apiGatewayV2HTTPResponse.setIsBase64Encoded(true);
            apiGatewayV2HTTPResponse.setBody(Base64.getEncoder().encodeToString(file));
            apiGatewayV2HTTPResponse.setStatusCode(200);
            Map<String, String> headers = new HashMap<>();
            System.out.println("Key - " + key + " bucket - " + bucketName);
            headers.put("Content-Type", "image/" + key.split("\\.")[1]);
            headers.put(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + key);
            apiGatewayV2HTTPResponse.setHeaders(headers);
            return apiGatewayV2HTTPResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
