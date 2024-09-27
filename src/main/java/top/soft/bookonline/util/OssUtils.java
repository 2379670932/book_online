package top.soft.bookonline.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import top.soft.bookonline.config.OSSConfig;
import jakarta.servlet.http.Part;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class OssUtils {
    private String accessKeyId;


    private String secretAccessKey;


    private String endPoint;

    private String bucketName;

//    @Resource
//    private IResourceService iResourceService;

    public OssUtils(){
        OSSConfig ossConfig = new OSSConfig();
        accessKeyId = ossConfig.getAccessKeyId();
        secretAccessKey = ossConfig.getSecretAccessKey();
        endPoint = ossConfig.getEndPoint();
        bucketName = ossConfig.getBucketName();
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 获取原始文件名并进行检查
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file must have a valid name.");
        }

        // 生成唯一文件名，避免文件覆盖
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // 获取当前时间并格式化为文件夹路径
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String fullPath = datePath + "/" + uniqueFileName;

        // 上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        try {
            ossClient.putObject(bucketName, fullPath, inputStream);
        } finally {
            // 确保输入流和 ossClient 最终被关闭
            inputStream.close();
            ossClient.shutdown();
        }

        // 返回文件访问路径
        return "https://" + bucketName + "." + endPoint + "/" + fullPath;
    }

}
