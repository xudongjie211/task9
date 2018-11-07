package com.jnshu.util.huaweiobs;

import com.obs.services.ObsClient;
import com.obs.services.model.ObsObject;

import java.io.*;

public  class ObsUtil {
	final static String endPoint = "https://obs.cn-east-2.myhwclouds.com";
	final static String ak = "5K0KJXTFYQAK0BCPOO3C";
	final static String sk = "2j70CqbzcT105SJUWWiClmyqhBEacyv4uQaOYANs";
	final static String bucketname = "xudongjiehuawei";





	//传递字符串
	public static void upload(String content,String obj) throws Exception {
		// 创建ObsClient实例
		ObsClient obsClient = new ObsClient(ak, sk, endPoint);

		// 使用访问OBS
		obsClient.putObject(bucketname, obj, new ByteArrayInputStream(content.getBytes()));
		// 关闭obsClient
		obsClient.close();
	}
	//上传文件流
	public static void uploadfile(String obj,String filename) throws Exception{
//		String endPoint = "https://yourdomainname";
//		String ak = "*** Provide your Access Key ***";
//		String sk = "*** Provide your Secret Key ***";
		// 创建ObsClient实例
		ObsClient obsClient = new ObsClient(ak, sk, endPoint);
		FileInputStream fis = new FileInputStream(new File(filename));
		obsClient.putObject(bucketname, obj, fis);
		// 关闭obsClient
		obsClient.close();
	}
	//不用流上传,同时创建文件夹
	public static void uploadlobalfile(String obj,String filename) throws Exception{
//		String endPoint = "https://yourdomainname";
//		String ak = "*** Provide your Access Key ***";
//		String sk = "*** Provide your Secret Key ***";
		// 创建ObsClient实例
		ObsClient obsClient = new ObsClient(ak, sk, endPoint);
		//创建文件夹
		final String keySuffixWithSlash = "demo1/";
		obsClient.putObject(bucketname, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));


		obsClient.putObject(bucketname, keySuffixWithSlash + obj, new File(filename));
		// 关闭obsClient
		obsClient.close();

	}
	//流式下载,没有普通下载
	public static void downloadliu(String obj,String filename) throws Exception {
		final ObsClient obsClient = new ObsClient(ak, sk, endPoint);
		ObsObject obsObject = obsClient.getObject(bucketname, obj);
		// 读取对象内容
		System.out.println("Object content:");
		InputStream input = obsObject.getObjectContent();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		FileOutputStream fos=new FileOutputStream(new File(filename));
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
		//bos.writeTo(fos);
		byte[] b = new byte[1024];
		int len;
		while ((len = input.read(b)) != -1) {
			fos.write(b);
			bos.write(b, 0, len);

		}
		System.out.println(new String(bos.toByteArray()));
		fos.close();
		bos.close();
		input.close();
		// 关闭obsClient
		obsClient.close();
	}




	public static void main(String[] args) {
		try {
//			upload("第一个华为云的储存","test1.txt");
//			uploadfile("技能书后台页面.jpg","D:/技能书后台页面.jpg");
//			uploadlobalfile("param.txt","D:/param.txt");
			downloadliu("demo1/param.txt","D:/param1.txt");
			System.out.println("发送成功");
		} catch (Exception e) {
			System.out.println("发送失败");
			e.printStackTrace();
		}
	}





}