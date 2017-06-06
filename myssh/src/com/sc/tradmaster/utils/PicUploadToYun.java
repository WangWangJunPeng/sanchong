package com.sc.tradmaster.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.tradmaster.utils.qcloud.cos.COSClient;
import com.sc.tradmaster.utils.qcloud.cos.ClientConfig;
import com.sc.tradmaster.utils.qcloud.cos.request.GetFileInputStreamRequest;
import com.sc.tradmaster.utils.qcloud.cos.request.GetFileLocalRequest;
import com.sc.tradmaster.utils.qcloud.cos.request.UploadFileRequest;
import com.sc.tradmaster.utils.qcloud.cos.sign.Credentials;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PicUploadToYun {
	
	static long appId;
	static String secretId;
	static String secretKey;
	
	public static String upload(String fileNewName,MultipartFile uploadFile,String comming) throws Exception {
		// 设置用户属性, 包括appid, secretId和SecretKey
		// 这些属性可以通过cos控制台获取(https://console.qcloud.com/cos)
		String version = PropertiesUtil.getValue("version");
		if(!version.equals(SmsContext.VERSION)){
			appId = 1252627680;
			secretId = "AKIDb5NqJjD470mQtBACxZ6lJhuwqy6dlGPR";
			secretKey = "2ub6yjNq8FBvrjS5WlrlgZGoBNND4pPM";
		}else{
			appId = 1252955170;
			secretId = "AKIDCICwZ0FI9rOkushFeqsjOrBKI4g7gQIz";
			secretKey = "f9RXR9f0AAzgDY4VlphOtDL6mHajhT3R";
		}
		
		// 设置要操作的bucket
		String bucketName = "root";
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion("sh");
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);
		// 文件操作 //
		// 1. 上传文件(默认不覆盖)
		// 将本地的local_file_1.txt上传到bucket下的根分区下,并命名为sample_file.txt
		// 默认不覆盖, 如果cos上已有文件, 则返回错误
		String cosFilePath = "/pictures/";
		//判断图片来源，上传指定路径
		if(comming.equals(SmsContext.AD_PIC)){
			cosFilePath += "adPic/";
		}else if(comming.equals(SmsContext.EF_PIC)){
			cosFilePath += "ettecPic/";
		}else if(comming.equals(SmsContext.HT_PIC)){
			cosFilePath += "houseTypePic/";
		}else if(comming.equals(SmsContext.CR_PIC)){
			cosFilePath += "recordContractPic/";
		}else if(comming.equals(SmsContext.SHOP_PIC)){
			cosFilePath += "shopsPic/";
		}else if(comming.equals(SmsContext.USERS_PIC)){
			cosFilePath += "usersPic/";
		}else if(comming.equals(SmsContext.WSC_PIC)){
			cosFilePath += "willSaleCard/";
		}else if(comming.equals(SmsContext.FB_PIC)){
			cosFilePath += "feedBackPic/";
		}
		cosFilePath += fileNewName;
		
		byte[] localFilePath1 =  uploadFile.getBytes();
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosFilePath, localFilePath1);
		uploadFileRequest.setEnableShaDigest(false);
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		System.out.println("upload file ret:" + uploadFileRet);
		//获取保存路径
		ObjectMapper om = new ObjectMapper();
		HashMap map = om.readValue(uploadFileRet, HashMap.class);
		HashMap<String, String> value = (HashMap<String, String>) map.get("data");
		return value.get("source_url");
	}
	
	
	//文件下载
	public static String downLoad(String cosFilePath) throws Exception {
		String version = PropertiesUtil.getValue("version");
		if(!version.equals(SmsContext.VERSION)){
			appId = 1252627680;
			secretId = "AKIDb5NqJjD470mQtBACxZ6lJhuwqy6dlGPR";
			secretKey = "2ub6yjNq8FBvrjS5WlrlgZGoBNND4pPM";
		}else{
			appId = 1252955170;
			secretId = "AKIDCICwZ0FI9rOkushFeqsjOrBKI4g7gQIz";
			secretKey = "f9RXR9f0AAzgDY4VlphOtDL6mHajhT3R";
		}
		// 设置要操作的bucket
		String bucketName = "root";
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz), 天津(tj)
		clientConfig.setRegion("sh");
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		GetFileInputStreamRequest getFileLocalRequest = new GetFileInputStreamRequest(bucketName, cosFilePath);
		getFileLocalRequest.setUseCDN(false);
		//getFileLocalRequest.setReferer("*.myweb.cn");
		InputStream in = cosClient.getFileInputStream(getFileLocalRequest);
		//转base64
		BufferedInputStream bufin = new BufferedInputStream(in);  
        int buffSize = 1024;  
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);  
    
        byte[] temp = new byte[buffSize];
        int size = 0;  
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);  
        }  
        bufin.close();  
        in.close();  
        byte[] content = out.toByteArray();
        out.close();
        System.out.println(content);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(content);
	}
	
	

	
	public static void main(String[] args) throws Exception {
		//String in = downLoad("/pictures/houseTypePic/20170509101448497.jpg");
		BufferedImage originalImage = ImageIO.read(new File("C:\\thumbnail.jpg"));
		BufferedImage thumbnail = Thumbnails.of(originalImage).scale(4f).asBufferedImage();
		ImageIO.write(thumbnail, "jpg",new File("C:\\thumbnail2.jpg"));
		System.out.println(thumbnail);
	}
	
	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = "d://222.jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
