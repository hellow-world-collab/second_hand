package com.cupk.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
@Component
public class AliOSSUtils {
        //        @Test
        public String upload(MultipartFile file) throws Exception {
//            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(index);
            String newfilename= UUID.randomUUID().toString()+suffix;
//            file.transferTo(new File("D:\\Users\\WJF\\IdeaProjects\\ems-thymeleaf\\src\\main\\resources\\static\\fileByuser"+newfilename));

            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。oss-cn-beijing.aliyuncs.com
            String endpoint = "https://oss-cn-beijing.aliyuncs.com";
            // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            // 填写Bucket名称，例如examplebucket。
            String bucketName = "webwjf";

            // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//            String objectName = "1.jpg";
            // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
            // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//            String filePath= "C:\\Users\\WJF\\OneDrive\\图片\\屏幕快照\\屏幕截图 2024-06-28 185132.png";

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);


                InputStream inputStream = file.getInputStream();
                // 创建PutObjectRequest对象。
//                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newfilename, inputStream);
//                // 创建PutObject请求。
//                PutObjectResult result = ossClient.putObject(putObjectRequest);
                ossClient.putObject(bucketName,newfilename , inputStream);
                String url=endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+newfilename;
//            } catch (OSSException oe) {
//                System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                        + "but was rejected with an error response for some reason.");
//                System.out.println("Error Message:" + oe.getErrorMessage());
//                System.out.println("Error Code:" + oe.getErrorCode());
//                System.out.println("Request ID:" + oe.getRequestId());
//                System.out.println("Host ID:" + oe.getHostId());
//            } catch (ClientException ce) {
//                System.out.println("Caught an ClientException, which means the client encountered "
//                        + "a serious internal problem while trying to communicate with OSS, "
//                        + "such as not being able to access the network.");
//                System.out.println("Error Message:" + ce.getMessage());
//            } finally {
//                if (ossClient != null) {
                    ossClient.shutdown();
//                }
//            }
            return url;
        }
}
