package configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


public class AwsS3Config {
    private final String ACCESS_KEY = "AKIAQEIP3GKFAHYBENSN";
    private final String SECRET_KEY = "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";
    private final String REGION = "us-east-1";

    public AmazonS3 getS3ClientFactory() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(REGION)
                .build();
    }
}
