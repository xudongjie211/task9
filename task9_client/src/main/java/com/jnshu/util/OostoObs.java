package com.jnshu.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Maibenben on 2018/11/4.
 */
public class OostoObs {



    public static void main(String[] args) throws Exception {
        String aliyun_endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String aliyun_bucketName = "xudongjiejnshufirststorage";
        String aliyun_accessKeyId = "LTAIyLyvkcAyPKOX";
        String aliyun_accessKeySecret = "KMpgOCG804ds7wZqQt2TbdTwfwwPjG";
        String huawei_endpoint = "https://obs.cn-east-2.myhwclouds.com";
        String huawei_bucketName = "xudongjiehuawei";
        String huawei_accessKeyId = "5K0KJXTFYQAK0BCPOO3C";
        String huawei_accessKeySecret = "2j70CqbzcT105SJUWWiClmyqhBEacyv4uQaOYANs";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(aliyun_endpoint, aliyun_accessKeyId, aliyun_accessKeySecret);
    //创建华为云的实列
        ObsClient obsClient = new ObsClient(huawei_accessKeyId, huawei_accessKeySecret, huawei_endpoint);

        //构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(aliyun_bucketName);

        //列出所有文件
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        List<OSSObjectSummary> sums = listing.getObjectSummaries();


        //遍历该目录下所有文件 , 逐个从阿里云转储到腾讯云
        for (OSSObjectSummary objectSummary : sums) {
            String objectKey = objectSummary.getKey();
            System.out.println("objectKey = " + objectKey);
            fromOssToObs(ossClient, obsClient, objectKey);
        }
        ossClient.shutdown();
        obsClient.close();


    }

    public static void fromOssToObs(OSSClient ossClient,ObsClient obsClient,String objectKey) throws Exception{
        String aliyun_endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String aliyun_bucketName = "xudongjiejnshufirststorage";
        String aliyun_accessKeyId = "LTAIyLyvkcAyPKOX";
        String aliyun_accessKeySecret = "KMpgOCG804ds7wZqQt2TbdTwfwwPjG";
        String huawei_endpoint = "https://obs.cn-east-2.myhwclouds.com";
        String huawei_bucketName = "xudongjiehuawei";
        String huawei_accessKeyId = "5K0KJXTFYQAK0BCPOO3C";
        String huawei_accessKeySecret = "2j70CqbzcT105SJUWWiClmyqhBEacyv4uQaOYANs";


        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(aliyun_bucketName, objectKey);
        InputStream input = ossObject.getObjectContent();
        com.aliyun.oss.model.ObjectMetadata metaGet = ossObject.getObjectMetadata();
        Map<String, String> metaDataGet = metaGet.getUserMetadata();

        Map<String, Object> metaDataPut = new HashMap<>();
        metaDataPut.putAll(metaDataGet);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setMetadata(metaDataPut);
        obsClient.putObject(huawei_bucketName,objectKey,input,objectMetadata);

    }



}
