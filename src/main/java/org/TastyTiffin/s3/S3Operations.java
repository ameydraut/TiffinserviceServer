package org.TastyTiffin.s3;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class S3Operations {
    static Region region=Region.EU_NORTH_1;
    static S3Client s3Client=S3Client.builder().region(region).build();

    static String bucketNamePrefix = "tiffinservice-";

    public void UploadImage(String key, byte[] file,String bucketName, Map<String,String> metadata ){
        bucketName = bucketNamePrefix + bucketName;
        createBucketIfNotExist(bucketName);
        PutObjectRequest putObjectRequest= PutObjectRequest.builder().bucket(bucketName)
                .key(key)
                .metadata(metadata)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file));

    }
    public String createBucketIfNotExist(String bucketName){
        if (!s3Client.listBuckets().buckets().stream().map(bucket -> {return bucket.name();}).collect(Collectors.toList()).contains(bucketName)){
            S3Waiter waiter=s3Client.waiter();
            CreateBucketRequest createBucketRequest =  CreateBucketRequest.builder().bucket(bucketName).build();
            s3Client.createBucket(createBucketRequest);
            HeadBucketRequest bucketRequestwait=HeadBucketRequest.builder().bucket(bucketName).build();
            WaiterResponse<HeadBucketResponse> waiterResponse= waiter.waitUntilBucketExists(bucketRequestwait);
            waiterResponse.matched().response().ifPresent(System.out::println);
            System.out.println("Bucket Created - " + bucketName);
        }
        return bucketName;
    }

    public byte[] downloadImage(String key, String bucket) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucket).key(key).build();
        return s3Client.getObject(getObjectRequest).readAllBytes();
    }
}
