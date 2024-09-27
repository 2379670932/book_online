package top.soft.bookonline.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OSSConfig {
    private String accessKeyId;
    private String secretAccessKey;
    private String endPoint;
    private String bucketName;

    public OSSConfig() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            // Load a properties file from class path, inside static method
            properties.load(input);

            // Get the property values
            accessKeyId = properties.getProperty("aliyun.accessKeyId");
            secretAccessKey = properties.getProperty("aliyun.secretAccessKey");
            endPoint = properties.getProperty("aliyun.oss.endPoint");
            bucketName = properties.getProperty("aliyun.oss.bucketName");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Getter methods
    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getBucketName() {
        return bucketName;
    }
}
